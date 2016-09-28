package pe.edu.upeu.smscore.util;

public enum StatusDepartament {

	STATUS_DEPARTAMENT_ACT("Activo"), STATUS_DEPARTAMENT_INACT("Inactivo");

	private String statusCode;

	private StatusDepartament(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}
}
