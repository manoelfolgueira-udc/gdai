package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.sprint;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of an Application modification
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ApplicationModified.java
 */
public class SprintModified {

	@Property
	private Long sprintId;

	Long onPassivate() {
		return sprintId;
	}

	void onActivate(Long sprintId) {
		this.sprintId = sprintId;
	}

}
