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

import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;

@Entity
@Table(name = "gdai_application")
public class Application {

	@Column(name = "applicationId")
	@SequenceGenerator(name = "applicationIdGenerator", sequenceName = "applicationSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "applicationIdGenerator")
	private Long applicationId;
	private String applicationName;
	private String applicationDescription;
	private Calendar creationDate = Calendar.getInstance();
	private Calendar expirationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systemId")
	private System system;

	/**
	 * Empty constructor
	 */
	public Application() {
	}

	/**
	 * Main constructor
	 * 
	 * @param applicationName
	 *            an application name
	 * @param applicationDescription
	 *            an application description
	 * @param creationDate
	 *            when the application is created
	 * @param system
	 *            the system which the application will belong to
	 */
	public Application(String applicationName, String applicationDescription, Calendar creationDate,
			Calendar expirationDate, System system) {
		super();
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.system = system;
	}

	/**
	 * Constructs an application from an ApplicationDetails
	 * 
	 * @param applicationDetails
	 */
	public Application(ApplicationDetails applicationDetails) {
		super();
		this.applicationId = applicationDetails.getApplicationId();
		this.applicationName = applicationDetails.getApplicationName();
		this.applicationDescription = applicationDetails.getApplicationDescription();
		this.creationDate = applicationDetails.getCreationDate();
		this.expirationDate = applicationDetails.getExpirationDate();
		this.system = new System(applicationDetails.getSystem());
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
	 * @return the system
	 */
	public System getSystem() {
		return system;
	}

	/**
	 * @param system
	 *            the system to set
	 */
	public void setSystem(System system) {
		this.system = system;
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
		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
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
		Application other = (Application) obj;
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
		return true;
	}

}
