package es.udc.fic.manoelfolgueira.gdai.model.userstoryservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class UserStoryDetails extends GDAIDetailsCodificable {

	private Long userStoryId;
	private String userStoryName;
	private String userStoryDescription;
	private Calendar creationDate = Calendar.getInstance();
	private UserDetails createdBy;
	/**
	 * @param userStoryId
	 * @param userStoryName
	 * @param userStoryDescription
	 * @param creationDate
	 * @param createdBy
	 */
	public UserStoryDetails(Long userStoryId, String userStoryName, String userStoryDescription, Calendar creationDate,
			UserDetails createdBy) {
		super();
		this.userStoryId = userStoryId;
		this.userStoryName = userStoryName;
		this.userStoryDescription = userStoryDescription;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}
	/**
	 * @param userStory
	 */
	public UserStoryDetails(UserStory userStory) {
		this.userStoryId = userStory.getUserStoryId();
		this.userStoryName = userStory.getUserStoryName();
		this.userStoryDescription = userStory.getUserStoryDescription();
		this.creationDate = userStory.getCreationDate();
		this.createdBy = new UserDetails(userStory.getCreatedBy());
	}
	/**
	 * @return the userStoryid
	 */
	public Long getUserStoryId() {
		return userStoryId;
	}
	/**
	 * @param userStoryid the userStoryid to set
	 */
	public void setUserStoryid(Long userStoryid) {
		this.userStoryId = userStoryid;
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
	 * @return the userStoryDescription
	 */
	public String getUserStoryDescription() {
		return userStoryDescription;
	}
	/**
	 * @param userStoryDescription the userStoryDescription to set
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
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((userStoryDescription == null) ? 0 : userStoryDescription.hashCode());
		result = prime * result + ((userStoryName == null) ? 0 : userStoryName.hashCode());
		result = prime * result + ((userStoryId == null) ? 0 : userStoryId.hashCode());
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
		UserStoryDetails other = (UserStoryDetails) obj;
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
		if (userStoryId == null) {
			if (other.userStoryId != null)
				return false;
		} else if (!userStoryId.equals(other.userStoryId))
			return false;
		return true;
	}

	
}
