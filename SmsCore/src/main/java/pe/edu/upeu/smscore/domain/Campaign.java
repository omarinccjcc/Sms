package pe.edu.upeu.smscore.domain;

import java.io.Serializable;

public class Campaign extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String campaingName;
	private String description;
	private String message;
	private String statusCampaign;
	private Long departamentId;

	public String getCampaingName() {
		return campaingName;
	}

	public void setCampaingName(String campaingName) {
		this.campaingName = campaingName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCampaign() {
		return statusCampaign;
	}

	public void setStatusCampaign(String statusCampaign) {
		this.statusCampaign = statusCampaign;
	}

	public Long getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(Long departamentId) {
		this.departamentId = departamentId;
	}

}
