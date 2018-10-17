package es.udc.fic.manoelfolgueira.gdai.web.pages.system;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemView {

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private SystemService systemService;
    
    @Property
    private System system;
    
    @Inject
    private Locale locale;

    void onActivate(Long systemId) throws InstanceNotFoundException {

        system = systemService.findSystem(systemId);

    }
}