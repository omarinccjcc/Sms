package pe.edu.upeu.smscore.dao;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class GpsDAOTest extends AbstractTransactionalDataSourceSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		setPopulateProtectedVariables(true);
		setAutowireMode(AUTOWIRE_BY_TYPE);
		return new String[] { "pe/edu/upeu/smscore/config/applicationContext.xml" };
	}

	public void testApp() {
		assertTrue(true);
	}

	protected GpsDAO gpsDAO;

	public void test() {
		System.out.println(gpsDAO);
		gpsDAO.selectRegs();
	}

}
