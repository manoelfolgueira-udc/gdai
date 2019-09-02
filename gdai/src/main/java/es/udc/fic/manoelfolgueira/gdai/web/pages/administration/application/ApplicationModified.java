package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.application;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of an Application modification
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ApplicationModified.java
 */
public class ApplicationModified {
	
	@Property
	private Long applicationId;
	
	Long onPassivate() {
		return applicationId;
	}
	
	void onActivate(Long applicationId) {
		this.applicationId = applicationId;
	}

}
