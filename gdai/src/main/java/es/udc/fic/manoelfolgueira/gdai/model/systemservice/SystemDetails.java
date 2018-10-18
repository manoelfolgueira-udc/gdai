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
	
	public String getSystemName() {
		return systemName;
	}
	
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
