package es.udc.fic.manoelfolgueira.gdai.model.project;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;

@Entity
@Table(name = "gdai_project")
public class Project {

	@Column(name = "projectId")
	@SequenceGenerator(name = "projectIdGenerator", sequenceName = "projectSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "projectIdGenerator")
	private Long projectId;
	private String projectName;
	private String projectDescription;
	private Calendar creationDate = Calendar.getInstance();
	private String requirementsPath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdById")
	private User createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systemId")
	private System system;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "gdai_project_sprint_jt", joinColumns = {
			@JoinColumn(name = "projectId") }, inverseJoinColumns = { @JoinColumn(name = "sprintId") })
	List<Sprint> sprints;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userStoryId")
	private UserStory userStory;

	/**
	 * Empty constructor
	 */
	public Project() {
	}

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
	 * @param sprints
	 *            a list of sprints when this project will take place
	 * @param userStory
	 *            the us which this project belongs to
	 */
	public Project(String projectName, String projectDescription, Calendar creationDate, String requirementsPath,
			User createdBy, System system, List<Sprint> sprints, UserStory userStory) {

		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.requirementsPath = requirementsPath;
		this.createdBy = createdBy;
		this.system = system;
		this.sprints = sprints;
		this.userStory = userStory;
	}
		
	
	public Project(ProjectDetails projectDetails) {
		super();
		this.projectId = projectDetails.getProjectId();
		this.projectName = projectDetails.getProjectName();
		this.projectDescription = projectDetails.getProjectDescription();
		this.creationDate = projectDetails.getCreationDate();
		this.requirementsPath = projectDetails.getRequirementsPath();
		this.createdBy = new User(projectDetails.getCreatedBy());
		this.system = new System(projectDetails.getSystemDetails());
		this.sprints = new LinkedList<>();
		projectDetails.getSprintsDetails().forEach(s -> {
			this.sprints.add(new Sprint(s, this));
		});
		this.userStory = new UserStory(projectDetails.getUserStoryDetails());
	}
	
	public Project(ProjectDetails projectDetails, Sprint sprint) {
		super();
		this.projectId = projectDetails.getProjectId();
		this.projectName = projectDetails.getProjectName();
		this.projectDescription = projectDetails.getProjectDescription();
		this.creationDate = projectDetails.getCreationDate();
		this.requirementsPath = projectDetails.getRequirementsPath();
		this.createdBy = new User(projectDetails.getCreatedBy());
		this.system = new System(projectDetails.getSystemDetails());
		this.sprints = new LinkedList<>();
		this.sprints.add(sprint);
		this.userStory= new UserStory(projectDetails.getUserStoryDetails());
	}
	

	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
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
	 * @param projectName
	 *            the projectName to set
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
	 * @param projectDescription
	 *            the projectDescription to set
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
	 * @param creationDate
	 *            the creationDate to set
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
	 * @param requirementsPath
	 *            the requirementsPath to set
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
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param system
	 *            the system to set
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
	 * @param sprints
	 *            the sprints to set
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
	 * @param userStory
	 *            the userStory to set
	 */
	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Project other = (Project) obj;
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
		return true;
	}

}
