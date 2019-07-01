package es.udc.fic.manoelfolgueira.gdai.model.application;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAICodificable;

@Entity
@Table(name="gdai_application")
public class Application extends GDAICodificable {

	@Column(name = "applicationId")
	@SequenceGenerator(name = "applicationIdGenerator", sequenceName = "applicationSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "applicationIdGenerator")
	private Long applicationId;
	private String applicationName;
	private String applicationDescription;
	private Calendar creationDate = Calendar.getInstance();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemId")
	private System system;
	

	/**
	 * Empty constructor
	 */
	public Application() {}
	
	/**
	 * Main constructor
	 * @param applicationName an application name
	 * @param applicationDescription an application description
	 * @param creationDate when the application is created
	 * @param system the system which the application will belong to
	 */
	public Application(String applicationName, String applicationDescription, Calendar creationDate, System system) {
		super();
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.creationDate = creationDate;
		this.system = system;
	}

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationDescription == null) ? 0 : applicationDescription.hashCode());
		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
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
		Application other = (Application) obj;
		if (applicationDescription == null) {
			if (other.applicationDescription != null)
				return false;
		} else if (!applicationDescription.equals(other.applicationDescription))
			return false;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
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
