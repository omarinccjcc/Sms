package pe.edu.upeu.smscore.dao;

import pe.edu.upeu.smscore.domain.UserSystem;

public interface SecurityDAO {

	UserSystem findUserSystemByLoginAndPass(String login, String password);

	String[] findRoleByUserId(Long userId);

}
