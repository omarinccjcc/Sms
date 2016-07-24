package pe.edu.upeu.smscore.domain;

import java.io.Serializable;

/**
 * 
 * @author ocalsin
 *
 */

public class BaseEntity implements Serializable {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 0L;

	/**
	 * Defines the unique identity of an entity class
	 */
	private Long id;
//	/**
//	 * Actor who created an instance of the entity class
//	 */
//	private String createdBy;
//	/**
//	 * When the entity class was created
//	 */
//	private Date dateCreated;
//	/**
//	 * Indicates when was the last date the entity class was changed
//	 */
//	private Date dateLastUpdated;
//
//	/**
//	 * Actor who last updated an instance of the entity class
//	 */
//	private String lastUpdatedBy;
//	/**
//	 * Determines the number of times an entity class has changed over time
//	 */
//	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public Date getDateCreated() {
//		return dateCreated;
//	}
//
//	public void setDateCreated(Date dateCreated) {
//		this.dateCreated = dateCreated;
//	}
//
//	public Date getDateLastUpdated() {
//		return dateLastUpdated;
//	}
//
//	public void setDateLastUpdated(Date dateLastUpdated) {
//		this.dateLastUpdated = dateLastUpdated;
//	}
//
//	public String getLastUpdatedBy() {
//		return lastUpdatedBy;
//	}
//
//	public void setLastUpdatedBy(String lastUpdatedBy) {
//		this.lastUpdatedBy = lastUpdatedBy;
//	}
//
//	public Integer getVersion() {
//		return version;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}

	public boolean hasValidId() {
		return this.id != null && this.id.longValue() > 0;
	}

	@Override
	public String toString() {
		return this.getClass().getCanonicalName() + " : ID=" + this.id;
	}
}