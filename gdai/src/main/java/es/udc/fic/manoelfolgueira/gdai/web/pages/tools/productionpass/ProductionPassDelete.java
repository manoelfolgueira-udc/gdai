package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPass;
import es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete a productionPass web page
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ProductionPassDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProductionPassDelete {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
	@Inject
    private Messages messages;
	
	@Inject
	private ProductionPassService productionPassService;
	
	@Property
	private String infoDeleteProductionPass;
	
	private Long productionPassId;
	
	@Property
	private ProductionPass productionPass;
	
	@Component
	private Form deleteForm;
    
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Locale locale;
	
	void setupRender() {
		infoDeleteProductionPass = messages.format("surePerformAction", messages.get("info-delete-productionPass"));
		try {
			productionPass = productionPassService.findProductionPass(productionPassId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void onActivate(Long productionPassId) {
		this.productionPassId = productionPassId;
	}
	
	Long onPassivate() {
        return productionPassId;
    }
	
	public String getDeleteProductionPassInfo() {
		return this.infoDeleteProductionPass;
	}
	
	Object onValidateFromDeleteForm() {
		try {
			productionPassService.removeProductionPass(productionPassId);
			return pageRenderLS.createPageRenderLinkWithContext("tools/productionPass/ProductionPassDeleted", productionPassId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages
                    .get("error-productionPassDoesNotExist") + productionPass.getProductionPassName());
			return null;
		}
	}
	
}
