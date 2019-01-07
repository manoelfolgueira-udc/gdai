package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.Calendar;
import java.util.LinkedList;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;

public class ProjectDetails {

	private String projectName;
	private String projectDescription;
	private Calendar creationDate;
	private Calendar targetDate;
	private User createdBy;
	private System system;
	private LinkedList<Sprint> sprints;	

	public ProjectDetails(String projectName, String projectDescription, Calendar creationDate, Calendar targetDate,
			User createdBy, System system, LinkedList<Sprint> sprints) {
		super();
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.targetDate = targetDate;
		this.createdBy = createdBy;
		this.system = system;
		this.sprints = sprints;
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
	
	public LinkedList<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(LinkedList<Sprint> sprints) {
		this.sprints = sprints;
	}
	
}
