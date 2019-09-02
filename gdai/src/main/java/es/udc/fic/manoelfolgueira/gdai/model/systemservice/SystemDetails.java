package es.udc.fic.manoelfolgueira.gdai.model.systemservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;

public class SystemDetails {

	private String systemName;
	private String systemDescription;
	private Calendar creationDate;
	private Group group;
	
	public SystemDetails(String systemName, String systemDescription, Calendar creationDate, Group group) {
		this.systemName = systemName;
		this.systemDescription = systemDescription;
		this.creationDate = creationDate;
		this.group = group;
	}

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	/**
	 * @param systemName the systemName to set
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * @return the systemDescription
	 */
	public String getSystemDescription() {
		return systemDescription;
	}

	/**
	 * @param systemDescription the systemDescription to set
	 */
	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
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
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((systemDescription == null) ? 0 : systemDescription.hashCode());
		result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
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
		SystemDetails other = (SystemDetails) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (systemDescription == null) {
			if (other.systemDescription != null)
				return false;
		} else if (!systemDescription.equals(other.systemDescription))
			return false;
		if (systemName == null) {
			if (other.systemName != null)
				return false;
		} else if (!systemName.equals(other.systemName))
			return false;
		return true;
	}
	
}
