package pe.edu.upeu.smscore;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class AbstractUnitTest extends AbstractTransactionalDataSourceSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		setPopulateProtectedVariables(true);
		setAutowireMode(AUTOWIRE_BY_TYPE);
		return new String[] { "pe/edu/upeu/smscore/config/application-config.xml",
				"pe/edu/upeu/smscore/config/applicationContext-dao.xml",
				"pe/edu/upeu/smscore/service/config/applicationContext-service.xml" };
	}

	public void testApp() {
		assertTrue(true);
	}

}
