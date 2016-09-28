package pe.edu.upeu.smscore.util;

public enum StatusCampaign {

	STATUS_CAMPAIGN_ACT("Activo"), STATUS_CAMPAIGN_INACT("Inactivo");

	private String statusCode;

	private StatusCampaign(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
}
