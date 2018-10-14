package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.group;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ManageGroups {
    
    @Property
    private Group group;

    @Property
    private String groupName;
    
    @Property
    private String creationDate;
    
    @Property
    private String expirationDate;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private GroupService groupService;
    
//    @Property
//    private SelectModel groupsModel;
//    
//    @Inject
//    private SelectModelFactory selectModelFactory;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    List<Group> groups;
    
    @Property
    private Action action;
    
    void onActivate (Action action) {
    	this.action = action;
    }
    
    Action onPassivate() {
    	return this.action;
    }
    
    void setupRender() {
    	// A GridDataSource is not provided due to the little ammount of groups which are going to be in the app at a time
        groups = groupService.findAllOrderedByGroupNameIC();
    }
    
    public String getGroupCreationDateTimeFormatted() {
    	
    	return (group.getCreationTime() != null) ? Utils.getFormattedDate(group.getCreationTime().getTime(), locale) : "";
    }
    
    public String getGroupExpirationDateTimeFormatted() {
    	return (group.getExpirationTime() != null) ? Utils.getFormattedDate(group.getExpirationTime().getTime(), locale) : "";
    }
    
}
