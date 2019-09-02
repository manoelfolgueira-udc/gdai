package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of creating an project
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   projectDeleted.java
 */
public class ProjectCreated {
	
	@Property
	private Long projectId;
	
	Long onPassivate() {
		return projectId;
	}
	
	void onActivate(Long projectId) {
		this.projectId = projectId;
	}

}
