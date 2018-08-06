package es.udc.fic.manoelfolgueira.gdai.model.groupservice;

import java.util.Calendar;

public class GroupDetails {

	private String name;
	private Calendar creationTime;
	private Calendar expirationTime;
	
	public GroupDetails(String name, Calendar creationTime, Calendar expirationTime) {
		this.name = name;
		this.creationTime = creationTime;
		this.expirationTime = expirationTime;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
