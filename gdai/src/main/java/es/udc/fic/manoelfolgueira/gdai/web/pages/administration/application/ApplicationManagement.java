package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.application;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allows Application Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ApplicationManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ApplicationManagement {

	@Property
	private ApplicationDetails applicationDetails;

	@Property
	private String applicationName;

	@Property
	private String applicationDescription;

	@Property
	private String creationDate;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private ApplicationService applicationService;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	List<ApplicationDetails> applicationsDetails;

	void setupRender() {
		// A GridDataSource is not provided due to the little ammount of applications
		// which are going to be in the app at a time
		applicationsDetails = applicationService.findAllOrderedByApplicationName();
	}

	public String getApplicationCreationDateFormatted() {

		return (applicationDetails.getCreationDate() != null)
				? Utils.getFormattedDate(applicationDetails.getCreationDate().getTime(), locale)
				: "";
	}

}
