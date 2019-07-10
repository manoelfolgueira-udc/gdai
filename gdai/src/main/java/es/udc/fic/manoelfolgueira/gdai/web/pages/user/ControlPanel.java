package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project.ProjectManagement;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Main page for any users logged in
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ControlPanel.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ControlPanel {
	
	@Property
	private String result;

    @Property
    private String firstName;

    @Property
    private String lastName;

    @Property
    private String email;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;
    
    Object onActivate() {
    	
    	if (userSession == null) return Login.class;
    	
    	User user;

        try {
			user = userService.findUser(userSession
			        .getUserId());
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			return Login.class;
		}
    	
    	if ((user.getGroup().getGroupName().equals(
				Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.ADMINISTRATORS_GROUP_NAME)))) {
    		return ProjectManagement.class;
    	} else {
    		return ControlPanel.class;
    	}
    }

    void onPrepareForRender() throws InstanceNotFoundException {

        User user;

        user = userService.findUser(userSession
                .getUserId());
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();

    }
    
    String onPassivate() {
    	return result;
    }

}