package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.sprint;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of creating an Sprint
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SprintDeleted.java
 */
public class SprintCreated {

	@Property
	private Long sprintId;

	Long onPassivate() {
		return sprintId;
	}

	void onActivate(Long sprintId) {
		this.sprintId = sprintId;
	}

}
