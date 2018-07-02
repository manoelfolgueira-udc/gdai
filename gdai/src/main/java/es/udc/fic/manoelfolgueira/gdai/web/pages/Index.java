package es.udc.fic.manoelfolgueira.gdai.web.pages;

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
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.user.ControlPanel;
import es.udc.fic.manoelfolgueira.gdai.web.pages.user.Login;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.CookiesManager;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Index {

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

    private User user = null;
    
    Object onActivate() {
    	
    	if (userSession != null)
    		 return ControlPanel.class;
    	else return Login.class;

    }


    void onValidateFromLoginForm() {

        if (!loginForm.isValid()) {
            return;
        }

        try {
            user = userService.login(loginName, password, false);
        } catch (InstanceNotFoundException e) {
            loginForm.recordError(messages.get("error-authenticationFailed"));
        } catch (IncorrectPasswordException e) {
            loginForm.recordError(messages.get("error-authenticationFailed"));
        }

    }

    Object onSuccess() {

    	userSession = new UserSession();
        userSession.setUserId(user.getUserId());
        userSession.setFirstName(user.getFirstName());

        if (rememberMyPassword) {
            CookiesManager.leaveCookies(cookies, loginName, user
                    .getEncryptedPassword());
        }
        return ControlPanel.class;

    }

}
