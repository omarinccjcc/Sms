package pe.edu.upeu.smscore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;

import pe.edu.upeu.smscore.domain.AccessApp;
import pe.edu.upeu.smscore.domain.Campaign;
import pe.edu.upeu.smscore.domain.Departament;
import pe.edu.upeu.smscore.domain.Role;
import pe.edu.upeu.smscore.domain.Sms;
import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.domain.UserSystemRole;
import pe.edu.upeu.smscore.util.CommonUtils;

public class SmsDAOImpl extends BaseDAOHibernate implements SmsDAO {

	protected Log logger = LogFactory.getLog(getClass());

	public void saveSms(Sms sms) {
		save(sms);
	}

	// @SuppressWarnings("unchecked")
	public List<Sms> findSmsAll(Long campaignId, Long departamentId, String status, int limit) {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT * FROM Sms WHERE");
		query.append(" departamentId = " + departamentId);
		if (campaignId != null) {
			query.append(" AND campaignId =" + campaignId);
		}
		query.append(" AND statusSms ='" + status + "'");
//		query.append(" limit 50 ");
		logger.info("query.toString() " + query.toString());
		return findDataLimit(query.toString(),Sms.class, limit );
	}

	
	public Sms findSmsById(Long id) {
		String query = "from Sms where id=" + id;
		return findFirst(Sms.class, query);
	}

	public void deleteSms(Sms sms) {
		delete(sms);
	}

	public int deleteSmsByCampaignId(Long campaignId, String status) {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM Sms where campaignId=");
		query.append(campaignId);
		query.append(" and statusSms='" + status + "'");

		return deleteAll(query.toString());
	}

	public void saveObjectAllSms(List<Sms> list) {
		saveObjectAll(list);
	}

	public void saveAccessApp(AccessApp accessApp) {
		save(accessApp);
	}

	public void deleteAccessApp(AccessApp accessApp) {
		delete(accessApp);
	}

	public AccessApp findAccessAppById(Long id) {
		String query = "from AccessApp where id=" + id;
		return findFirst(AccessApp.class, query);
	}

	public AccessApp findAccessAppByIMEI(String imei) {
		Date toyDate = new Date();

		String query = " from AccessApp where imei = '" + imei + "'";
		query = query + " AND ( ('" + CommonUtils.dateToString(toyDate, "yyyy-MM-dd")
				+ "' BETWEEN dateIni AND dateEnd) OR ";
		query = query + " (dateIni IS NULL AND dateEnd IS NULL)  ) AND statusAccessApp = 'Activo' ";

		return findFirst(AccessApp.class, query);
	}

	public List<AccessApp> findAccessAppAll() {
		return find(AccessApp.class, "from AccessApp");
	}

	// ok
	public List<UserSystem> findUserSystem() {
		return find(UserSystem.class, "from UserSystem");
	}

	// ok
	public UserSystem findUserSystemById(Long id) {
		return findFirst(UserSystem.class, "from UserSystem where id=" + id);
	}

	// ok
	public void saveUserSystem(UserSystem userSystem) {
		save(userSystem);

	}

	public void deleteUserSystem(UserSystem userSystem) {
		delete(userSystem);
	}

	// ok
	public void deleteUserSystemRole(Long id) {
		executeSQL("DELETE FROM UserSystemRole where userId=" + id);
	}

	// ok
	public void saveUserSystemRole(UserSystemRole userSystemRole) {
		String query = "Insert into UserSystemRole VALUES (" + userSystemRole.getUserId() + ","
				+ userSystemRole.getRoleId() + ")";
		executeSQL(query);

	}

	public Role findRoleById(Long id) {
		return findFirst(Role.class, "from Role where id=" + id);
	}

	public List<Role> findRoleByUserId(Long userId) {
		String sql = "SELECT CONCAT(r.id,'') AS roleId,r.roleName,r.description,r.url, "
				+ " (SELECT userId FROM usersystemrole u WHERE r.id=u.roleId AND u.userId = " + userId + ") AS userId "
				+ " FROM role r";

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		List<Role> list = new ArrayList<Role>();

		List<Object> rows = query.list();
		for (Iterator<Object> iterator = rows.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Role role = new Role();
			role.setId(new Long((String) objects[0]));
			role.setRoleName((String) objects[1]);
			role.setDescription((String) objects[2]);
			role.setUrl((String) objects[3]);
			if (objects[4] != null) {
				role.setCheck("checked='checked'");
			}
			list.add(role);
		}
		return list;
	}

	public List<UserSystemRole> findUserSystemRoleByUserId(Long userId) {
		String sql = "select * from UserSystemRole where userId=" + userId;

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		List<UserSystemRole> list = new ArrayList<UserSystemRole>();

		List<Object> rows = query.list();
		for (Iterator<Object> iterator = rows.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			UserSystemRole obj = new UserSystemRole();
			obj.setUserId(new Long((String) objects[0]));
			obj.setRoleId(new Long((String) objects[1]));
			list.add(obj);
		}
		return list;
	}

	/**
	 * maintain of departament
	 */
	public void saveDepartament(Departament departament) {
		save(departament);
	}

	public void deleteDepartament(Departament departament) {
		delete(departament);
	}

	public Departament findDepartamentById(Long id) {
		return findFirst(Departament.class, "from Departament where id = " + id);
	}

	public List<Departament> findDepartamentByStatus(String status) {
		String query = "from Departament ";
		if (status != null) {
			query = query + " where statusDepartament ='" + status + "'";
		}
		return find(Departament.class, query);
	}

	/**
	 * maintain of Campaign
	 * 
	 * @param campaign
	 */
	public void saveCampaign(Campaign campaign) {
		save(campaign);
	}

	public void deleteCampaign(Campaign campaign) {
		delete(campaign);
	}

	public Campaign findCampaignById(Long id) {
		return findFirst(Campaign.class, "from Campaign where id = " + id);
	}

	public List<Campaign> findCampaignByDepartamentId(Long departamentId) {
		String query = "from Campaign where departamentId = " + departamentId;
		return find(Campaign.class, query);
	}

}
