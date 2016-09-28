package pe.edu.upeu.smscore.util;

public enum StatusUser {

	STATUS_ACCESS_ACT("Activo"), STATUS_ACCESS_INACT("Inactivo");

	private String statusCode;

	private StatusUser(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}

}
