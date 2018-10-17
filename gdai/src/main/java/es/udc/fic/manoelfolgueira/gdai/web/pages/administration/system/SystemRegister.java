package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemRegister {
	
    @Property
    private String systemName;
    
    @Component(id = "systemName")
    private TextField systemNameField;
    
    @Property
    private String systemDescription;
    
    @Component(id = "systemDescription")
    private TextField systemDescriptionField;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private SystemService systemService;

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
        	Calendar calCreationDate = Calendar.getInstance();
        	systemService.registerSystem(systemName, new SystemDetails(systemName, systemDescription, calCreationDate));
        	result = messages.getFormatter("result-SystemRegister-ok").format(systemName);
        } catch (DuplicateInstanceException e) {
            registrationForm.recordError(systemNameField, messages
                    .get("error-systemNameAlreadyExists"));
        } catch (Exception e) {
        	e.printStackTrace();
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return SystemManagement.class;
    }
    
    
    String onPassivate() {
    	return result;
    }

}
