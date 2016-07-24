package pe.edu.upeu.smscore.dao;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upeu.smscore.AbstractUnitTest;
import pe.edu.upeu.smscore.domain.Person;

public class SmsDAOImplTest extends AbstractUnitTest{

	@Autowired
	protected SmsDAO smsDAO;
	
	public void testFindPersonAll() {
		System.out.println(">>>>>"+smsDAO.findPersonAll());
	}

	public void testFindPersonById() {
		System.out.println(">>>>>"+smsDAO.findPersonById(1L));
	}
	
	public void testSavePerson() {
		Person person =  new Person();
		person.setFirstName("David");
		person.setLastNameF("Mamani");
		person.setLastNameM("Pari");
//		person.setMessage("Favor de pagar su deuda 2512.25");
//		person.setNumPhone("995590753");
		smsDAO.savePerson(person);
		setComplete();
		
	}

}
