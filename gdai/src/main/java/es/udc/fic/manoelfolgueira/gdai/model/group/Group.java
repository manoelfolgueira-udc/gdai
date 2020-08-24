package es.udc.fic.manoelfolgueira.gdai.model.group;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;

@Entity
@Table(name = "gdai_group")
public class Group {

	@Column(name = "groupId")
	@SequenceGenerator(name = "groupIdGenerator", sequenceName = "groupSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "groupIdGenerator")
	private Long groupId;
	private String groupName;
	private String groupDescription;
	private Calendar creationDate = Calendar.getInstance();
	private Calendar expirationDate = null;

	@OneToMany(targetEntity = User.class, mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<User> users;

	@OneToMany(targetEntity = System.class, mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<System> systems;

	/**
	 * Empty constructor
	 */
	public Group() {
	}

	public Group(String groupName, String groupDescription, Calendar creationDate, Calendar expirationDate,
			List<User> users, List<System> systems) {
		super();
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.users = users;
		this.systems = systems;
	}

	public Group(GroupDetails groupDetails) {
		super();
		this.groupId = groupDetails.getGroupId();
		this.groupName = groupDetails.getGroupName();
		this.groupDescription = groupDetails.getGroupDescription();
		this.creationDate = groupDetails.getCreationDate();
		this.expirationDate = groupDetails.getExpirationDate();
		this.users = groupDetails.getUsers();
		this.systems = groupDetails.getSystems();
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

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @return the creationDate
	 */
	public Calendar getCreationDate() {
		return creationDate;
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
		Group other = (Group) obj;
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
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", groupDescription=" + groupDescription
				+ ", creationDate=" + creationDate + ", expirationDate=" + expirationDate + ", users=" + users
				+ ", systems=" + systems + "]";
	}

}
