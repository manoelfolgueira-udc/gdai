package es.udc.fic.manoelfolgueira.gdai.web.pages.group;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that lets User see any Group
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   GroupView.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GroupView {

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private GroupService groupService;
    
    @Property
    private Group group;
    
    @Property
    private String expirationDate = null;
    
    @Inject
    private Locale locale;

    void onActivate(Long groupId) throws InstanceNotFoundException {

        group = groupService.findGroup(groupId);
        
        if (group.getExpirationDate()== null) expirationDate = "";
        else {
        	Calendar cal = group.getExpirationDate();
            cal.add(Calendar.DATE, 1);
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            expirationDate = f.format(cal.getTime());
        }

    }
    public String getExpirationDateFormatted() {
    	return group.getExpirationDate() == null ? "" : Utils.getFormattedDate(group.getExpirationDate().getTime(), locale);
    }

}