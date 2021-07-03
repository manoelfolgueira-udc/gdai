package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.application;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.services.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete an application web page
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ApplicationDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ApplicationDelete {

	@Inject
	private PageRenderLinkSource pageRenderLS;

	@Inject
	private Messages messages;

	@Inject
	private ApplicationService applicationService;

	@Property
	private String infoApplicationDelete;

	private Long applicationId;

	@Property
	private ApplicationDetails applicationDetails;

	@Component
	private Form deleteForm;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private Locale locale;

	void setupRender() {
		infoApplicationDelete = messages.format("surePerformAction", messages.get("info-delete-application"));
		try {
			applicationDetails = applicationService.findApplication(applicationId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void onActivate(Long applicationId) {
		this.applicationId = applicationId;
	}

	Long onPassivate() {
		return applicationId;
	}

	public String getApplicationDeleteInfo() {
		return this.infoApplicationDelete;
	}

	Object onValidateFromDeleteForm() {
		try {
			applicationService.remove(applicationId);
			return pageRenderLS.createPageRenderLinkWithContext("administration/application/ApplicationDeleted",
					applicationId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(
					messages.get("error-applicationDoesNotExist") + applicationDetails.getApplicationName());
			return null;
		}
	}

}
