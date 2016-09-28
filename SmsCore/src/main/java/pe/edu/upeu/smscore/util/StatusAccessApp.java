package pe.edu.upeu.smscore.util;

public enum StatusAccessApp {

	STATUS_ACCESS_ACT("Activo"), STATUS_ACCESS_INACT("Inactivo");

	private String statusCode;

	private StatusAccessApp(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}

}
