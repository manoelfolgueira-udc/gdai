package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of deleting a user story
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserStoryDeleted.java
 */
public class UserStoryDeleted {

	@Property
	private Long UserStoryId;

	Long onPassivate() {
		return UserStoryId;
	}

	void onActivate(Long UserStoryId) {
		this.UserStoryId = UserStoryId;
	}

}
