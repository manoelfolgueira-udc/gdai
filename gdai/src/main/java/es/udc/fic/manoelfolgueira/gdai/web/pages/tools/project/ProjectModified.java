package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of a project modification
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectModified.java
 */
public class ProjectModified {

	@Property
	private Long projectId;

	Long onPassivate() {
		return projectId;
	}

	void onActivate(Long projectId) {
		this.projectId = projectId;
	}

}
