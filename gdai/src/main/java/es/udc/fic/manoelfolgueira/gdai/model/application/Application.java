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

@Entity
@Table(name="gdai_application")
public class Application {

	@Column(name = "applicationId")
	@SequenceGenerator( // It only takes effect for
		name = "applicationIdGenerator", // databases providing identifier
		sequenceName = "applicationSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "applicationIdGenerator")
	private Long applicationId;
	private String applicationName;
	private String applicationDescription;
	private Calendar creationDate = Calendar.getInstance();
	
	// TODO relations
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemId")
	private System system;
	

	public Application() {
	}
	
	public Application(String applicationName, String applicationDescription, Calendar creationDate, System system) {
		super();
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.creationDate = creationDate;
		this.system = system;
	}

	public Long getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public String getApplicationName() {
		return applicationName;
	}


	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}


	public String getApplicationDescription() {
		return applicationDescription;
	}


	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
	}


	public Calendar getCreationDate() {
		return creationDate;
	}
		
	public System getSystem() {
		return system;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
		return result;
	}

	
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
		return true;
	}

}
