package pe.edu.upeu.smscore.dao;

import java.util.List;

import pe.edu.upeu.smscore.domain.Person;

public interface SmsDAO {

	void savePerson(Person person);

	List<Person> findPersonAll();

	Person findPersonById(Long id);

	void deletePerson(Person person);

	int deleteAllPerson();

	void saveObjectAllPerson(List<Person> list);

}
