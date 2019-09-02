package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete a user story web page
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   UserStoryDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserStoryDelete {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
	@Inject
    private Messages messages;
	
	@Inject
	private UserStoryService UserStoryService;
	
	@Property
	private String infoDeleteUserStory;
	
	private Long UserStoryId;
	
	@Property
	private UserStory userStory;
	
	@Component
	private Form deleteForm;
    
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Locale locale;
	
	void setupRender() {
		infoDeleteUserStory = messages.format("surePerformAction", messages.get("info-delete-userStory"));
		try {
			userStory = UserStoryService.findUserStory(UserStoryId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void onActivate(Long UserStoryId) {
		this.UserStoryId = UserStoryId;
	}
	
	Long onPassivate() {
        return UserStoryId;
    }
	
	public String getDeleteUserStoryInfo() {
		return this.infoDeleteUserStory;
	}
	
	Object onValidateFromDeleteForm() {
		try {
			UserStoryService.removeUserStory(UserStoryId);
			return pageRenderLS.createPageRenderLinkWithContext("tools/userStory/UserStoryDeleted", UserStoryId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages
                    .get("error-UserStoryDoesNotExist") + userStory.getUserStoryName());
			return null;
		}
	}
	
}
