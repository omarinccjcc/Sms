package pe.edu.upeu.smscore.domain;

import java.io.Serializable;

public class Role extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleName;
	private String description;
	private String url;
	private String statusRole;
	private String check;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatusRole() {
		return statusRole;
	}

	public void setStatusRole(String statusRole) {
		this.statusRole = statusRole;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

}
