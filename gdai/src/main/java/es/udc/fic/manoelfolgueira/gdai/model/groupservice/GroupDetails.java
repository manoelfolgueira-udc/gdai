package es.udc.fic.manoelfolgueira.gdai.model.groupservice;

import java.util.Calendar;

public class GroupDetails {

	private String groupName;
	private Calendar creationDate;
	private Calendar expirationDate;
	
	public GroupDetails(String groupName, Calendar creationDate, Calendar expirationDate) {
		this.groupName = groupName;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Calendar getCreationDate() {
		return creationDate;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
