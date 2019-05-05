package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UpdateProfile {

    @Property
    private String firstName;

    @Property
    private String lastName;

    @Property
    private String email;
    
    @Property
    private String phoneNumber;
    
    @Property
    private String avatarUrl;
    
    @Property
    private String groupName;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;
    
    @Property
    private User user;

    @Property
    private String loginName;


    @Property
    private String genderValue;
    
    @Component
    private RadioGroup gender;
    
    @Property
    private String hireDate;
    
    @Property
    private String dateOfBirth;

    @Property
    private String expirationDate;
    
    @Inject
    private GroupService groupService;
    
    @Property
    private Group group;

    @Component
    private Form updateProfileForm;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    void onPrepareForRender() throws InstanceNotFoundException {

        user = userService.findUserProfile(userSession
                .getUserId());

        loginName = user.getLoginName();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        
        hireDate = getHireDateDBValue();
        dateOfBirth = getDateOfBirthDBValue();
        expirationDate = getExpirationDateDBValue();
        
        avatarUrl = user.getAvatarUrl() == null ? "" : user.getAvatarUrl();
        
        groupName = user.getGroup().getGroupName();

    }
    
    void onValidateFromUpdateProfileForm() {

        if (!updateProfileForm.isValid()) {
            return;
        }

        try {
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	updateProfileForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() throws InstanceNotFoundException {
    	
    	user = userService.findUserProfile(userSession
                .getUserId());
    	
        userService.updateUserDetails(
                userSession.getUserId(), new UserDetails(loginName, firstName, lastName, genderValue, email, phoneNumber,
            			avatarUrl, user.getHireDate(), user.getDateOfBirth(), user.getExpirationDate(), user.getGroup()));
        userSession.setLoginName(loginName);
        
        return ViewProfile.class;

    }
    
    public String getHireDateDBValue() {
    	return Utils.getFormattedDate(user.getHireDate().getTime(), locale);
    }
    
    public String getDateOfBirthDBValue() {
    	return Utils.getFormattedDate(user.getDateOfBirth().getTime(), locale);
    }
    
    public String getExpirationDateDBValue() {
    	return Utils.getFormattedDate(user.getExpirationDate().getTime(), locale);
    }
    
    public boolean getTestMale() {
    	return user.getGender().equals("M");
    }

}