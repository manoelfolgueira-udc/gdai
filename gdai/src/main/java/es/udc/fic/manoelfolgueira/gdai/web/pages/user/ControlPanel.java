package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project.ProjectManagement;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Main page for any users logged in
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ControlPanel.java
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

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	Object onActivate() {

		if (userSession == null)
			return Login.class;

		UserDetails userDetails;

		try {
			userDetails = userService.findUser(userSession.getUserId());
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			return Login.class;
		}

		if ((userDetails.getGroup().getGroupName().equals(
				Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.ADMINISTRATORS_GROUP_NAME)))) {
			return ProjectManagement.class;
		} else {
			return ProjectManagement.class;
		}
	}

	void onPrepareForRender() throws InstanceNotFoundException {

		UserDetails userDetails = userService.findUser(userSession.getUserId());
		
		firstName = userDetails.getFirstName();
		lastName = userDetails.getLastName();
		email = userDetails.getEmail();

	}

	String onPassivate() {
		return result;
	}

}