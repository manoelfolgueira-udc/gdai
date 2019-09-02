package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of creating an productionPass
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   productionPassDeleted.java
 */
public class ProductionPassCreated {
	
	@Property
	private Long productionPassId;
	
	Long onPassivate() {
		return productionPassId;
	}
	
	void onActivate(Long productionPassId) {
		this.productionPassId = productionPassId;
	}

}
