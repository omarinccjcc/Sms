package pe.edu.upeu.smscore.domain;

import java.io.Serializable;

import pe.edu.upeu.smscore.util.CommonUtils;

public class Departament extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String areaName;
	private String description;
	private String statusDepartament;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusDepartament() {
		return statusDepartament;
	}

	public void setStatusDepartament(String statusDepartament) {
		this.statusDepartament = statusDepartament;
	}

	public String getDateCreatedString() {
		return CommonUtils.dateToString(super.getDateCreated(), "yyyy-MM-dd");
	}

	public void setDateCreatedString(String dateCaculationsString) {
		super.setDateCreated(CommonUtils.stringToDate(dateCaculationsString, "yyyy-MM-dd"));
	}
}
