package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allows user story Management
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   UserStoryManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserStoryManagement {
    
    @Property
    private UserStory userStory;

    @Property
    private String UserStoryName;
    
    @Property
    private String creationDate;
    
    @Property
    private String expirationDate;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private UserStoryService UserStoryService;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    List<UserStory> UserStorys;
    void setupRender() {
    	// A GridDataSource is not provided due to the little ammount of UserStorys which are going to be in the app at a time
        UserStorys = UserStoryService.findAllOrderedByUserStoryName();
    }
    
    public String getUserStoryCreationDateFormatted() {
    	
    	return (userStory.getCreationDate() != null) ? Utils.getFormattedDate(userStory.getCreationDate().getTime(), locale) : "";
    }
    
}
