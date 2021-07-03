package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allows System Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SystemManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemManagement {

	@Property
	private SystemDetails systemDetails;

	@Property
	private String systemName;

	@Property
	private String systemDescription;

	@Property
	private String creationDate;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private SystemService systemService;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	List<SystemDetails> systemsDetails;

	void setupRender() {
		// A GridDataSource is not provided due to the little ammount of systems which
		// are going to be in the app at a time
		systemsDetails = systemService.findAllOrderedBySystemName();
	}

	public String getSystemCreationDateFormatted() {

		return (systemDetails.getCreationDate() != null)
				? Utils.getFormattedDate(systemDetails.getCreationDate().getTime(), locale)
				: "";
	}

}
