package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import java.util.Calendar;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.IncorrectPasswordException;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.UserExpiratedException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.CookiesManager;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Login {

    @Property
    private String loginName;

    @Property
    private String password;

    @Property
    private boolean rememberMyPassword;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Cookies cookies;

    @Component
    private Form loginForm;

    @Inject
    private Messages messages;

    @Inject
    private UserService userService;

    private User userProfile = null;


    void onValidateFromLoginForm() {

        if (!loginForm.isValid()) {
            return;
        }

        try {
            userProfile = userService.login(loginName, password, false);
            if ( (userProfile.getExpirationDate() != null) &&
            		(userProfile.getExpirationDate().compareTo(Calendar.getInstance()) < 0) ) {
            	throw new UserExpiratedException(userProfile.getUserId(), User.class.getName());
            }
        } catch (InstanceNotFoundException | IncorrectPasswordException e) {
        	e.printStackTrace();
            loginForm.recordError(messages.get("error-authenticationFailed"));
        }  catch (UserExpiratedException e) {
        	e.printStackTrace();
        	loginForm.recordError(messages.get("error-expiratedUser"));
		}

    }

    Object onSuccess() {

    	userSession = new UserSession();
        userSession.setUserId(userProfile.getUserId());
        userSession.setLoginName(userProfile.getLoginName());
        
        String userGroupName = userProfile.getGroup() == null ? "" : userProfile.getGroup().getGroupName();

        userSession.setAdministrator(userGroupName.equals(Config.getInstance().getProperties().getProperty(Config.ADMINISTRATORS_GROUP_NAME)));

        if (rememberMyPassword) {
            CookiesManager.leaveCookies(cookies, loginName, userProfile
                    .getEncryptedPassword());
        }
        return ControlPanel.class;

    }

}
