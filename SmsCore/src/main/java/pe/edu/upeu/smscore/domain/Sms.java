package pe.edu.upeu.smscore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.edu.upeu.smscore.util.SmsFields;

public class Sms extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nameAll;
	private String numPhone;
	private String textA;
	private String textB;

	private String dateA;
	private String dateB;
	private BigDecimal amountA;
	private BigDecimal amountB;
	private String message;
	private Date dateProccess;
	private Date dateCreated;
	private String createdBy;
	private String statusSms;
	private Long campaignId;
	private Long departamentId;

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	public String getNumPhone() {
		return numPhone;
	}

	public void setNumPhone(String numPhone) {
		this.numPhone = numPhone;
	}

	public String getTextA() {
		return textA;
	}

	public void setTextA(String textA) {
		this.textA = textA;
	}

	public String getTextB() {
		return textB;
	}

	public void setTextB(String textB) {
		this.textB = textB;
	}

	public String getDateA() {
		return dateA;
	}

	public void setDateA(String dateA) {
		this.dateA = dateA;
	}

	public String getDateB() {
		return dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public BigDecimal getAmountA() {
		return amountA;
	}

	public void setAmountA(BigDecimal amountA) {
		this.amountA = amountA;
	}

	public BigDecimal getAmountB() {
		return amountB;
	}

	public void setAmountB(BigDecimal amountB) {
		this.amountB = amountB;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateProccess() {
		return dateProccess;
	}

	public void setDateProccess(Date dateProccess) {
		this.dateProccess = dateProccess;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatusSms() {
		return statusSms;
	}

	public void setStatusSms(String statusSms) {
		this.statusSms = statusSms;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public Long getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(Long departamentId) {
		this.departamentId = departamentId;
	}

	public String getMethodInformation(String method) {
		String information = "";
		if (SmsFields.textA.toString().equals(method)) {
			information = this.textA;
		}
		if (SmsFields.textB.toString().equals(method)) {
			information = this.textB;
		}
		if (SmsFields.dateA.toString().equals(method)) {
			information = this.dateA;
		}
		if (SmsFields.dateB.toString().equals(method)) {
			information = this.dateB;
		}
		if (SmsFields.amountA.toString().equals(method)) {
			information = this.amountA.toString();
		}
		if (SmsFields.amountB.toString().equals(method)) {
			information = this.amountB.toString();
		}
		return information;
	}

}
