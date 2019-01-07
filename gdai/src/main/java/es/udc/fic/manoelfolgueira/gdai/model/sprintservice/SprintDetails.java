package es.udc.fic.manoelfolgueira.gdai.model.sprintservice;

import java.util.Calendar;
import java.util.LinkedList;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;

public class SprintDetails {

	private String sprintName;
	private Calendar startDate = null;
	private Calendar endDate = null;
	private Calendar creationDate = Calendar.getInstance();
    private LinkedList<Project> projects;

	public SprintDetails(String sprintName, Calendar startDate, Calendar endDate, Calendar creationDate,
			LinkedList<Project> projects) {
		this.sprintName = sprintName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.projects = projects;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
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
