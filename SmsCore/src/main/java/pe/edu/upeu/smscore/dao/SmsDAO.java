package pe.edu.upeu.smscore.dao;

import java.util.List;

import pe.edu.upeu.smscore.domain.AccessApp;
import pe.edu.upeu.smscore.domain.Campaign;
import pe.edu.upeu.smscore.domain.Departament;
import pe.edu.upeu.smscore.domain.Sms;
import pe.edu.upeu.smscore.domain.Role;
import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.domain.UserSystemRole;

public interface SmsDAO {

	void saveSms(Sms sms);

	List<Sms> findSmsAll(Long campaignId, Long departamentId, String status,int limit);

	Sms findSmsById(Long id);

	void deleteSms(Sms sms);

	int deleteSmsByCampaignId(Long campaignId,String status);

	void saveObjectAllSms(List<Sms> list);

	void saveAccessApp(AccessApp accessApp);

	void deleteAccessApp(AccessApp accessApp);

	AccessApp findAccessAppById(Long id);

	List<AccessApp> findAccessAppAll();

	AccessApp findAccessAppByIMEI(String imei);

	List<UserSystem> findUserSystem();

	UserSystem findUserSystemById(Long id);

	void saveUserSystem(UserSystem userSystem);

	void deleteUserSystem(UserSystem userSystem);

	void deleteUserSystemRole(Long id);

	void saveUserSystemRole(UserSystemRole userSystemRole);

	List<Role> findRoleByUserId(Long userId);

	List<UserSystemRole> findUserSystemRoleByUserId(Long userId);

	Role findRoleById(Long id);

	void saveDepartament(Departament departament);

	void deleteDepartament(Departament departament);

	Departament findDepartamentById(Long id);

	List<Departament> findDepartamentByStatus(String status);

	void saveCampaign(Campaign campaign);

	void deleteCampaign(Campaign campaign);

	Campaign findCampaignById(Long id);

	List<Campaign> findCampaignByDepartamentId(Long departamentId);

}
