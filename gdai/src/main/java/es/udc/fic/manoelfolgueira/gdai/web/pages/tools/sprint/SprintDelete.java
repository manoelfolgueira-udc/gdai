package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.sprint;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete an sprint web page
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   SprintDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SprintDelete {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
	@Inject
    private Messages messages;
	
	@Inject
	private SprintService sprintService;
	
	@Property
	private String infoSprintDelete;
	
	private Long sprintId;
	
	@Property
	private Sprint sprint;
	
	@Component
	private Form deleteForm;
    
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Locale locale;
	
	void setupRender() {
		infoSprintDelete = messages.format("surePerformAction", messages.get("info-delete-sprint"));
		try {
			sprint = sprintService.findSprint(sprintId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void onActivate(Long sprintId) {
		this.sprintId = sprintId;
	}
	
	Long onPassivate() {
        return sprintId;
    }
	
	public String getSprintDeleteInfo() {
		return this.infoSprintDelete;
	}
	
	Object onValidateFromDeleteForm() {
		try {
			sprintService.removeSprint(sprintId);
			return pageRenderLS.createPageRenderLinkWithContext("administration/sprint/SprintDeleted", sprintId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages
                    .get("error-sprintDoesNotExist") + sprint.getSprintName());
			return null;
		}
	}
	
}
