package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import java.util.Date;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemModify {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
    @Property
    private String systemName;
    
    @Component(id = "systemName")
    private TextField systemNameField;
    
    @Property
    private String systemDescription;
    
    @Component(id = "systemDescription")
    private TextField systemDescriptionField;

    @Property
    private Date expirationDate;

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
    
    private Long systemId;
    
    @Property
    private System system;
    
    void onActivate(Long systemId) {
		this.systemId = systemId;
	}
	
	Long onPassivate() {
        return systemId;
    }
    
    void setupRender() throws InstanceNotFoundException {
    	system = systemService.findSystem(systemId);

    	systemName = system.getSystemName();
    	systemDescription = system.getSystemDescription();
    }

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        try {
        	
        	system = systemService.findSystem(systemId);
        	
         	systemService.updateSystemDetails(systemId, new SystemDetails(systemName, systemDescription, system.getCreationDate()));
        	
        	result = messages.getFormatter("result-SystemRegister-ok").format(systemName);
        	        	
        } catch (Exception e) {
        	e.printStackTrace();
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return pageRenderLS.createPageRenderLinkWithContext("administration/system/SystemModified", systemId);
    }
	
}
