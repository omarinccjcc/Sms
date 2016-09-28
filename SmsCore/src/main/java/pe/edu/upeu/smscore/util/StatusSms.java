package pe.edu.upeu.smscore.util;

public enum StatusSms {

	STATUS_SMS_CANCELADO("Cancelado"), STATUS_SMS_ESPERA("Espera"), STATUS_SMS_ACTIVO(
			"Activo"), STATUS_SMS_PROCESADO("Procesado");

	private String statusCode;

	private StatusSms(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}

}
