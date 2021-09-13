package es.udc.fic.manoelfolgueira.gdai.model.util.dtos;

import java.util.Calendar;
import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.entities.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.entities.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

/**
 * DTO for the Group Entity
 */
public class GroupDetails extends GDAIDetailsCodificable {

	private Long groupId;
	private String groupName;
	private String groupDescription;
	private Calendar creationDate;
	private Calendar expirationDate;
	private List<User> users;
	private List<System> systems;

	/**
	 * @param groupId
	 * @param groupName
	 * @param groupDescription
	 * @param creationDate
	 * @param expirationDate
	 * @param users
	 * @param systems
	 */
	public GroupDetails(Long groupId, String groupName, String groupDescription, Calendar creationDate,
			Calendar expirationDate, List<User> users, List<System> systems) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.users = users;
		this.systems = systems;
	}

	public GroupDetails(Group group) {
		super();
		this.groupId = group.getGroupId();
		this.groupName = group.getGroupName();
		this.groupDescription = group.getGroupDescription();
		this.creationDate = group.getCreationDate();
		this.expirationDate = group.getExpirationDate();
		this.users = group.getUsers();
		this.systems = group.getSystems();
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the groupDescription
	 */
	public String getGroupDescription() {
		return groupDescription;
	}

	/**
	 * @param groupDescription
	 *            the groupDescription to set
	 */
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
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
	 * @return the expirationDate
	 */
	public Calendar getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the systems
	 */
	public List<System> getSystems() {
		return systems;
	}

	/**
	 * @param systems
	 *            the systems to set
	 */
	public void setSystems(List<System> systems) {
		this.systems = systems;
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
		result = prime * result + ((groupDescription == null) ? 0 : groupDescription.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
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
		GroupDetails other = (GroupDetails) obj;
		if (groupDescription == null) {
			if (other.groupDescription != null)
				return false;
		} else if (!groupDescription.equals(other.groupDescription))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupDetails [groupId=" + groupId + ", groupName=" + groupName + ", groupDescription="
				+ groupDescription + ", creationDate=" + creationDate + ", expirationDate=" + expirationDate
				+ ", users=" + users + ", systems=" + systems + "]";
	}

}
