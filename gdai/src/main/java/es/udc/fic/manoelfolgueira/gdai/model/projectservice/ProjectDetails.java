package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryDetails;
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
		this.projectName = project.getProjectName();
		this.projectDescription = project.getProjectDescription();
		this.creationDate = project.getCreationDate();
		this.requirementsPath = project.getRequirementsPath();
		this.createdBy = new UserDetails(project.getCreatedBy());
		this.systemDetails = new SystemDetails(project.getSystem());
		this.sprintsDetails = new LinkedList<>();
		project.getSprints().forEach(s -> {
			this.sprintsDetails.add(new SprintDetails(s));
		});
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
	
		

}
