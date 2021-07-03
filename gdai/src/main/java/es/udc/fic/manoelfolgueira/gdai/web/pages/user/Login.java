package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import java.util.Calendar;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.IncorrectPasswordException;
import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.UserExpiratedException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.CookiesManager;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows any user to authenticate to GDAI
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file Login.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Login {

	@Property
	private String loginName;

	@Property
	private String password;

	@Property
	private boolean rememberMyPassword;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private Cookies cookies;

	@Component
	private Form loginForm;

	@Inject
	private Messages messages;

	@Inject
	private UserService userService;

	private UserDetails userDetails = null;

	void onValidateFromLoginForm() {

		if (!loginForm.isValid()) {
			return;
		}

		try {
			userDetails = userService.login(loginName, password, false);
			if ((userDetails.getExpirationDate() != null)
					&& (userDetails.getExpirationDate().compareTo(Calendar.getInstance()) < 0)) {
				throw new UserExpiratedException(userDetails.getUserId(), User.class.getName());
			}
		} catch (InstanceNotFoundException | IncorrectPasswordException e) {
			e.printStackTrace();
			loginForm.recordError(messages.get("error-authenticationFailed"));
		} catch (UserExpiratedException e) {
			e.printStackTrace();
			loginForm.recordError(messages.get("error-expiratedUser"));
		}

	}

	Object onSuccess() {

		userSession = new UserSession();
		userSession.setUserId(userDetails.getUserId());
		userSession.setLoginName(userDetails.getLoginName());

		String userGroupName = userDetails.getGroup() == null ? "" : userDetails.getGroup().getGroupName();

		userSession.setAdministrator(userGroupName.equals(
				Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.ADMINISTRATORS_GROUP_NAME)));
		
		User u = new User(userDetails);

		if (rememberMyPassword) {
			CookiesManager.leaveCookies(cookies, loginName, u.getEncryptedPassword());
		}
		
		return ControlPanel.class;

	}

}
