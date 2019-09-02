package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.gdaicase;

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
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify a gdaiCase
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GDAICaseModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GDAICaseModify {
	
	private Long systemId;

	@Property
	private Long gdaiCaseId;

	@Property
	private String gdaiCaseResolution;

	@Component(id = "gdaiCaseResolution")
	private TextField gdaiCaseResolutionField;

	@Property
	private String gdaiCaseDescription;

	@Component(id = "gdaiCaseDescription")
	private TextField gdaiCaseDescriptionField;

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

	void onActivate(Long gdaiCaseId) {
		this.gdaiCaseId = gdaiCaseId;
	}

	Long onPassivate() {
		return gdaiCaseId;
	}

	void setupRender() {
		try {

			gdaiCase = gdaiCaseService.findGDAICase(gdaiCaseId);

			gdaiCaseDescription = gdaiCase.getGDAICaseDescription();
			gdaiCaseResolution = gdaiCase.getGDAICaseResolution();

			system = gdaiCase.getSystem();

		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

		try {
			User createdBy = userService.findUser(userSession.getUserId());
			gdaiCase = gdaiCaseService.findGDAICase(gdaiCaseId);

			gdaiCaseService.updateGDAICaseDetails(gdaiCaseId, new GDAICaseDetails(gdaiCaseDescription,
					gdaiCaseResolution, gdaiCase.getCreationDate(), createdBy, system));
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() {

		return pageRenderLS.createPageRenderLinkWithContext("tools/gdaiCase/gdaiCasemodified", gdaiCaseId);
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
