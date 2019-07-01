package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.group;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of a Group modification
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   GroupModified.java
 */
public class GroupModified {
	
	@Property
	private Long groupId;
	
	Long onPassivate() {
		return groupId;
	}
	
	void onActivate(Long groupId) {
		this.groupId = groupId;
	}

}
