package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;

public class ProjectDetails {

	private String projectName;
	private String projectDescription;
	private Calendar creationTime;
	private Calendar targetTime;
	private User createdBy;
	
	public ProjectDetails(String projectName, String projectDescription,  Calendar creationTime, Calendar targetTime, User createdBy) {
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationTime = creationTime;
		this.targetTime = targetTime;
		this.createdBy = createdBy;
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

	public Calendar getCreationTime() {
		return creationTime;
	}

	public Calendar getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(Calendar targetTime) {
		this.targetTime = targetTime;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
}
