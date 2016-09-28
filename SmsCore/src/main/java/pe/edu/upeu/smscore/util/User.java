package pe.edu.upeu.smscore.util;

import java.io.Serializable;

/**
 * 
 * @author ocalsin
 *
 */

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String status;
	private Long userId;

	private Long departamentId;

	private String userName;

	private String[] roles;

	private Long campaignId;
	private String campaingMessage;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(Long departamentId) {
		this.departamentId = departamentId;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCampaingMessage() {
		return campaingMessage;
	}

	public void setCampaingMessage(String campaingMessage) {
		this.campaingMessage = campaingMessage;
	}

}
