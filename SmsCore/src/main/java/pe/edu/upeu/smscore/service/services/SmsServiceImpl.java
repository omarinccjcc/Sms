package pe.edu.upeu.smscore.service.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.util.NumberUtils;
//import org.springframework.stereotype.Service;

import pe.edu.upeu.smscore.dao.SmsDAO;
import pe.edu.upeu.smscore.domain.AccessApp;
import pe.edu.upeu.smscore.domain.Campaign;
import pe.edu.upeu.smscore.domain.Departament;
import pe.edu.upeu.smscore.domain.Role;
import pe.edu.upeu.smscore.domain.Sms;
import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.domain.UserSystemRole;
import pe.edu.upeu.smscore.util.SmsFields;
import pe.edu.upeu.smscore.util.StatusSms;

@Service("smsService")
public class SmsServiceImpl {

	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SmsDAO smsDAO;

	public void processChangeStatusSms(Long campaignId, Long departamentId, String statusSearch, String statusChange) {
		logger.info("processChangeStatusSms campaignId:" + campaignId + " departamentId:" + departamentId
				+ " statusSearch:" + statusSearch + " statusChange:" + statusChange);

		List<Sms> list = smsDAO.findSmsAll(campaignId, departamentId, statusSearch,0);
		for (Sms sms : list) {
			sms.setStatusSms(statusChange);
			smsDAO.saveSms(sms);
		}
	}

	public void processDeleteSms(Long campaignId, String status) {
		smsDAO.deleteSmsByCampaignId(campaignId, status);
	}

	public void processMessage(Long campaignId, Long departamentId, String messageTemplete) {
		logger.info("processMessage campaignId:" + campaignId + " departamentId:" + departamentId + " messageTemplete:"
				+ messageTemplete);

		List<Sms> list = smsDAO.findSmsAll(campaignId, departamentId, StatusSms.STATUS_SMS_ESPERA.getStatusCode(),0);
		for (Sms sms : list) {
			String message = messageTemplete;
			for (SmsFields dir : SmsFields.values()) {
				if (message.contains(dir.toString())) {
					message = message.replace(dir.toString(), sms.getMethodInformation(dir.toString()));
				}
			}
			sms.setMessage(message);
			smsDAO.saveSms(sms);
		}
	}

	public List<Sms> findSmsAll(Long campaignId, Long departamentId, String status,int limit) {
		return smsDAO.findSmsAll(campaignId, departamentId, status, limit);
	}

	public void saveSms(Sms sms) {
		smsDAO.saveSms(sms);
	}

	public Sms findSmsById(Long id) {
		return smsDAO.findSmsById(id);
	}

	public void deleteSms(Sms sms) {
		smsDAO.deleteSms(sms);
	}

	public void processLoadFile(String fname, Long departamentId, Long campaignId) throws IOException {
		logger.info("processLoadFile campaignId:" + campaignId + " departamentId:" + departamentId + " fname:" + fname);

		List<Sms> listSms = new ArrayList<Sms>();

		InputStream inp = new FileInputStream(fname);
		XSSFWorkbook wb = new XSSFWorkbook(inp); // Declare XSSF WorkBook
		XSSFSheet sheet = wb.getSheetAt(0); // sheet can be used as common
											// for
		// XSSF and HSSF
		Iterator<org.apache.poi.ss.usermodel.Row> rows = sheet.rowIterator();
		int count = 1;
		while (rows.hasNext()) {
			org.apache.poi.ss.usermodel.Row row = rows.next();
			if (row.getRowNum() != 0) {
				// Iterator cells = row.cellIterator();
				Sms sms = new Sms();

				sms.setNameAll(row.getCell(0) != null ? row.getCell(0).toString() : "");
				// sms.setLastNameF(row.getCell(1).toString());
				// sms.setLastNameM(row.getCell(2).toString());

				try {
					if (row.getCell(1) == null) {
						throw new IOException("Favor de ingresar numero de celular en la segunda columna");
					}

					if (row.getCell(1).getCellType() != Cell.CELL_TYPE_NUMERIC) {
						throw new IOException("El numero de celular debe de ser número");
					}

					NumberFormat formatter = new DecimalFormat();
					formatter = new DecimalFormat("000000000");
					System.out.println("......" + formatter.format(row.getCell(1).getNumericCellValue()));
					String numeroTele = formatter.format(row.getCell(1).getNumericCellValue());

					if (numeroTele.toString().length() == 9) {
						sms.setNumPhone(numeroTele);
					} else {
						System.err.println("ERROR en TAMAÑO " + numeroTele);
						throw new IOException("El numero de Celular debe de ser de nueve digitos: " + numeroTele);
					}
				} catch (IOException e) {
					// TODO: handle exception
					System.err.println("No Integer " + row.getCell(1));
					throw new IOException(
							"Error en la carga del numero de celular: " + row.getCell(1) + " del registro " + count);
				}

				sms.setTextA(row.getCell(2) != null ? row.getCell(2).toString() : "");
				sms.setTextB(row.getCell(3) != null ? row.getCell(3).toString() : "");
				sms.setDateA(row.getCell(4) != null ? row.getCell(4).toString() : "");
				sms.setDateB(row.getCell(5) != null ? row.getCell(5).toString() : "");
				sms.setAmountA(row.getCell(6) != null ? new BigDecimal(row.getCell(6).toString()) : null);
				sms.setAmountB(row.getCell(7) != null ? new BigDecimal(row.getCell(7).toString()) : null);
				sms.setDepartamentId(departamentId);
				sms.setCampaignId(campaignId);
				sms.setStatusSms(StatusSms.STATUS_SMS_ESPERA.getStatusCode());
				listSms.add(sms);
				count++;
			}
		}
		inp.close();

		// smsDAO.deleteAllSms();
		smsDAO.saveObjectAllSms(listSms);
	}

	public void saveAccessApp(AccessApp accessApp) {
		smsDAO.saveAccessApp(accessApp);
	}

	public List<AccessApp> findAccessAppAll() {
		return smsDAO.findAccessAppAll();
	}

	public AccessApp findAccessAppById(Long id) {
		return smsDAO.findAccessAppById(id);
	}

	public void deleteAccessApp(Long id) {
		AccessApp accessApp = smsDAO.findAccessAppById(id);
		smsDAO.deleteAccessApp(accessApp);
	}

	public AccessApp findAccessAppByIMEI(String imei) {
		return smsDAO.findAccessAppByIMEI(imei);
	}

	public UserSystem findUserSystemById(Long id) {
		return smsDAO.findUserSystemById(id);
	}

	public List<UserSystem> findUserSystem() {
		return smsDAO.findUserSystem();
	}

	public void processSaveUserSystem(UserSystem userSystem, List<UserSystemRole> listUserSystemRole) {
		smsDAO.deleteUserSystemRole(userSystem.getId());

		smsDAO.saveUserSystem(userSystem);
		for (UserSystemRole userSystemRole : listUserSystemRole) {
			userSystemRole.setUserId(userSystem.getId());
			smsDAO.saveUserSystemRole(userSystemRole);
		}

	}

	public void saveUserSystem(UserSystem userSystem){
		smsDAO.saveUserSystem(userSystem);
	}
	public void deleteUserSystem(Long id) {
		UserSystem userSystem = smsDAO.findUserSystemById(id);
		smsDAO.deleteUserSystem(userSystem);
	}

	public List<Role> findRoleByUserId(Long userId) {
		return smsDAO.findRoleByUserId(userId);
	}

	public List<UserSystemRole> findUserSystemRoleByUserId(Long userId) {
		return smsDAO.findUserSystemRoleByUserId(userId);
	}

	public Role findRoleById(Long id) {
		return smsDAO.findRoleById(id);
	}

	/**
	 * save object departament
	 * 
	 * @param departament
	 */
	public void saveDepartament(Departament departament) {
		smsDAO.saveDepartament(departament);
	}

	public void deleteDepartament(Long id) {
		Departament departament = smsDAO.findDepartamentById(id);
		smsDAO.deleteDepartament(departament);
	}

	public Departament findDepartamentById(Long id) {
		return smsDAO.findDepartamentById(id);
	}

	public List<Departament> findDepartamentByStatus(String status) {
		return smsDAO.findDepartamentByStatus(status);
	}

	//
	/**
	 * save object departament
	 * 
	 * @param campaign
	 */
	public void saveCampaign(Campaign campaign) {
		smsDAO.saveCampaign(campaign);
	}

	public void deleteCampaign(Long id) {
		Campaign campaign = smsDAO.findCampaignById(id);
		smsDAO.deleteCampaign(campaign);
	}

	public Campaign findCampaignById(Long id) {
		return smsDAO.findCampaignById(id);
	}

	public List<Campaign> findCampaignByDepartamentId(Long departamentId) {
		return smsDAO.findCampaignByDepartamentId(departamentId);
	}

}
