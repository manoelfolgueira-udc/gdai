package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ControlPanel {

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

    void onPrepareForRender() throws InstanceNotFoundException {

        User user;

        user= userService.findUserProfile(userSession
                .getUserId());
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();

    }

    Object onSuccess() throws InstanceNotFoundException {

        userService.updateUserDetails(
                userSession.getUserId(), new UserDetails(
                        firstName, lastName, email));
        userSession.setFirstName(firstName);
        return Index.class;

    }

}