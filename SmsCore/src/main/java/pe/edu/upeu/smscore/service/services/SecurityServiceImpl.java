package pe.edu.upeu.smscore.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upeu.smscore.dao.SecurityDAO;
import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.util.User;

@Service("securityService")
public class SecurityServiceImpl {

	@Autowired
	private SecurityDAO securityDAO;

	public User findValidadUser(String login, String password) {
		UserSystem userSystem = securityDAO.findUserSystemByLoginAndPass(login, password);
		User user = null;
		if (userSystem != null) {
			user = new User();
			user.setStatus("Espera");
			user.setFullName(userSystem.getAllName() + " - " + userSystem.getLogin());
			user.setRoles(securityDAO.findRoleByUserId(userSystem.getId()));
			user.setDepartamentId(userSystem.getDepartamentId());
			user.setUserId(userSystem.getId());
		}
		return user;
	}
}
