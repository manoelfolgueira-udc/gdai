package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.gdaicase;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseDetails;
import es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete a gdaiCase web page
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GDAICaseDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GDAICaseView {

	@Inject
	private PageRenderLinkSource pageRenderLS;

	@Inject
	private Messages messages;

	@Inject
	private GDAICaseService gdaiCaseService;

	@Property
	private String infoDeleteGDAICase;

	private Long gdaiCaseId;

	@Property
	private GDAICaseDetails gdaiCaseDetails;

	@Component
	private Form deleteForm;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private Locale locale;

	void setupRender() {
		try {
			gdaiCaseDetails = gdaiCaseService.findGDAICase(gdaiCaseId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void onActivate(Long gdaiCaseId) {
		this.gdaiCaseId = gdaiCaseId;
	}

	Long onPassivate() {
		return gdaiCaseId;
	}

}
