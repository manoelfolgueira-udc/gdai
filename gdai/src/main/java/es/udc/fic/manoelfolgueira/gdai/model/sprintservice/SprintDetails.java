package es.udc.fic.manoelfolgueira.gdai.model.sprintservice;

import java.util.Calendar;
import java.util.LinkedList;

import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class SprintDetails extends GDAIDetailsCodificable {

	private Long sprintId;
	private String sprintName;
	private Calendar startDate = null;
	private Calendar endDate = null;
	private Calendar creationDate = Calendar.getInstance();
	private LinkedList<ProjectDetails> projectsDetails;

	/**
	 * Main constructor
	 * 
	 * @param sprintName
	 *            the name of the sprint
	 * @param startDate
	 *            when it starts
	 * @param endDate
	 *            when it ends
	 * @param creationDate
	 *            when it's created in GDAI
	 * @param projectsDetails
	 *            a list of projects the are performed with this sprint
	 */
	public SprintDetails(Long sprintId, String sprintName, Calendar startDate, Calendar endDate, Calendar creationDate,
			LinkedList<ProjectDetails> projectsDetails) {
		this.sprintId = sprintId;
		this.sprintName = sprintName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.projectsDetails = projectsDetails;
	}

	/**
	 * @param find
	 */
	public SprintDetails(Sprint sprint) {
		this.sprintId = sprint.getSprintId();
		this.sprintName = sprint.getSprintName();
		this.startDate = sprint.getSprintStart();
		this.endDate = sprint.getSprintEnd();
		this.creationDate = sprint.getCreationDate();
		this.projectsDetails = new LinkedList<>();
		sprint.getProjects().forEach(p -> {
			this.projectsDetails.add(new ProjectDetails(p));
		});
	}

	/**
	 * @param findSprint
	 */
	public SprintDetails(SprintDetails sprint) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the sprintId
	 */
	public Long getSprintId() {
		return sprintId;
	}

	/**
	 * @param sprintId the sprintId to set
	 */
	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	/**
	 * @return the sprintName
	 */
	public String getSprintName() {
		return sprintName;
	}

	/**
	 * @param sprintName the sprintName to set
	 */
	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
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
	 * @return the projectsDetails
	 */
	public LinkedList<ProjectDetails> getProjectsDetails() {
		return projectsDetails;
	}

	/**
	 * @param projectsDetails the projectsDetails to set
	 */
	public void setProjectsDetails(LinkedList<ProjectDetails> projectsDetails) {
		this.projectsDetails = projectsDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((projectsDetails == null) ? 0 : projectsDetails.hashCode());
		result = prime * result + ((sprintId == null) ? 0 : sprintId.hashCode());
		result = prime * result + ((sprintName == null) ? 0 : sprintName.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		SprintDetails other = (SprintDetails) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (projectsDetails == null) {
			if (other.projectsDetails != null)
				return false;
		} else if (!projectsDetails.equals(other.projectsDetails))
			return false;
		if (sprintId == null) {
			if (other.sprintId != null)
				return false;
		} else if (!sprintId.equals(other.sprintId))
			return false;
		if (sprintName == null) {
			if (other.sprintName != null)
				return false;
		} else if (!sprintName.equals(other.sprintName))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
