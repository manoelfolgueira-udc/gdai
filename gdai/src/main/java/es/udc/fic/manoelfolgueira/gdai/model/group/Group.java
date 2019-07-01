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

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;

@Entity
@Table(name="gdai_group")
public class Group {

	@Column(name = "groupId")
	@SequenceGenerator(name = "groupIdGenerator", sequenceName = "groupSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "groupIdGenerator")
	private Long groupId;
	private String groupName;
	private Calendar creationDate = Calendar.getInstance();
	private Calendar expirationDate = null;
	
	@OneToMany(targetEntity=User.class, mappedBy="group", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<User> users;
	
	@OneToMany(targetEntity=System.class, mappedBy="group", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<System> systems;

	
	/**
	 * Empty constructor
	 */
	public Group() {}

	/**
	 * Main constructor
	 * @param groupName the group name
	 * @param users a collection (list) of users which belong to a the group that is being created
	 * @param expirationDate when the group expires
	 * @param system
	 */
	public Group(String groupName, List<User> users, Calendar expirationDate, List<System> systems) {
		this.groupName = groupName;
		this.users = users;
		this.expirationDate = expirationDate;
		this.systems = systems;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
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
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	 * @return the expirationDate
	 */
	public Calendar getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
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
	 * @param users the users to set
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
	 * @param systems the systems to set
	 */
	public void setSystems(List<System> systems) {
		this.systems = systems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((systems == null) ? 0 : systems.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Group other = (Group) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
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
		if (systems == null) {
			if (other.systems != null)
				return false;
		} else if (!systems.equals(other.systems))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	
}
