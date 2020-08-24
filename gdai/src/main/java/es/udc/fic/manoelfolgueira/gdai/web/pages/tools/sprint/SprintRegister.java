package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.sprint;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Sprints
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SprintRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SprintRegister {

	@Property
	private String sprintName;

	@Component(id = "sprintName")
	private TextField sprintNameField;

	@Property
	private Date sprintStart;

	@Property
	private Date sprintEnd;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private SprintService sprintService;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private String result = null;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	Object onValidateFromRegistrationForm() {

		SprintDetails sprintDetails;

		if (sprintStart.after(sprintEnd)) {
			registrationForm.recordError(messages.get("SprintStartLEQSprintEnd"));
		}

		if (!registrationForm.isValid()) {
			return null;
		}

		try {

			Calendar calSprintStart = Calendar.getInstance();
			if (sprintStart != null)
				calSprintStart.setTime(sprintStart);
			else
				calSprintStart = null;

			Calendar calSprintEnd = Calendar.getInstance();
			if (sprintEnd != null)
				calSprintEnd.setTime(sprintEnd);
			else
				calSprintEnd = null;

			Calendar calCreationDate = Calendar.getInstance();
			sprintDetails = sprintService.registerSprint(sprintName,
					new SprintDetails(null, sprintName, calSprintStart, calSprintEnd, calCreationDate, null));
			result = messages.getFormatter("result-SprintRegister-ok").format(sprintName);
			return pageRenderLS.createPageRenderLinkWithContext("tools/sprint/SprintCreated", sprintDetails.getSprintId());
		} catch (DuplicateInstanceException e) {
			registrationForm.recordError(sprintNameField, messages.get("error-sprintNameAlreadyExists"));
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}
		return null;

	}

	String onPassivate() {
		return result;
	}
}
