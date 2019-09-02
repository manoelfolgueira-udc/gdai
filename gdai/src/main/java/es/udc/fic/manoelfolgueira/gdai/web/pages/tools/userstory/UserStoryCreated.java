package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of creating an userStory
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   userStoryDeleted.java
 */
public class UserStoryCreated {
	
	@Property
	private Long userStoryId;
	
	Long onPassivate() {
		return userStoryId;
	}
	
	void onActivate(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

}
