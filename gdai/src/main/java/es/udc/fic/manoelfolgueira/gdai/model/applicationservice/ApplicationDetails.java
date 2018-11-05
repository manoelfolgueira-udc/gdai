package es.udc.fic.manoelfolgueira.gdai.model.applicationservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;

public class ApplicationDetails {

	private String applicationName;
	private String applicationDescription;
	private Calendar creationDate;
	private System system;
	
	public ApplicationDetails(String applicationName, String applicationDescription, Calendar creationDate, System system) {
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.creationDate = creationDate;
		this.system = system;
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

	public void setSystem(System system) {
		this.system = system;
	}
	
}
