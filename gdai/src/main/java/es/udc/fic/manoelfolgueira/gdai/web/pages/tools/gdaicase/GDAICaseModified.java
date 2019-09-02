package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.gdaicase;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of a gdaiCase modification
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   GDAICaseModified.java
 */
public class GDAICaseModified {
	
	@Property
	private Long gdaiCaseId;
	
	Long onPassivate() {
		return gdaiCaseId;
	}
	
	void onActivate(Long gdaiCaseId) {
		this.gdaiCaseId = gdaiCaseId;
	}

}
