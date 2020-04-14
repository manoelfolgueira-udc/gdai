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

import es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseDetails;
import es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseService;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
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
	private SystemDetails systemDetails;

	@Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	private GDAICaseDetails gdaiCaseDetails;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

	}

	Object onSuccess() {

		Calendar calCreationDate = Calendar.getInstance();
		UserDetails createdBy;
		try {
			createdBy = userService.findUser(userSession.getUserId());
			gdaiCaseDetails = gdaiCaseService.createGDAICase(new GDAICaseDetails(gdaiCaseDescription,
					gdaiCaseResolution == null ? "" : gdaiCaseResolution, calCreationDate, createdBy, systemDetails));
		} catch (DuplicateInstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageRenderLS.createPageRenderLinkWithContext("tools/gdaiCase/gdaiCasecreated", gdaiCaseDetails.getGDAICaseId());
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
