package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.application;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Applications
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ApplicationRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ApplicationRegister {

	// The activation context
	private Long systemId;

	@Property
	private String applicationName;

	@Property
	private Date expirationDate;

	@Component(id = "applicationName")
	private TextField applicationNameField;

	@Property
	private String applicationDescription;

	@Component(id = "applicationDescription")
	private TextField applicationDescriptionField;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private ApplicationService applicationService;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private SystemService systemService;

	@Property
	private String result = null;

	@Property
	private SystemDetails systemDetails;

	@Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	Object onValidateFromRegistrationForm() {

		ApplicationDetails applicationDetails;

		if (!registrationForm.isValid()) {
			return null;
		}

		try {
			Calendar calCreationDate = Calendar.getInstance();

			Calendar calExpirationDate = Calendar.getInstance();
			calExpirationDate.setTime(expirationDate);

			applicationDetails = applicationService.registerApplication(new ApplicationDetails(null, applicationName,
					applicationDescription, calCreationDate, calExpirationDate, systemDetails));
			result = messages.getFormatter("result-ApplicationRegister-ok").format(applicationName);
			return pageRenderLS.createPageRenderLinkWithContext("administration/application/ApplicationCreated",
					applicationDetails.getApplicationId());
		} catch (DuplicateInstanceException e) {
			registrationForm.recordError(applicationNameField, messages.get("error-applicationNameAlreadyExists"));
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}
		return null;

	}

	Object onSuccess() {
		return ApplicationManagement.class;
	}

	String onPassivate() {
		return result;
	}

	public SystemEncoder getSystemEncoder() {
		return new SystemEncoder(systemService);
	}

	void onPrepare() {

		List<SystemDetails> systemsDetails = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			systemDetails = findSystemInList(systemId, systemsDetails);
		}

		systemsModel = selectModelFactory.create(systemsDetails, "systemName");
	}

	private SystemDetails findSystemInList(Long systemId, List<SystemDetails> systemsDetails) {
		for (SystemDetails s : systemsDetails) {
			if (s.getSystemId().equals(systemId)) {
				return s;
			}
		}
		return null;
	}

}
