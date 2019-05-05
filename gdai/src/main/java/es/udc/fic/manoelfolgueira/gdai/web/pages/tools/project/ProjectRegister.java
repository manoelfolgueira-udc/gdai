package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProjectRegister {
	
//    @Property
//    private String groupName;
    
//    @Component(id = "groupName")
//    private TextField groupNameField;

    @Property
    private String expirationDate;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private GroupService groupService;

    @Component
    private Form registrationForm;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    private String result = null;

    void onValidateFromRegistrationForm() {

       
    }

    Object onSuccess() {
        return ProjectManagement.class;
    }
    
    
    String onPassivate() {
    	return result;
    }

}
