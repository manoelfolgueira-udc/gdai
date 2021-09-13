package es.udc.fic.manoelfolgueira.gdai.model.entities.userstory;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.entities.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserStoryDetails;

@Entity
@Table(name = "gdai_userStory")
public class UserStory extends GDAIDetailsCodificable {

	@Column(name = "userStoryId")
	@SequenceGenerator(name = "userStoryIdGenerator", sequenceName = "userStorySeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "userStoryIdGenerator")
	private Long userStoryId;
	private String userStoryName;
	private String userStoryDescription;
	private Calendar creationDate = Calendar.getInstance();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdById")
	private User createdBy;

	@OneToMany(targetEntity = Project.class, mappedBy = "userStory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Project> projects;

	/**
	 * Empty constructor
	 */
	public UserStory() {
	}

	/**
	 * Main constructor
	 * 
	 * @param userStoryName
	 *            user story short name
	 * @param userStoryDescription
	 *            a description for a userStory
	 * @param creationDate
	 *            when it's created
	 * @param projects
	 *            projects related to a userStory
	 * @param createdBy
	 *            who creates the US
	 */
	public UserStory(String userStoryName, String userStoryDescription, Calendar creationDate, User createdBy) {
		this.userStoryName = userStoryName;
		this.userStoryDescription = userStoryDescription;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}

	/**
	 * @param userStoryDetails
	 */
	public UserStory(UserStoryDetails userStoryDetails) {
		if (userStoryDetails != null) {
			this.userStoryId = userStoryDetails.getUserStoryId();
			this.userStoryName = userStoryDetails.getUserStoryName();
			this.userStoryDescription = userStoryDetails.getUserStoryDescription();
			this.creationDate = userStoryDetails.getCreationDate();
			this.createdBy = new User(userStoryDetails.getCreatedBy());
		}
	}

	/**
	 * @return the userStoryId
	 */
	public Long getUserStoryId() {
		return userStoryId;
	}

	/**
	 * @param userStoryId
	 *            the userStoryId to set
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
	 * @param userStoryName
	 *            the userStoryName to set
	 */
	public void setUserStoryName(String userStoryName) {
		this.userStoryName = userStoryName;
	}

	/**
	 * @return the userStoryDescription
	 */
	public String getUserStoryDescription() {
		return userStoryDescription;
	}

	/**
	 * @param userStoryDescription
	 *            the userStoryDescription to set
	 */
	public void setUserStoryDescription(String userStoryDescription) {
		this.userStoryDescription = userStoryDescription;
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
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
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
		result = prime * result + ((userStoryId == null) ? 0 : userStoryId.hashCode());
		result = prime * result + ((userStoryName == null) ? 0 : userStoryName.hashCode());
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
		UserStory other = (UserStory) obj;
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
