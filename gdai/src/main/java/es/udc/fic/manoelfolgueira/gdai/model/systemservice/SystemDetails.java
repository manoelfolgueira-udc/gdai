package es.udc.fic.manoelfolgueira.gdai.model.systemservice;

import java.util.Calendar;

public class SystemDetails {

	private String systemName;
	private String systemDescription;
	private Calendar creationDate;
	
	public SystemDetails(String systemName, String systemDescription, Calendar creationDate) {
		this.systemName = systemName;
		this.systemDescription = systemDescription;
		this.creationDate = creationDate;
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
	
}
