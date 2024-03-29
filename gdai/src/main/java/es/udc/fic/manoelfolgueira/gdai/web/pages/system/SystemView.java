package es.udc.fic.manoelfolgueira.gdai.web.pages.system;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets User see any System
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SystemView.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemView {

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private SystemService systemService;

	@Property
	private SystemDetails systemDetails;

	@Inject
	private Locale locale;
	
	@Property
	private String goBackUrl;

	void onActivate(Long systemId, String goBack) throws InstanceNotFoundException {
		goBackUrl = (goBack.indexOf("tools") > -1 ? goBack.substring(goBack.indexOf("tools")) : "administration/system/management");
		systemDetails = systemService.findSystem(systemId);
	}
}