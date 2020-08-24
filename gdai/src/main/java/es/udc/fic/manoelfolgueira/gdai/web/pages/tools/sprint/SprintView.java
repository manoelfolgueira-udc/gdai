package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.sprint;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets User see any Sprint
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SprintView.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SprintView {

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private SprintService sprintService;

	@Property
	private SprintDetails sprintDetails;

	@Inject
	private Locale locale;

	void onActivate(Long sprintId) throws InstanceNotFoundException {

		sprintDetails = sprintService.findSprint(sprintId);

	}
}