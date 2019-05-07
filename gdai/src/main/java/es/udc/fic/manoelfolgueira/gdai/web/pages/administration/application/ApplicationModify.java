package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.application;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.application.Application;
import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ApplicationModify {
	
	// The activation context
    private Long systemId;
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
    @Property
    private String applicationName;
    
    @Component(id = "applicationName")
    private TextField applicationNameField;
    
    @Property
    private String applicationDescription;
    
    @Component(id = "applicationDescription")
    private TextField applicationDescriptionField;

    @Property
    private Date expirationDate;

    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private ApplicationService applicationService;

    @Component
    private Form registrationForm;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Inject
    private SystemService systemService;
    
    @Property
    private String result = null;
    
    @Property
    private System system;
    
    @Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

    
    private Long applicationId;
    
    @Property
    private Application application;
    
    void onActivate(Long applicationId) {
		this.applicationId = applicationId;
	}
	
	Long onPassivate() {
        return applicationId;
    }
    
    void setupRender() throws InstanceNotFoundException {
    	application = applicationService.findApplication(applicationId);

    	applicationName = application.getApplicationName();
    	applicationDescription = application.getApplicationDescription();
    	system = application.getSystem();
    }

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        try {
        	
        	application = applicationService.findApplication(applicationId);
        	
         	applicationService.updateApplicationDetails(applicationId, new ApplicationDetails(applicationName, applicationDescription, application.getCreationDate(), system));
        	
        	result = messages.getFormatter("result-ApplicationRegister-ok").format(applicationName);
        	        	
        } catch (Exception e) {
        	e.printStackTrace();
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return pageRenderLS.createPageRenderLinkWithContext("administration/application/ApplicationModified", applicationId);
    }
    
    public SystemEncoder getSystemEncoder() {
		return new SystemEncoder(systemService);
	}
    
    void onPrepare() {

		List<System> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			system = findSystemInList(systemId, systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");
	}

	private System findSystemInList(Long systemId, List<System> systems) {
		for (System g : systems) {
			if (g.getSystemId().equals(systemId)) {
				return g;
			}
		}
		return null;
	}
	
}
