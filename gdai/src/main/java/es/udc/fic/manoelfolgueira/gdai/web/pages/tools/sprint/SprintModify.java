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
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify an Sprint
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SprintModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SprintModify {

	@Property
	private Long sprintId;

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

	@Property
	private SprintDetails sprintDetails;

	Object onValidateFromRegistrationForm() {

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
			sprintService.updateSprintDetails(sprintId,
					new SprintDetails(sprintDetails.getSprintId(), sprintName, calSprintStart, calSprintEnd, calCreationDate, null));
			result = messages.getFormatter("result-SprintRegister-ok").format(sprintName);
			return pageRenderLS.createPageRenderLinkWithContext("tools/sprint/SprintCreated", sprintId);
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}
		return null;

	}

	void onActivate(Long sprintId) {
		this.sprintId = sprintId;
	}

	Long onPassivate() {
		return sprintId;
	}

	void onPrepare() {
		try {
			sprintDetails = sprintService.findSprint(sprintId);
			sprintName = sprintDetails.getSprintName();
			sprintStart = sprintDetails.getStartDate().getTime();
			sprintEnd = sprintDetails.getEndDate().getTime();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
