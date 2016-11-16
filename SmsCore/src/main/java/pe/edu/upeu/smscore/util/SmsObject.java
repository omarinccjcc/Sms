package pe.edu.upeu.smscore.util;

import java.io.Serializable;

public class SmsObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String numPhone;
	private String message;

	public SmsObject(Long id, String numPhone, String message) {
		this.numPhone = numPhone;
		this.message = message;
		this.id = id;
	}

	/***
	This is construct
	*/
	public SmsObject() {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
