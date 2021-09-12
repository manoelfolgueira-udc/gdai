package es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;

@Entity
@Table(name = "gdai_projects_filter")
public class ProjectsFilter {

	@Column(name = "projectsFilterId")
	@SequenceGenerator(name = "projectsFilterIdGenerator", sequenceName = "projectsFilterSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "projectsFilterIdGenerator")
	private Long projectsFilterId;
	private String projectName;
	private String projectDescription;
	private String userStoryName;
	private String userStoryDescription;
	private Calendar projectCreationDateStart = null;
	private Calendar projectCreationDateEnd = null;
	private Long sprintId;
	private Long groupId;
	private Long systemId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdById")
	private User createdBy;
	
	public ProjectsFilter() {}

	public ProjectsFilter(Long projectsFilterId, String projectName, String projectDescription, String userStoryName,
			String userStoryDescription, Calendar projectCreationDateStart, Calendar projectCreationDateEnd,
			Long sprintId, Long groupId, Long systemId, User createdBy) {
		super();
		this.projectsFilterId = projectsFilterId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.userStoryName = userStoryName;
		this.userStoryDescription = userStoryDescription;
		this.projectCreationDateStart = projectCreationDateStart;
		this.projectCreationDateEnd = projectCreationDateEnd;
		this.sprintId = sprintId;
		this.groupId = groupId;
		this.systemId = systemId;
		this.createdBy = createdBy;
	}

	public Long getProjectsFilterId() {
		return projectsFilterId;
	}

	public void setProjectsFilterId(Long projectsFilterId) {
		this.projectsFilterId = projectsFilterId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getUserStoryName() {
		return userStoryName;
	}

	public void setUserStoryName(String userStoryName) {
		this.userStoryName = userStoryName;
	}

	public String getUserStoryDescription() {
		return userStoryDescription;
	}

	public void setUserStoryDescription(String userStoryDescription) {
		this.userStoryDescription = userStoryDescription;
	}

	public Calendar getProjectCreationDateStart() {
		return projectCreationDateStart;
	}

	public void setProjectCreationDateStart(Calendar projectCreationDateStart) {
		this.projectCreationDateStart = projectCreationDateStart;
	}

	public Calendar getProjectCreationDateEnd() {
		return projectCreationDateEnd;
	}

	public void setProjectCreationDateEnd(Calendar projectCreationDateEnd) {
		this.projectCreationDateEnd = projectCreationDateEnd;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((projectCreationDateEnd == null) ? 0 : projectCreationDateEnd.hashCode());
		result = prime * result + ((projectCreationDateStart == null) ? 0 : projectCreationDateStart.hashCode());
		result = prime * result + ((projectDescription == null) ? 0 : projectDescription.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((projectsFilterId == null) ? 0 : projectsFilterId.hashCode());
		result = prime * result + ((sprintId == null) ? 0 : sprintId.hashCode());
		result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
		result = prime * result + ((userStoryDescription == null) ? 0 : userStoryDescription.hashCode());
		result = prime * result + ((userStoryName == null) ? 0 : userStoryName.hashCode());
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
		ProjectsFilter other = (ProjectsFilter) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (projectCreationDateEnd == null) {
			if (other.projectCreationDateEnd != null)
				return false;
		} else if (!projectCreationDateEnd.equals(other.projectCreationDateEnd))
			return false;
		if (projectCreationDateStart == null) {
			if (other.projectCreationDateStart != null)
				return false;
		} else if (!projectCreationDateStart.equals(other.projectCreationDateStart))
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
		if (projectsFilterId == null) {
			if (other.projectsFilterId != null)
				return false;
		} else if (!projectsFilterId.equals(other.projectsFilterId))
			return false;
		if (sprintId == null) {
			if (other.sprintId != null)
				return false;
		} else if (!sprintId.equals(other.sprintId))
			return false;
		if (systemId == null) {
			if (other.systemId != null)
				return false;
		} else if (!systemId.equals(other.systemId))
			return false;
		if (userStoryDescription == null) {
			if (other.userStoryDescription != null)
				return false;
		} else if (!userStoryDescription.equals(other.userStoryDescription))
			return false;
		if (userStoryName == null) {
			if (other.userStoryName != null)
				return false;
		} else if (!userStoryName.equals(other.userStoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectsFilter [projectsFilterId=" + projectsFilterId + ", projectName=" + projectName
				+ ", projectDescription=" + projectDescription + ", userStoryName=" + userStoryName
				+ ", userStoryDescription=" + userStoryDescription + ", projectCreationDateStart="
				+ projectCreationDateStart + ", projectCreationDateEnd=" + projectCreationDateEnd + ", sprintId="
				+ sprintId + ", groupId=" + groupId + ", systemId=" + systemId + ", createdBy=" + createdBy + "]";
	}
	
	
		
}
