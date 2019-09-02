package es.udc.fic.manoelfolgueira.gdai.model.applicationservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;

/**
 * DTO for the Application Entity
 */
public class ApplicationDetails {

	private String applicationName;
	private String applicationDescription;
	private Calendar creationDate;
	private System system;
	
	/**
	 * Main Constructor
	 * @param applicationName application name
	 * @param applicationDescription application description
	 * @param creationDate when it's created
	 * @param system to which this application belongs to
	 */
	public ApplicationDetails(String applicationName, String applicationDescription, Calendar creationDate, System system) {
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.creationDate = creationDate;
		this.system = system;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return the applicationDescription
	 */
	public String getApplicationDescription() {
		return applicationDescription;
	}

	/**
	 * @param applicationDescription the applicationDescription to set
	 */
	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
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
		result = prime * result + ((applicationDescription == null) ? 0 : applicationDescription.hashCode());
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((system == null) ? 0 : system.hashCode());
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
		ApplicationDetails other = (ApplicationDetails) obj;
		if (applicationDescription == null) {
			if (other.applicationDescription != null)
				return false;
		} else if (!applicationDescription.equals(other.applicationDescription))
			return false;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		return true;
	}
	
}
