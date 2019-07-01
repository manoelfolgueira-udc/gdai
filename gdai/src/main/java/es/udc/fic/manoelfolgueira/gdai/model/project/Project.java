package es.udc.fic.manoelfolgueira.gdai.model.project;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
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

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;

@Entity
@Table(name="gdai_project")
public class Project {
	
	@Column(name = "projectId")
	@SequenceGenerator(name = "projectIdGenerator", sequenceName = "projectSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "projectIdGenerator")
	private Long projectId;
	private String projectName;
	private String projectDescription;
	private Calendar creationDate = Calendar.getInstance();
	private Calendar targetDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdById")
	private User createdBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemId")
	private System system;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "project_sprint_jt", 
        joinColumns = { @JoinColumn(name = "projectId") }, 
        inverseJoinColumns = { @JoinColumn(name = "sprintId") }
    )
    List<Sprint> sprints;
	
	/**
	 * Empty constructor
	 */
	public Project() {}
	
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
	public Project(String projectName, String projectDescription, Calendar creationDate,
			Calendar targetDate, User createdBy, System system, List<Sprint> sprints) {

		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.targetDate = targetDate;
		this.createdBy = createdBy;
		this.system = system;
		this.sprints = sprints;
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
	public List<Sprint> getSprints() {
		return sprints;
	}

	/**
	 * @param sprints the sprints to set
	 */
	public void setSprints(List<Sprint> sprints) {
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
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
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
		Project other = (Project) obj;
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
