package es.udc.fic.manoelfolgueira.gdai.web.pages.application;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets User see any Application
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ApplicationView.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ApplicationView {

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private ApplicationService applicationService;

	@Property
	private ApplicationDetails applicationDetails;

	@Inject
	private Locale locale;

	void onActivate(Long applicationId) throws InstanceNotFoundException {

		applicationDetails = applicationService.findApplication(applicationId);

	}
}