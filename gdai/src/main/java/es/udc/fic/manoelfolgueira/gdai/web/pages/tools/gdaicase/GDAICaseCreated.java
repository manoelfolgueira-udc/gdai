package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.gdaicase;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of creating an gdaiCase
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   gdaiCaseDeleted.java
 */
public class GDAICaseCreated {
	
	@Property
	private Long gdaiCaseId;
	
	Long onPassivate() {
		return gdaiCaseId;
	}
	
	void onActivate(Long gdaiCaseId) {
		this.gdaiCaseId = gdaiCaseId;
	}

}
