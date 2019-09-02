package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets User see any userStory
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   userStoryView.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserStoryView {

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserStoryService userStoryService;
    
    @Property
    private UserStory userStory;
    
    @Inject
    private Locale locale;

    void onActivate(Long userStoryId) throws InstanceNotFoundException {

        userStory = userStoryService.findUserStory(userStoryId);

    }
}