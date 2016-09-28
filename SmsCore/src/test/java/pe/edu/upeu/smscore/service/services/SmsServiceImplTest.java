package pe.edu.upeu.smscore.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upeu.smscore.AbstractUnitTest;
import pe.edu.upeu.smscore.domain.Sms;
import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.domain.UserSystemRole;
import pe.edu.upeu.smscore.util.CommonUtils;
import pe.edu.upeu.smscore.util.SmsFields;
import pe.edu.upeu.smscore.util.StatusAccessApp;
import pe.edu.upeu.smscore.util.StatusSms;

public class SmsServiceImplTest extends AbstractUnitTest {

	@Autowired
	protected SmsServiceImpl serviceImpl;

	public void testFindAccessAppByIMIE() {
		// System.out.println(serviceImpl.findAccessAppByIMEI("357344060605327").getDateEndString());
	}

	public void testFindSmsAll() {

		StatusAccessApp kk = StatusAccessApp.STATUS_ACCESS_ACT;
		System.out.println(kk.getStatusCode());

		System.out.println(StatusAccessApp.STATUS_ACCESS_ACT.getStatusCode());

		System.out.println(">>>>>>>>>" + serviceImpl.findSmsAll(0L, 0L, null));
	}

	/**
	 * 
	 */
	public void test() {

		String roleIds[] = { "2" };

		UserSystem userSystem = serviceImpl.findUserSystemById(10L);

		// UserSystem userSystem = new UserSysCtem();
		userSystem.setLogin("nueva Sms");

		List<UserSystemRole> userSystemRoleLista = new ArrayList<UserSystemRole>();

		for (String roleId : roleIds) {
			UserSystemRole userSystemRole = new UserSystemRole();
			userSystemRole.setRoleId(new Long(roleId));
			userSystemRole.setUserId(userSystem.getId());
			userSystemRoleLista.add(userSystemRole);
		}

		System.out.println("okkkkkkkkkkkkkk" + userSystem.getId());
		serviceImpl.processSaveUserSystem(userSystem, userSystemRoleLista);
		// setComplete();
		// userSystem.setUserSystemRoleLista(userSystemRoleLista);

	}

	public void testEncrypted() {
		System.out.println(":::::" + CommonUtils.encrypt("admin"));
	}

	public void test2() {
		// System.out.println(CommonUtils.encrypt("admision.juliaca"));

		String messageTemplete = "textA Contacto: textB. Examen de Admision: dateA. www.upeu.edu.pe";

		System.out.println("messageTemplete::: " + messageTemplete);

		// SmsFields

		List<Sms> list = serviceImpl.findSmsAll(7L, 3L, StatusSms.STATUS_SMS_ESPERA.getStatusCode());
		for (Sms sms : list) {
			String message = messageTemplete;
			for (SmsFields dir : SmsFields.values()) {
				
				if (message.contains(dir.toString())) {
					message = message.replace(dir.toString(), sms.getMethodInformation(dir.toString()));
				}
			}
			System.out.println(message);
		}

	}

}
