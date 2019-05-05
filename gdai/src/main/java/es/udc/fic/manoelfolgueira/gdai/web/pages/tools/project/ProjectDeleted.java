package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import org.apache.tapestry5.annotations.Property;

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
