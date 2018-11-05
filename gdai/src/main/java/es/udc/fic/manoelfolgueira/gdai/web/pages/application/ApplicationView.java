package es.udc.fic.manoelfolgueira.gdai.web.pages.application;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.application.Application;
import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ApplicationView {

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private ApplicationService applicationService;
    
    @Property
    private Application application;
    
    @Inject
    private Locale locale;

    void onActivate(Long applicationId) throws InstanceNotFoundException {

        application = applicationService.findApplication(applicationId);

    }
}