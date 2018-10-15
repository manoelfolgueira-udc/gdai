package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;

public class ProjectDetails {

	private String projectName;
	private String projectDescription;
	private Calendar creationDate;
	private Calendar targetDate;
	private User createdBy;
	private System system;
	
	public ProjectDetails(String projectName, String projectDescription,  Calendar creationDate, Calendar targetDate, User createdBy, System system) {
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.targetDate = targetDate;
		this.createdBy = createdBy;
		this.system = system;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public Calendar getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Calendar targetDate) {
		this.targetDate = targetDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}
	
}
