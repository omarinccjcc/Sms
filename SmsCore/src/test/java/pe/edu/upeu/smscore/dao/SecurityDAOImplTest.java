package pe.edu.upeu.smscore.dao;

import pe.edu.upeu.smscore.AbstractUnitTest;

public class SecurityDAOImplTest extends AbstractUnitTest {

	protected SecurityDAO securityDAO;
	
	public void testFindRoleByUserId() {
		String array[] = securityDAO.findRoleByUserId(1L);
		
	}
	
}
