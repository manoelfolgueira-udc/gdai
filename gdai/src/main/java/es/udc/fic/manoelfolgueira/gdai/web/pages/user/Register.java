package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Register {

    @Property
    private String loginName;

    @Property
    private String password;

    @Property
    private String retypePassword;

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

    @Component
    private Form registrationForm;

    @Component(id = "loginName")
    private TextField loginNameField;

    @Component(id = "password")
    private PasswordField passwordField;

    @Inject
    private Messages messages;

    private Long userProfileId;

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        if (!password.equals(retypePassword)) {
            registrationForm.recordError(passwordField, messages
                    .get("error-passwordsDontMatch"));
        } else {

            try {
                User userProfile = userService.registerUser(loginName, password,
                    new UserDetails(firstName, lastName, email));
                userProfileId = userProfile.getUserId();
            } catch (DuplicateInstanceException e) {
                registrationForm.recordError(loginNameField, messages
                        .get("error-loginNameAlreadyExists"));
            }

        }

    }

    Object onSuccess() {

        userSession = new UserSession();
        userSession.setUserId(userProfileId);
        userSession.setFirstName(firstName);
        return Index.class;

    }

}
