package pe.edu.upeu.smscore.service.services;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upeu.smscore.AbstractUnitTest;

public class SmsServiceImplTest extends AbstractUnitTest{

	@Autowired
	protected SmsServiceImpl serviceImpl;
	
	public void testFindPersonAll() {
		System.out.println(">>>>>>>>>"+serviceImpl.findPersonAll());
	}
	
}
