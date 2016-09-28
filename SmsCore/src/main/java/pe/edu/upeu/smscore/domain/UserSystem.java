package pe.edu.upeu.smscore.domain;

import java.io.Serializable;
import java.util.Date;

import pe.edu.upeu.smscore.util.CommonUtils;

public class UserSystem extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String passwordConfirmation;
	private Date dateIni;
	private Date dateEnd;
	private String firstName;
	private String lastNameF;
	private String lastNameM;
	private String statusUser;
	private Long departamentId;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateIni() {
		return dateIni;
	}

	public void setDateIni(Date dateIni) {
		this.dateIni = dateIni;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNameF() {
		return lastNameF;
	}

	public void setLastNameF(String lastNameF) {
		this.lastNameF = lastNameF;
	}

	public String getLastNameM() {
		return lastNameM;
	}

	public void setLastNameM(String lastNameM) {
		this.lastNameM = lastNameM;
	}

	public String getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(String statusUser) {
		this.statusUser = statusUser;
	}

	public String getAllName() {
		return this.firstName + " " + this.lastNameF + " " + this.lastNameM;
	}

	public String getDateIniString() {
		return CommonUtils.dateToString(dateIni, "dd/MM/yyyy");
	}

	public void setDateIniString(String dateIniString) {
		this.dateIni = CommonUtils.stringToDate(dateIniString, "dd/MM/yyyy");
	}

	public String getDateEndString() {
		return CommonUtils.dateToString(dateEnd, "dd/MM/yyyy");
	}

	public void setDateEndString(String dateEndString) {
		this.dateEnd = CommonUtils.stringToDate(dateEndString, "dd/MM/yyyy");

	}

	public String getDateCreatedString() {
		return CommonUtils.dateToString(super.getDateCreated(), "dd/MM/yyyy");
	}

	public void setDateCreatedString(String dateCreatedString) {
		super.setDateCreated(CommonUtils.stringToDate(dateCreatedString, "dd/MM/yyyy"));
	}

	public Long getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(Long departamentId) {
		this.departamentId = departamentId;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	
	
}
