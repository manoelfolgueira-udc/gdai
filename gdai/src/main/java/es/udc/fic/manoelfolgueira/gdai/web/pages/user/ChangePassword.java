package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.IncorrectPasswordException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.CookiesManager;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows any user to change their password
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ChangePassword.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ChangePassword {

	@Property
	private String oldPassword;

	@Property
	private String newPassword;

	@Property
	private String retypeNewPassword;

	@SessionState(create = false)
	private UserSession userSession;

	@Component
	private Form changePasswordForm;

	@Inject
	private Cookies cookies;

	@Inject
	private Messages messages;

	@Inject
	private UserService userService;

	void onValidateFromChangePasswordForm() throws InstanceNotFoundException {

		if (!changePasswordForm.isValid()) {
			return;
		}

		if (!newPassword.equals(retypeNewPassword)) {
			changePasswordForm.recordError(messages.get("error-passwordsDontMatch"));
		} else {

			try {
				userService.changePassword(userSession.getUserId(), oldPassword, newPassword);
			} catch (IncorrectPasswordException e) {
				changePasswordForm.recordError(messages.get("error-invalidPassword"));
			}

		}

	}

	Object onSuccess() {

		CookiesManager.removeCookies(cookies);
		return Index.class;

	}

}
