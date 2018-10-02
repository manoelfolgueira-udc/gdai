package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
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
    private String expirationTime;
    
    @Inject
    private GroupService groupService;
    
    @Property
    private Group group;

    @Component
    private Form updateProfileForm;

    @Component(id = "loginName")
    private TextField loginNameField;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    void onPrepareForRender() throws InstanceNotFoundException {

        user= userService.findUserProfile(userSession
                .getUserId());
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        
        avatarUrl = user.getAvatarUrl() == null ? "" : user.getAvatarUrl();

    }
    
    void onValidateFromUpdateProfileForm() {

        if (!updateProfileForm.isValid()) {
            return;
        }

        try {
        	
        	Calendar calHireDate = Calendar.getInstance();
        	Calendar calDateOfBirth = Calendar.getInstance();
        	Calendar calExpirationTime = Calendar.getInstance();
        	SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        	if (hireDate != null) calHireDate.setTime(sdf.parse(hireDate)); else calHireDate = null;
        	if (dateOfBirth != null) calDateOfBirth.setTime(sdf.parse(dateOfBirth)); else calDateOfBirth = null;
        	if (expirationTime != null) calExpirationTime.setTime(sdf.parse(expirationTime)); else calExpirationTime = null;
        	
            // TODO: UPDATE USER
            // userProfile.getUserId();
    //    } catch (DuplicateInstanceException e) {
        	//         updateProfileForm.recordError(loginNameField, messages
        	//      .get("error-loginNameAlreadyExists"));
        } catch (Exception e) {
        	updateProfileForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() throws InstanceNotFoundException {

        userService.updateUserDetails(
                userSession.getUserId(), new UserDetails(null, firstName, lastName, null, email, phoneNumber, avatarUrl, null, null, null, null));
        userSession.setFirstName(firstName);
        
        return Index.class;

    }
    
    public String getHireDateDBValue() {
    	return Utils.getFormattedDate(user.getHireDate().getTime(), locale);
    }
    
    public String getDateOfBirthDBValue() {
    	return Utils.getFormattedDate(user.getDateOfBirth().getTime(), locale);
    }
    
    public String getExpirationTimeDBValue() {
    	return Utils.getFormattedDate(user.getExpirationTime().getTime(), locale);
    }
    
    public boolean getTestMale() {
    	return user.getGender().equals("M");
    }

}