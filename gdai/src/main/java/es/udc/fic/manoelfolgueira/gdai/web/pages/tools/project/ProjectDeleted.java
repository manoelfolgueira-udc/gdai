package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of deleting a Project
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ProjectDeleted.java
 */
public class ProjectDeleted {
	
	@Property
	private Long groupId;
	
	Long onPassivate() {
		return groupId;
	}
	
	void onActivate(Long groupId) {
		this.groupId = groupId;
	}

}
