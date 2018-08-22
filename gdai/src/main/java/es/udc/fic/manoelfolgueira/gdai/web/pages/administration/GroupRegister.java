package es.udc.fic.manoelfolgueira.gdai.web.pages.administration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GroupRegister {
	
    @Property
    private String groupName;
    
    @Component(id = "groupName")
    private TextField groupNameField;

    @Property
    private String expirationTime;
    
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

        if (!registrationForm.isValid()) {
            return;
        }

        try {

        	Calendar calCreationTime = Calendar.getInstance();
        	Calendar calExpirationTime = Calendar.getInstance();
        	SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        	if (expirationTime != null) calExpirationTime.setTime(sdf.parse(expirationTime)); else calExpirationTime = null;
        	
        	groupService.registerGroup(groupName, new GroupDetails(groupName, calCreationTime, calExpirationTime));
        	
        	result = messages.getFormatter("result-GroupRegister-ok").format(groupName);
        } catch (DuplicateInstanceException e) {
            registrationForm.recordError(groupNameField, messages
                    .get("error-groupNameAlreadyExists"));
        } catch (Exception e) {
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return Index.class;
    }
    
    
    String onPassivate() {
    	return result;
    }

}
