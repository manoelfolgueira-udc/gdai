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

	/**
	 * Main constructor
	 * @param projectName a name for this project
	 * @param projectDescription a description for this project
	 * @param creationDate when it's created
	 * @param targetDate when it should be finished
	 * @param createdBy user that has registered this project
	 * @param system system related to this project
	 * @param sprints a list of sprints when this project will take place
	 */
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
	 * @return the targetDate
	 */
	public Calendar getTargetDate() {
		return targetDate;
	}

	/**
	 * @param targetDate the targetDate to set
	 */
	public void setTargetDate(Calendar targetDate) {
		this.targetDate = targetDate;
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
	public LinkedList<Sprint> getSprints() {
		return sprints;
	}

	/**
	 * @param sprints the sprints to set
	 */
	public void setSprints(LinkedList<Sprint> sprints) {
		this.sprints = sprints;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((projectDescription == null) ? 0 : projectDescription.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((sprints == null) ? 0 : sprints.hashCode());
		result = prime * result + ((system == null) ? 0 : system.hashCode());
		result = prime * result + ((targetDate == null) ? 0 : targetDate.hashCode());
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
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (projectDescription == null) {
			if (other.projectDescription != null)
				return false;
		} else if (!projectDescription.equals(other.projectDescription))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (sprints == null) {
			if (other.sprints != null)
				return false;
		} else if (!sprints.equals(other.sprints))
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		if (targetDate == null) {
			if (other.targetDate != null)
				return false;
		} else if (!targetDate.equals(other.targetDate))
			return false;
		return true;
	}
	
}
