package es.udc.fic.manoelfolgueira.gdai.model.util.dtos;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.entities.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class SprintDetails extends GDAIDetailsCodificable {

	private Long sprintId;
	private String sprintName;
	private Calendar startDate = null;
	private Calendar endDate = null;
	private Calendar creationDate = Calendar.getInstance();
	private List<ProjectDetails> projectsDetails;

	/**
	 * Main constructor
	 * 
	 * @param sprintName      the name of the sprint
	 * @param startDate       when it starts
	 * @param endDate         when it ends
	 * @param creationDate    when it's created in GDAI
	 * @param projectsDetails a list of projects the are performed with this sprint
	 */
	public SprintDetails(Long sprintId, String sprintName, Calendar startDate, Calendar endDate, Calendar creationDate,
			List<ProjectDetails> projectsDetails) {
		super();
		this.sprintId = sprintId;
		this.sprintName = sprintName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.projectsDetails = projectsDetails;
	}

	/**
	 * @param sprint
	 */
	public SprintDetails(Sprint sprint) {
		super();
		this.sprintId = sprint.getSprintId();
		this.sprintName = sprint.getSprintName();
		this.startDate = sprint.getSprintStart();
		this.endDate = sprint.getSprintEnd();
		this.creationDate = sprint.getCreationDate();
		this.projectsDetails = new LinkedList<ProjectDetails>();
		sprint.getProjects().forEach(p -> {
			this.projectsDetails.add(new ProjectDetails(p, this));
		});
	}
	
	/**
	 * @param sprint
	 */
	public SprintDetails(Sprint sprint, ProjectDetails projectDetails) {
		super();
		this.sprintId = sprint.getSprintId();
		this.sprintName = sprint.getSprintName();
		this.startDate = sprint.getSprintStart();
		this.endDate = sprint.getSprintEnd();
		this.creationDate = sprint.getCreationDate();
		this.projectsDetails = new LinkedList<ProjectDetails>();
		this.projectsDetails.add(projectDetails);
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
	public List<ProjectDetails> getProjectsDetails() {
		return projectsDetails;
	}

	/**
	 * @param projectsDetails the projectsDetails to set
	 */
	public void setProjectsDetails(List<ProjectDetails> projectsDetails) {
		this.projectsDetails = projectsDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sprintId == null) ? 0 : sprintId.hashCode());
		result = prime * result + ((sprintName == null) ? 0 : sprintName.hashCode());
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
		SprintDetails other = (SprintDetails) obj;
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
		return true;
	}

	

}
