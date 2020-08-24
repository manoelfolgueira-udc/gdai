package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of deleting a System
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SystemDeleted.java
 */
public class SystemDeleted {

	@Property
	private Long systemId;

	Long onPassivate() {
		return systemId;
	}

	void onActivate(Long systemId) {
		this.systemId = systemId;
	}

}
