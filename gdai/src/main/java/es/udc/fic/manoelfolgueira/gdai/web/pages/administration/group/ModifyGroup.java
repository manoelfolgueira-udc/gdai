package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.group;

import java.util.Calendar;
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

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ModifyGroup {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
    @Property
    private String groupName;
    
    @Component(id = "groupName")
    private TextField groupNameField;

    @Property
    private Date expirationDate;

    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private GroupService groupService;

    @Component
    private Form registrationForm;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    private String result = null;
    
    private Long groupId;
    
    private Group group;
    
    void onActivate(Long groupId) {
		this.groupId = groupId;
	}
	
	Long onPassivate() {
        return groupId;
    }
    
    void setupRender() throws InstanceNotFoundException {
    	group = groupService.findGroup(groupId);
    	groupName = group.getGroupName();
    	expirationDate = (group.getExpirationDate() == null) ? null : group.getExpirationDate().getTime();
    }

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        try {

        	Calendar calCreationDate = Calendar.getInstance();
        	Calendar calExpirationDate = Calendar.getInstance();
        	if (expirationDate != null) calExpirationDate.setTime(expirationDate); else calExpirationDate = null;
        	
         	groupService.updateGroupDetails(groupId, new GroupDetails(groupName, calCreationDate, calExpirationDate));
        	
        	result = messages.getFormatter("result-GroupRegister-ok").format(groupName);
        	        	
        } catch (Exception e) {
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return pageRenderLS.createPageRenderLinkWithContext("administration/group/GroupModified", groupId);
    }
	
}
