package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete a User web page
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   UserDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserDelete {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
	@Inject
    private Messages messages;
	
	@Inject
	private UserService userService;
	
	@Property
	private String infoDeleteUser;
	
	private Long userId;
	
	@Property
	private User user;
	
	@Component
	private Form deleteForm;
    
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Locale locale;
    
    void onActivate(Long userId) {
		this.userId = userId;
	}
	
	Long onPassivate() {
        return userId;
    }
	
	void setupRender() {
		infoDeleteUser = messages.format("surePerformAction", messages.get("info-delete-user"));
		try {
			user = userService.findUser(userId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getDeleteUserInfo() {
		return this.infoDeleteUser;
	}
	
	Object onValidateFromDeleteForm() {
		try {
			userService.remove(userId);
			return pageRenderLS.createPageRenderLinkWithContext("administration/user/UserDeleted", userId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages
                    .get("error-userDoesNotExist") + user.getLoginName());
			return null;
		}
	}
	
}
