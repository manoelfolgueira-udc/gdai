package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import java.util.Date;
import java.util.List;
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
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows productionPass Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProductionPassManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProductionPassManagement {

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	@Inject
	private SystemService systemService;

	@Inject
	private ProductionPassService productionPassService;

	@Property
	private System system = null;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private Date creationDate = null;

	@Property
	private List<ProductionPass> productionPassesSearch;

	@Property
	private ProductionPass productionPass = null;

	@Inject
	private PageRenderLinkSource pageRenderLS;
	
	@Property
	private User user;
	
	void setupRender() {
		try {
			productionPassesSearch = productionPassService.findByGroup(userService.findUser(userSession.getUserId()).getGroup().getGroupId());
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
