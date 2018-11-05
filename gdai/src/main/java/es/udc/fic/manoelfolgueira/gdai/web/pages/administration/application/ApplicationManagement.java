package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.application;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.application.Application;
import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ApplicationManagement {
    
    @Property
    private Application application;

    @Property
    private String applicationName;
    
    @Property
    private String applicationDescription;
    
    @Property
    private String creationDate;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private ApplicationService applicationService;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    List<Application> applications;
    
    void setupRender() {
    	// A GridDataSource is not provided due to the little ammount of applications which are going to be in the app at a time
        applications = applicationService.findAllOrderedByApplicationName();
    }
    
    public String getApplicationCreationDateFormatted() {
    	
    	return (application.getCreationDate() != null) ? Utils.getFormattedDate(application.getCreationDate().getTime(), locale) : "";
    }
    
}
