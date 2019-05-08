package es.udc.fic.manoelfolgueira.gdai.model.userstory;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;

@Entity
@Table(name="gdai_userStory")
public class UserStory {

	@Column(name = "userStoryId")
	@SequenceGenerator(name = "userStoryIdGenerator", sequenceName = "userStorySeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "userStoryIdGenerator")
	private Long userStoryId;
	private String userStoryName;
	private Calendar startDate = null;
	private Calendar endDate = null;
	private Calendar creationDate = Calendar.getInstance();
	
	@ManyToMany(mappedBy = "userStorys")
    private List<Project> projects;

	/**
	 * Empty constructor
	 */
	public UserStory() {}

	/**
	 * Main constructor
	 * @param userStoryName user story short name
	 * // TODO @param userStoryDescription a description for a userStory
	 * @param startDate when the userStory starts
	 * @param endDate when the userStory ends, in 1 or more sprints
	 * @param creationDate when it's created
	 * @param projects projects related to a userStory
	 */
	public UserStory(String userStoryName, Calendar startDate, Calendar endDate, Calendar creationDate,
			List<Project> projects) {
		super();
		this.userStoryName = userStoryName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.projects = projects;
	}

	/**
	 * @return the userStoryId
	 */
	public Long getUserStoryId() {
		return userStoryId;
	}

	/**
	 * @param userStoryId the userStoryId to set
	 */
	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	/**
	 * @return the userStoryName
	 */
	public String getUserStoryName() {
		return userStoryName;
	}

	/**
	 * @param userStoryName the userStoryName to set
	 */
	public void setUserStoryName(String userStoryName) {
		this.userStoryName = userStoryName;
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
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
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
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((userStoryId == null) ? 0 : userStoryId.hashCode());
		result = prime * result + ((userStoryName == null) ? 0 : userStoryName.hashCode());
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
		UserStory other = (UserStory) obj;
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
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (userStoryId == null) {
			if (other.userStoryId != null)
				return false;
		} else if (!userStoryId.equals(other.userStoryId))
			return false;
		if (userStoryName == null) {
			if (other.userStoryName != null)
				return false;
		} else if (!userStoryName.equals(other.userStoryName))
			return false;
		return true;
	}	

}
