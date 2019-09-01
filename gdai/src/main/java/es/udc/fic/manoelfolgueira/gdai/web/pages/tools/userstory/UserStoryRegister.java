package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project.ProjectManagement;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Projects
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ProjectRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserStoryRegister {

    @Property
    private String userStoryName;
    
    @Property
    private String userStoryDescription;
    
    @Component(id = "userStoryName")
    private TextField userStoryNameField;
    
    @Component(id = "userStoryDescription")
    private TextField userStoryDescriptionField;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private UserStoryService userStoryService;
    
    @Inject
    private UserService userService;

    @Component
    private Form registrationForm;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    private String result = null;
    
    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        try {
        	
        	User user = userService.findUser(userSession.getUserId());
        	Calendar calCreationDate = Calendar.getInstance();
        	userStoryService.registerUserStory(userStoryName, new UserStoryDetails(userStoryName, userStoryDescription, calCreationDate, user));
        	result = messages.getFormatter("result-UserStoryRegister-ok").format(userStoryName);
        	
        } catch (DuplicateInstanceException e) {
            registrationForm.recordError(userStoryNameField, messages
                    .get("error-userStoryNameAlreadyExists"));
        } catch (Exception e) {
        	e.printStackTrace();
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return ProjectManagement.class;
    }
    
    
    String onPassivate() {
    	return result;
    }
    
}
