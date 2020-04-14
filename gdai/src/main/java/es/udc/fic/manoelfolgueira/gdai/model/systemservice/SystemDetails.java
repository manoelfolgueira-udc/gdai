package es.udc.fic.manoelfolgueira.gdai.model.systemservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class SystemDetails extends GDAIDetailsCodificable {

	private Long systemId;
	private String systemName;
	private String systemDescription;
	private Calendar creationDate;
	private Calendar expirationDate;
	private GroupDetails groupDetails;

	/**
	 * @param systemId
	 * @param systemName
	 * @param systemDescription
	 * @param creationDate
	 * @param expirationDate
	 * @param groupDetails
	 */
	public SystemDetails(Long systemId, String systemName, String systemDescription, Calendar creationDate,
			Calendar expirationDate, GroupDetails groupDetails) {
		this.systemName = systemName;
		this.systemDescription = systemDescription;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.groupDetails = groupDetails;
	}

	public SystemDetails(System system) {
		this.systemId = system.getSystemId();
		this.systemName = system.getSystemName();
		this.systemDescription = system.getSystemDescription();
		this.creationDate = system.getCreationDate();
		this.expirationDate = system.getExpirationDate();
		this.groupDetails = new GroupDetails(system.getGroup());
	}

	/**
	 * @return the systemId
	 */
	public Long getSystemId() {
		return systemId;
	}

	/**
	 * @param systemId
	 *            the systemId to set
	 */
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	/**
	 * @param systemName
	 *            the systemName to set
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
	 * @param systemDescription
	 *            the systemDescription to set
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
	 * @return the group
	 */
	public GroupDetails getGroup() {
		return groupDetails;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(GroupDetails groupDetails) {
		this.groupDetails = groupDetails;
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
		result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
		result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
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
		SystemDetails other = (SystemDetails) obj;
		if (systemId == null) {
			if (other.systemId != null)
				return false;
		} else if (!systemId.equals(other.systemId))
			return false;
		if (systemName == null) {
			if (other.systemName != null)
				return false;
		} else if (!systemName.equals(other.systemName))
			return false;
		return true;
	}

}
