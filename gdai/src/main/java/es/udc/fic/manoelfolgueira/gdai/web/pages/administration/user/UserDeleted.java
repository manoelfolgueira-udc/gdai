package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import org.apache.tapestry5.annotations.Property;

public class UserDeleted {
	
	@Property
	private Long userId;
	
	Long onPassivate() {
		return userId;
	}
	
	void onActivate(Long userId) {
		this.userId = userId;
	}

}
