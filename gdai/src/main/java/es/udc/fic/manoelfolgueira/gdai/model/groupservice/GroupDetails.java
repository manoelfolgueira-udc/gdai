package es.udc.fic.manoelfolgueira.gdai.model.groupservice;

import java.util.Calendar;

/**
 * DTO for the Group Entity
 */
public class GroupDetails {

	private String groupName;
	private Calendar creationDate;
	private Calendar expirationDate;
	
	/**
	 * Main Constructor
	 * @param groupName group name
	 * @param creationDate when it's created
	 * @param expirationDate when the Group Entity will expire
	 */
	public GroupDetails(String groupName, Calendar creationDate, Calendar expirationDate) {
		this.groupName = groupName;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
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
	
	
	
}
