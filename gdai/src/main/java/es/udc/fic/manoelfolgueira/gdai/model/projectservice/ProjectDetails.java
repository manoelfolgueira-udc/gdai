package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.Calendar;
import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;

public class ProjectDetails {

	private String projectName;
	private String projectDescription;
	private Calendar creationDate;
	private String requirementsPath;
	private User createdBy;
	private System system;
	private List<Sprint> sprints;	
	private UserStory userStory;

	/**
	 * Main constructor
	 * @param projectName a name for this project
	 * @param projectDescription a description for this project
	 * @param creationDate when it's created
	 * @param targetDate when it should be finished
	 * @param createdBy user that has registered this project
	 * @param system system related to this project
	 * @param list a list of sprints when this project will take place
	 */
	public ProjectDetails(String projectName, String projectDescription, Calendar creationDate, String requirementsPath,
			User createdBy, System system, List<Sprint> sprints, UserStory userStory) {
		super();
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.requirementsPath = requirementsPath;
		this.createdBy = createdBy;
		this.system = system;
		this.sprints = sprints;
		this.userStory = userStory;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectDescription
	 */
	public String getProjectDescription() {
		return projectDescription;
	}

	/**
	 * @param projectDescription the projectDescription to set
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
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
	 * @return the requirementsPath
	 */
	public String getRequirementsPath() {
		return requirementsPath;
	}

	/**
	 * @param requirementsPath the requirementsPath to set
	 */
	public void setRequirementsPath(String requirementsPath) {
		this.requirementsPath = requirementsPath;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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

	/**
	 * @return the sprints
	 */
	public List<Sprint> getSprints() {
		return sprints;
	}

	/**
	 * @param sprints the sprints to set
	 */
	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}
	
	

	/**
	 * @return the userStory
	 */
	public UserStory getUserStory() {
		return userStory;
	}

	/**
	 * @param userStory the userStory to set
	 */
	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
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
		ProjectDetails other = (ProjectDetails) obj;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}

	
	
}
