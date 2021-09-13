package es.udc.fic.manoelfolgueira.gdai.model.util.dtos;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.entities.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class ProjectDetails extends GDAIDetailsCodificable {

	private Long projectId;
	private String projectName;
	private String projectDescription;
	private Calendar creationDate;
	private String requirementsPath;
	private UserDetails createdBy;
	private SystemDetails systemDetails;
	private List<SprintDetails> sprintsDetails;
	private UserStoryDetails userStoryDetails;

	/**
	 * Main constructor
	 * 
	 * @param projectName
	 *            a name for this project
	 * @param projectDescription
	 *            a description for this project
	 * @param creationDate
	 *            when it's created
	 * @param targetDate
	 *            when it should be finished
	 * @param createdBy
	 *            user that has registered this project
	 * @param system
	 *            system related to this project
	 * @param list
	 *            a list of sprints when this project will take place
	 */
	public ProjectDetails(Long projectId, String projectName, String projectDescription, Calendar creationDate, String requirementsPath,
			UserDetails createdBy, SystemDetails systemDetails, List<SprintDetails> sprintsDetails, UserStoryDetails userStoryDetails) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.requirementsPath = requirementsPath;
		this.createdBy = createdBy;
		this.systemDetails = systemDetails;
		this.sprintsDetails = sprintsDetails;
		this.userStoryDetails = userStoryDetails;
	}
	
	public ProjectDetails(Project project) {
		super();
		this.projectId = project.getProjectId();
		this.projectName = project.getProjectName();
		this.projectDescription = project.getProjectDescription();
		this.creationDate = project.getCreationDate();
		this.requirementsPath = project.getRequirementsPath();
		this.createdBy = new UserDetails(project.getCreatedBy());
		this.systemDetails = new SystemDetails(project.getSystem());
		this.sprintsDetails = new LinkedList<>();
		project.getSprints().forEach(s -> {
			this.sprintsDetails.add(new SprintDetails(s, this));
		});
		this.userStoryDetails = new UserStoryDetails(project.getUserStory());
	}
	
	public ProjectDetails(Project project, SprintDetails sprintDetails) {
		super();
		this.projectId = project.getProjectId();
		this.projectName = project.getProjectName();
		this.projectDescription = project.getProjectDescription();
		this.creationDate = project.getCreationDate();
		this.requirementsPath = project.getRequirementsPath();
		this.createdBy = new UserDetails(project.getCreatedBy());
		this.systemDetails = new SystemDetails(project.getSystem());
		this.sprintsDetails = new LinkedList<>();
		this.sprintsDetails.add(sprintDetails);
		this.userStoryDetails = new UserStoryDetails(project.getUserStory());
	}

	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
	public UserDetails getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(UserDetails createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the systemDetails
	 */
	public SystemDetails getSystemDetails() {
		return systemDetails;
	}

	/**
	 * @param systemDetails the systemDetails to set
	 */
	public void setSystemDetails(SystemDetails systemDetails) {
		this.systemDetails = systemDetails;
	}

	/**
	 * @return the sprintsDetails
	 */
	public List<SprintDetails> getSprintsDetails() {
		return sprintsDetails;
	}

	/**
	 * @param sprintsDetails the sprintsDetails to set
	 */
	public void setSprintsDetails(List<SprintDetails> sprintsDetails) {
		this.sprintsDetails = sprintsDetails;
	}

	/**
	 * @return the userStoryDetails
	 */
	public UserStoryDetails getUserStoryDetails() {
		return userStoryDetails;
	}

	/**
	 * @param userStoryDetails the userStoryDetails to set
	 */
	public void setUserStoryDetails(UserStoryDetails userStoryDetails) {
		this.userStoryDetails = userStoryDetails;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((projectDescription == null) ? 0 : projectDescription.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((requirementsPath == null) ? 0 : requirementsPath.hashCode());
		result = prime * result + ((sprintsDetails == null) ? 0 : sprintsDetails.hashCode());
		result = prime * result + ((systemDetails == null) ? 0 : systemDetails.hashCode());
		result = prime * result + ((userStoryDetails == null) ? 0 : userStoryDetails.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
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
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (requirementsPath == null) {
			if (other.requirementsPath != null)
				return false;
		} else if (!requirementsPath.equals(other.requirementsPath))
			return false;
		if (sprintsDetails == null) {
			if (other.sprintsDetails != null)
				return false;
		} else if (!sprintsDetails.equals(other.sprintsDetails))
			return false;
		if (systemDetails == null) {
			if (other.systemDetails != null)
				return false;
		} else if (!systemDetails.equals(other.systemDetails))
			return false;
		if (userStoryDetails == null) {
			if (other.userStoryDetails != null)
				return false;
		} else if (!userStoryDetails.equals(other.userStoryDetails))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectDetails [projectId=" + projectId + ", projectName=" + projectName + ", projectDescription="
				+ projectDescription + ", creationDate=" + creationDate + ", requirementsPath=" + requirementsPath
				+ ", createdBy=" + createdBy + ", systemDetails=" + systemDetails + ", sprintsDetails=" + sprintsDetails
				+ ", userStoryDetails=" + userStoryDetails + "]";
	}

}
