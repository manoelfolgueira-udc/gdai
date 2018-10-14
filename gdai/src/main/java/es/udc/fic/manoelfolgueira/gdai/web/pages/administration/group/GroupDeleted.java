package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.group;

import org.apache.tapestry5.annotations.Property;

public class GroupDeleted {
	
	@Property
	private Long groupId;
	
	Long onPassivate() {
		return groupId;
	}
	
	void onActivate(Long groupId) {
		this.groupId = groupId;
	}

}
