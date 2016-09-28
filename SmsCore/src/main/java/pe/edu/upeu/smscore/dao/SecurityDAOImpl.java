package pe.edu.upeu.smscore.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;

import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.util.CommonUtils;

public class SecurityDAOImpl extends BaseDAOHibernate implements SecurityDAO {

	public UserSystem findUserSystemByLoginAndPass(String login, String password) {
		Date toyDate = new Date();
		StringBuffer query = new StringBuffer();
		query.append(" FROM UserSystem where login = '" + login + "'");
		query.append(" AND password='" + password + "'");
		query.append(" AND statusUser = 'Activo' ");
		query.append(" AND (( '" + CommonUtils.dateToString(toyDate, "yyyy-MM-dd")
				+ "' BETWEEN dateIni AND dateEnd ) OR (dateIni IS NULL OR dateEnd IS NULL) )");
		System.out.println("query: "+query.toString());
		return findFirst(UserSystem.class, query.toString());
	}

	public String[] findRoleByUserId(Long userId) {
		String sql = "SELECT url FROM userSystemRole ur JOIN role r ON (ur.roleId = r.id) WHERE ur.userId =" + userId
				+ " order by url DESC";
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		List<Object> rows = query.list();
		String[] arrayRole = new String[rows.size()];

		int count = 0;
		for (Iterator<Object> iterator = rows.iterator(); iterator.hasNext();) {
			arrayRole[count] = (String) iterator.next();
			count++;
		}
		System.out.println("arrayRole > " + arrayRole);
		return arrayRole;

	}

}
