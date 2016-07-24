package pe.edu.upeu.smscore.dao;

import java.util.List;

import pe.edu.upeu.smscore.domain.Person;

public class SmsDAOImpl extends BaseDAOHibernate implements SmsDAO {

	public void savePerson(Person person) {
		save(person);
	}

	// @SuppressWarnings("unchecked")
	public List<Person> findPersonAll() {
		// Query tmp = getSession().createQuery("from Person");
		// tmp.setMaxResults(20);
		// return (List<Person>) tmp.list();
		return find(Person.class, "from Person");
	}

	public Person findPersonById(Long id) {
		String query = "from Person where id=" + id;
		return findFirst(Person.class, query);
	}

	public void deletePerson(Person person) {
		delete(person);
	}

	public int deleteAllPerson() {
		return deleteAll("DELETE FROM Person");
	}

	public void saveObjectAllPerson(List<Person> list) {
		saveObjectAll(list);
	}

}
