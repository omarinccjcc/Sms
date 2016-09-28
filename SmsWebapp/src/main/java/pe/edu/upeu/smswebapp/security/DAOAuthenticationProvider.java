package pe.edu.upeu.smswebapp.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import pe.edu.upeu.smscore.service.services.SecurityServiceImpl;
import pe.edu.upeu.smscore.util.CommonUtils;
import pe.edu.upeu.smscore.util.User;

/**
 * 
 * @author ocalsin
 *
 */

public class DAOAuthenticationProvider implements AuthenticationProvider {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SecurityServiceImpl securityService;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UsernamePasswordAuthenticationToken successToken = null;

		if (SessionDetail.getUserDetails() == null) {
			logger.info(":: authenticate ::");
			String username = null;
			String password = null;

			try {
				username = authentication.getPrincipal().toString();
				password = authentication.getCredentials().toString();
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
				throw new BadCredentialsException("Username or Password is empty: " + username);
			}

			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				throw new BadCredentialsException("Username or Password is empty: " + username);
			}
			password = CommonUtils.encrypt(password);

			User user = securityService.findValidadUser(username, password);
			// User user = null;
			// if (username.equals("admin") && password.equals("admin")) {
			// user = new User();
			// String[] roles = { "ROLE_ADMIN" };
			// user.setRoles(roles);
			// user.setUserName("administrador del sistema");
			// user.setUserId(2L);
			// }

			if (user == null) {
				throw new BadCredentialsException("DAO: User's Information need to be updated, user " + username);
			}

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (String rol : user.getRoles()) {
				System.out.println("rol " + rol);
				authorities.add(new SimpleGrantedAuthority(rol));
			}
			logger.info(":: authorities ::" + authorities);

			// successToken = new
			// UsernamePasswordAuthenticationToken(authentication.getPrincipal().toString(),
			// authorities);
			successToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal().toString(),
					authentication.getCredentials().toString(), authorities);
			successToken.setDetails(user);
			logger.info("USER authenticated>>>>>:" + username + ":<<<<<<<<<<<<");
		} else {
			successToken = (UsernamePasswordAuthenticationToken) SessionDetail.getAuthentication();
		}
		return successToken;
	}

	public boolean supports(Class<? extends Object> authentication) {
		return true;
	}
	//
	// public UserService getUserService() {
	// return userService;
	// }
	//
	// public void setUserService(UserService userService) {
	// this.userService = userService;
	// }

}
