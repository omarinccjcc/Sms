package pe.edu.upeu.smscore.domain;

import java.io.Serializable;

public class Person extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastNameF;
	private String lastNameM;
	private String numPhone;
	private String message;
	private String statusPerson;

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

	public String getNumPhone() {
		return numPhone;
	}

	public void setNumPhone(String numPhone) {
		this.numPhone = numPhone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusPerson() {
		return statusPerson;
	}

	public void setStatusPerson(String statusPerson) {
		this.statusPerson = statusPerson;
	}

}
