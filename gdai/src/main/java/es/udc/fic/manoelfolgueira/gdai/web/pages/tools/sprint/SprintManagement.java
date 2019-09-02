package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.sprint;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allows Sprint Management
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   SprintManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SprintManagement {
    
    @Property
    private Sprint sprint;

    @Property
    private String sprintName;
    
    @Property
    private String sprintDescription;
    
    @Property
    private String creationDate;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private SprintService sprintService;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    List<Sprint> sprints;
    
    void setupRender() {
    	// A GridDataSource is not provided due to the little ammount of sprints which are going to be in the app at a time
        sprints = sprintService.findAllOrderedBySprintStart(SortingType.DESC, 0);
    }
    
    public String getSprintCreationDateFormatted() {
    	
    	return (sprint.getCreationDate() != null) ? Utils.getFormattedDate(sprint.getCreationDate().getTime(), locale) : "";
    }
    
}
