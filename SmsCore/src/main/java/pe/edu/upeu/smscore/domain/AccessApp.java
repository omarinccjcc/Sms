package pe.edu.upeu.smscore.domain;

import java.io.Serializable;
import java.util.Date;

import pe.edu.upeu.smscore.util.CommonUtils;

public class AccessApp extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imei;
	private Date dateIni;
	private Date dateEnd;
	private String statusAccessApp;
	private Long departamentId;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Date getDateIni() {
		return dateIni;
	}

	public void setDateIni(Date dateIni) {
		this.dateIni = dateIni;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getStatusAccessApp() {
		return statusAccessApp;
	}

	public void setStatusAccessApp(String statusAccessApp) {
		this.statusAccessApp = statusAccessApp;
	}

	public String getDateIniString() {
		return CommonUtils.dateToString(dateIni, "dd/MM/yyyy");
	}

	public void setDateIniString(String dateIniString) {
		this.dateIni = CommonUtils.stringToDate(dateIniString, "dd/MM/yyyy");
	}

	public String getDateEndString() {
		return CommonUtils.dateToString(dateEnd, "dd/MM/yyyy");
	}

	public void setDateEndString(String dateEndString) {
		this.dateEnd = CommonUtils.stringToDate(dateEndString, "dd/MM/yyyy");
	}

	public Long getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(Long departamentId) {
		this.departamentId = departamentId;
	}

}
