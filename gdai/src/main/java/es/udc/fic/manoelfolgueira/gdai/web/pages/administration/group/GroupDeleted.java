package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.group;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of deleting a Group
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GroupDeleted.java
 */
public class GroupDeleted {

	// Properties

	@Property
	private Long groupId;

	// Methods

	Long onPassivate() {
		return groupId;
	}

	void onActivate(Long groupId) {
		this.groupId = groupId;
	}

}
