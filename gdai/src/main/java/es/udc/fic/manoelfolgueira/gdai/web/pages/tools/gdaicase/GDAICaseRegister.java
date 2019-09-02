package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.gdaicase;

import java.util.Calendar;
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

import es.udc.fic.manoelfolgueira.gdai.model.gdaicase.GDAICase;
import es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseDetails;
import es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseService;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new GDAICases
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GDAICaseRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GDAICaseRegister {

	// The activation context
	private Long systemId;
	@Property
	private String gdaiCaseDescription;

	@Component(id = "gdaiCaseDescription")
	private TextField gdaiCaseDescriptionField;

	@Property
	private String gdaiCaseResolution;

	@Component(id = "gdaiCaseResolution")
	private TextField gdaiCaseResolutionField;

	@Property
	private String targetDate;

	@SessionState(create = false)
	private UserSession userSession;
	@Inject
	private GDAICaseService gdaiCaseService;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private SystemService systemService;

	@Inject
	private UserService userService;

	@Property
	private System system;

	@Property
	private SelectModel systemsModel;
	
	@Inject
	private SelectModelFactory selectModelFactory;

	private GDAICase gdaiCase;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

	}

	Object onSuccess() {

		Calendar calCreationDate = Calendar.getInstance();
		User createdBy;
		try {
			createdBy = userService.findUser(userSession.getUserId());
			gdaiCase = gdaiCaseService.createGDAICase(
					new GDAICaseDetails(gdaiCaseDescription, gdaiCaseResolution == null ? "" : gdaiCaseResolution, calCreationDate, createdBy, system));
		} catch (DuplicateInstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageRenderLS.createPageRenderLinkWithContext("tools/gdaiCase/gdaiCasecreated", gdaiCase.getGDAICaseId());
	}

	public SystemEncoder getSystemEncoder() {
		return new SystemEncoder(systemService);
	}

	void onPrepare() {

		List<System> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			system = findSystemInList(systemId, systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");
	}
	
	private System findSystemInList(Long systemId, List<System> systems) {
		for (System s : systems) {
			if (s.getSystemId().equals(systemId)) {
				return s;
			}
		}
		return null;
	}

}
