package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import org.apache.tapestry5.annotations.Property;

/**
 * Web page that shows the result of a productionPass modification
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProductionPassModified.java
 */
public class ProductionPassModified {

	@Property
	private Long productionPassId;

	Long onPassivate() {
		return productionPassId;
	}

	void onActivate(Long productionPassId) {
		this.productionPassId = productionPassId;
	}

}
