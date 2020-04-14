package es.udc.fic.manoelfolgueira.gdai.model.applicationservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.application.Application;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

/**
 * DTO for the Application Entity
 */
public class ApplicationDetails extends GDAIDetailsCodificable {

	private Long applicationId;
	private String applicationName;
	private String applicationDescription;
	private Calendar creationDate;
	private Calendar expirationDate;
	private SystemDetails systemDetails;

	/**
	 * Main Constructor
	 * 
	 * @param applicationName
	 *            application name
	 * @param applicationDescription
	 *            application description
	 * @param creationDate
	 *            when it's created
	 * @param SystemDetails
	 *            to which this application belongs to
	 */
	public ApplicationDetails(Long applicationId, String applicationName, String applicationDescription,
			Calendar creationDate, Calendar expirationDate, SystemDetails systemDetails) {
		super();
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.systemDetails = systemDetails;
	}

	/**
	 * Builds an ApplicationDetails from an Application
	 * 
	 * @param application
	 */
	public ApplicationDetails(Application application) {
		super();
		this.applicationId = application.getApplicationId();
		this.applicationName = application.getApplicationName();
		this.applicationDescription = application.getApplicationDescription();
		this.creationDate = application.getCreationDate();
		this.expirationDate = application.getExpirationDate();
		this.systemDetails = new SystemDetails(application.getSystem());
	}

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @param applicationName
	 *            the applicationName to set
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
	 * @param applicationDescription
	 *            the applicationDescription to set
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
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the expirationDate
	 */
	public Calendar getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the SystemDetails
	 */
	public SystemDetails getSystem() {
		return systemDetails;
	}

	/**
	 * @param SystemDetails
	 *            the SystemDetails to set
	 */
	public void setSystem(SystemDetails SystemDetails) {
		this.systemDetails = SystemDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		return true;
	}

}
