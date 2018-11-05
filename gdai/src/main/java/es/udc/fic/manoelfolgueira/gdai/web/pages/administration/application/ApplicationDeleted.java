package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.application;

import org.apache.tapestry5.annotations.Property;

public class ApplicationDeleted {
	
	@Property
	private Long applicationId;
	
	Long onPassivate() {
		return applicationId;
	}
	
	void onActivate(Long applicationId) {
		this.applicationId = applicationId;
	}

}
