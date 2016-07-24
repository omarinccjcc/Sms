package pe.edu.upeu.smscore.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pe.edu.upeu.smscore.domain.BaseEntity;

/**
 * 
 * @author ocalsin
 *
 */

public class BaseDAOHibernate extends HibernateDaoSupport {

	// public BaseDAOHibernate(){
	// }

	/**
	 * When use parameter arrays to limit the search, usually use the 'like'
	 * condition. To use this with arrays, we need, first, convert the parameter
	 * to the '%param%' form to be correctly replaced in the Hibernate parsin
	 * process.
	 * 
	 * @param param
	 * @return
	 */
	protected String likeParameter(String param) {
		return "%" + param + "%";
	}

	public <T> T findById(Class<T> entityClass, Long id) throws DataAccessException {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public void save(BaseEntity entity) throws DataAccessException {
		Date systemDate = new Date();
		// entity.setDateLastUpdated(systemDate);
		// if (entity.getId() == null || entity.getDateCreated() == null) {
		if (entity.getId() == null) {
			// entity.setDateCreated(systemDate);
			getHibernateTemplate().save(entity);
		} else {
			try {
				getHibernateTemplate().update(entity);
			} catch (HibernateSystemException systemExc) {
				if (systemExc.getRootCause() instanceof NonUniqueObjectException) {
					getHibernateTemplate().merge(entity);
				} else {
					logger.info("0%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					systemExc.printStackTrace();
					logger.info("1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					throw systemExc;
				}
			}
		}

		// try {
		// getHibernateTemplate().save(entity);
		// }catch (HibernateSystemException systemExc) {
		// if (systemExc.getRootCause() instanceof NonUniqueObjectException) {
		// getHibernateTemplate().merge(entity);
		// } else {
		//// logger.info("0%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		// systemExc.printStackTrace();
		//// logger.info("1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		// throw systemExc;
		// }
		// }
	}

	public void update(BaseEntity entity) throws DataAccessException {
		Date systemDate = new Date();
		// entity.setDateLastUpdated(systemDate);
		try {
			getHibernateTemplate().update(entity);
		} catch (HibernateSystemException systemExc) {
			if (systemExc.getRootCause() instanceof NonUniqueObjectException) {
				getHibernateTemplate().merge(entity);
			} else {
				logger.info("0%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				systemExc.printStackTrace();
				logger.info("1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				throw systemExc;
			}
		}
	}

	public void saveObject(Object entity) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (HibernateSystemException systemExc) {
			if (systemExc.getRootCause() instanceof NonUniqueObjectException) {
				getHibernateTemplate().merge(entity);
			} else {
				// logger.info("0%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				systemExc.printStackTrace();
				// logger.info("1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				throw systemExc;
			}
		}
	}

	public void saveObjectAll(List<?> entities) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(entities);
		} catch (HibernateSystemException systemExc) {
			if (systemExc.getRootCause() instanceof NonUniqueObjectException) {
				getHibernateTemplate().merge(entities);
			} else {
				// logger.info("0%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				systemExc.printStackTrace();
				// logger.info("1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				throw systemExc;
			}
		}
	}

	public <T> void delete(T entity) throws DataAccessException {
		getHibernateTemplate().delete(entity);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> List<T> find(Class<T> entityClass, String queryString) {
		// if (logger.isDebugEnabled()){
		// logger.debug("FIND :: " + queryString);
		// }
		return (List<T>) getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> List<T> find(Class<T> entityClass, String queryString, Object[] params) {
		// if (logger.isDebugEnabled()){
		// logger.debug("FIND :: " + queryString);
		// }
		return (List<T>) getHibernateTemplate().find(queryString, params);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> T findUniqueResult(String queryString) {
		Session session = getDaoSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findSQLData(String queryString, Class<T> entityClass) {
		Session session = getDaoSession();
		Query query = session.createSQLQuery(queryString).addEntity(entityClass);
		flushing(session);
		return (List<T>) query.list();
	}

	@SuppressWarnings("unchecked")
	public <T> T findSQLDataUniqueResult(String queryString, Class<T> entityClass) {
		Session session = getDaoSession();
		Query query = session.createSQLQuery(queryString).addEntity(entityClass);
		query.setFirstResult(0);
		query.setMaxResults(1);
		flushing(session);
		return (T) query.uniqueResult();
	}

	public <T> T firstIfExist(List<T> list) {
		if (list == null || list.size() <= 0) {
			return null;
		}
		return list.get(0);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> T findFirst(Class<T> entityClass, String queryString) {
		return firstIfExist((List<T>) getHibernateTemplate().find(queryString));
	}

	@SuppressWarnings(value = "unchecked")
	public <T> T findFirst(Class<T> entityClass, String queryString, Object... params) {
		return firstIfExist((List<T>) getHibernateTemplate().find(queryString, params));
	}

	@SuppressWarnings(value = "unchecked")
	public <T> List<T> findList(Class<T> entityClass, String queryString, Object... params) {
		// if (logger.isDebugEnabled()){
		// logger.debug("FIND :: " + queryString);
		// }
		return (List<T>) getHibernateTemplate().find(queryString, params);
	}

	public void initialize(Object proxy) {
		getHibernateTemplate().initialize(proxy);
	}

	public SQLQuery getSQLQuery(String sql) {
		Session session = getDaoSession();
		return session.createSQLQuery(sql);
	}

	@SuppressWarnings("unchecked")
	public List findSqlList(SQLQuery query) {
		Session session = getDaoSession();
		flushing(session);
		return query.list();
	}

	private void flushing(Session session) {
		// ( from:
		// http://docs.jboss.org/hibernate/orm/3.3/reference/es-ES/html/objectstate.html
		// )
		// Except when you explicitly flush(), there are absolutely no
		// guarantees about when the Session executes the JDBC calls,
		// only the order in which they are executed.
		// However, Hibernate does guarantee that the Query.list(..) will never
		// return stale or incorrect data.
		session.flush();
	}

	private Session getDaoSession() {
		// ( from getSession() API )
		// In general, it is recommended to use HibernateTemplate, either with
		// the provided convenience operations or with a custom
		// HibernateCallback
		// that provides you with a Session to work on. HibernateTemplate will
		// care for all resource management and for proper exception conversion.
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session;
	}

	public int deleteAll(String stringQuery) {
		// String stringQuery = "DELETE FROM tablename";
		Query query = this.getDaoSession().createQuery(stringQuery);
		return query.executeUpdate();
	}

}
