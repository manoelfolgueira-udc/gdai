package es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;

public class GDAICaseDetails {

	private String gdaiCaseDescription;
	private String gdaiCaseResolution;
	private Calendar creationDate = Calendar.getInstance();
	private User createdBy;
	private System system;
	
	/**
	 * Empty constructor
	 */
	public GDAICaseDetails() {}
	
	/**
	 * Main constructor
	 * @param gdaiCaseDescription a description for this gdaiCase
	 * @param creationDate when it's created
	 * @param createdBy user that has registered this gdaiCase
	 * @param system system related to this gdaiCase
	 */
	public GDAICaseDetails(String gdaiCaseDescription, String gdaiCaseResolution, Calendar creationDate,
			User createdBy, System system) {

		this.gdaiCaseDescription = gdaiCaseDescription;
		this.gdaiCaseResolution = gdaiCaseResolution;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.system = system;
	}

	/**
	 * @return the gdaiCaseDescription
	 */
	public String getGDAICaseDescription() {
		return gdaiCaseDescription;
	}

	/**
	 * @param gdaiCaseDescription the gdaiCaseDescription to set
	 */
	public void setGDAICaseDescription(String gdaiCaseDescription) {
		this.gdaiCaseDescription = gdaiCaseDescription;
	}

	/**
	 * @return the gdaiCaseResolution
	 */
	public String getGDAICaseResolution() {
		return gdaiCaseResolution;
	}

	/**
	 * @param gdaiCaseResolution the gdaiCaseResolution to set
	 */
	public void setGDAICaseResolution(String gdaiCaseResolution) {
		this.gdaiCaseResolution = gdaiCaseResolution;
	}

	/**
	 * @return the creationDate
	 */
	public Calendar getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the system
	 */
	public System getSystem() {
		return system;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(System system) {
		this.system = system;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gdaiCaseDescription == null) ? 0 : gdaiCaseDescription.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GDAICaseDetails other = (GDAICaseDetails) obj;
		if (gdaiCaseDescription == null) {
			if (other.gdaiCaseDescription != null)
				return false;
		} else if (!gdaiCaseDescription.equals(other.gdaiCaseDescription))
			return false;
		return true;
	}
}
