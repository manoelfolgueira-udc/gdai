package es.udc.fic.manoelfolgueira.gdai.model.groupservice;

import java.util.Calendar;

public class GroupDetails {

	private String groupName;
	private Calendar creationTime;
	private Calendar expirationTime;
	
	public GroupDetails(String groupName, Calendar creationTime, Calendar expirationTime) {
		this.groupName = groupName;
		this.creationTime = creationTime;
		this.expirationTime = expirationTime;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Calendar getCreationTime() {
		return creationTime;
	}

	public Calendar getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Calendar expirationTime) {
		this.expirationTime = expirationTime;
	}
	
}
