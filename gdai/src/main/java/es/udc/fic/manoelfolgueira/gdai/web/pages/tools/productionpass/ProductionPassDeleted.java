package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of deleting a productionPass
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ProductionPassDeleted.java
 */
public class ProductionPassDeleted {
	
	@Property
	private Long productionPassId;
	
	Long onPassivate() {
		return productionPassId;
	}
	
	void onActivate(Long productionPassId) {
		this.productionPassId = productionPassId;
	}

}
