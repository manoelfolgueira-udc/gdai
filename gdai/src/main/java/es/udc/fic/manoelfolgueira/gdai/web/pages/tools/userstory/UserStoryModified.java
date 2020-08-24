package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of a user story modification
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserStoryModified.java
 */
public class UserStoryModified {

	@Property
	private Long UserStoryId;

	Long onPassivate() {
		return UserStoryId;
	}

	void onActivate(Long UserStoryId) {
		this.UserStoryId = UserStoryId;
	}

}
