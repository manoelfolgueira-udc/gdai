package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import org.apache.tapestry5.annotations.Property;

public class SystemModified {
	
	@Property
	private Long systemId;
	
	Long onPassivate() {
		return systemId;
	}
	
	void onActivate(Long systemId) {
		this.systemId = systemId;
	}

}
