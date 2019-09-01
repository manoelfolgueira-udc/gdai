package es.udc.fic.manoelfolgueira.gdai.model.userstoryservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;

public class UserStoryDetails {

	private String userStoryName;
	private String userStoryDescription;
	private Calendar creationDate = Calendar.getInstance();
    private User createdBy;

    /**
	 * Main constructor
	 * @param userStoryName user story short name
	 * @param userStoryDescription a description for a userStory
	 * @param creationDate when it's created
	 */
	public UserStoryDetails(String userStoryName, String userStoryDescription, Calendar creationDate, User createdBy) {
		this.userStoryName = userStoryName;
		this.userStoryDescription = userStoryDescription;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
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
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		UserStoryDetails other = (UserStoryDetails) obj;
		if (userStoryName == null) {
			if (other.userStoryName != null)
				return false;
		} else if (!userStoryName.equals(other.userStoryName))
			return false;
		return true;
	}

}
