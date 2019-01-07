package es.udc.fic.manoelfolgueira.gdai.model.userstoryservice;

import java.util.Calendar;
import java.util.LinkedList;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;

public class UserStoryDetails {

	private String userStoryName;
	private Calendar startDate = null;
	private Calendar endDate = null;
	private Calendar creationDate = Calendar.getInstance();
    private LinkedList<Project> projects;

	public UserStoryDetails(String userStoryName, Calendar startDate, Calendar endDate, Calendar creationDate,
			LinkedList<Project> projects) {
		this.userStoryName = userStoryName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.projects = projects;
	}

	public String getUserStoryName() {
		return userStoryName;
	}

	public void setUserStoryName(String userStoryName) {
		this.userStoryName = userStoryName;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public LinkedList<Project> getProjects() {
		return projects;
	}

	public void setProjects(LinkedList<Project> projects) {
		this.projects = projects;
	}
	
	public void addProject(Project project) {
		this.projects.add(project);
	}
	
	public void removeProject(Project project) {
		this.projects.remove(project);
	}

	public Calendar getCreationDate() {
		return creationDate;
	}
	
}
