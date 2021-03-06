package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of creating a User
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserDeleted.java
 */
public class UserCreated {

	@Property
	private Long userId;

	Long onPassivate() {
		return userId;
	}

	void onActivate(Long userId) {
		this.userId = userId;
	}

}
