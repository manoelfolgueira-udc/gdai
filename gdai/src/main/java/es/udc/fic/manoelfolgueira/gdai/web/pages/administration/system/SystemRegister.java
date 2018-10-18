package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import java.util.Calendar;
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
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemRegister {
	
	// The activation context
    private Long groupId;
	
    @Property
    private String systemName;
    
    @Component(id = "systemName")
    private TextField systemNameField;
    
    @Property
    private String systemDescription;
    
    @Component(id = "systemDescription")
    private TextField systemDescriptionField;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private SystemService systemService;

    @Component
    private Form registrationForm;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Inject
    private GroupService groupService;
    
    @Property
    private String result = null;
    
    @Property
    private Group group;
    
    @Property
	private SelectModel groupsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        try {
        	Calendar calCreationDate = Calendar.getInstance();
        	systemService.registerSystem(systemName, new SystemDetails(systemName, systemDescription, calCreationDate, group));
        	result = messages.getFormatter("result-SystemRegister-ok").format(systemName);
        } catch (DuplicateInstanceException e) {
            registrationForm.recordError(systemNameField, messages
                    .get("error-systemNameAlreadyExists"));
        } catch (Exception e) {
        	e.printStackTrace();
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return SystemManagement.class;
    }
    
    
    String onPassivate() {
    	return result;
    }
    
    public GroupEncoder getGroupEncoder() {
		return new GroupEncoder(groupService);
	}
    
    void onPrepare() {

		List<Group> groups = groupService.findAllOrderedByGroupNameIC();

		if (groupId != null) {
			group = findGroupInList(groupId, groups);
		}

		groupsModel = selectModelFactory.create(groups, "groupName");
	}

	private Group findGroupInList(Long groupId, List<Group> groups) {
		for (Group g : groups) {
			if (g.getGroupId().equals(groupId)) {
				return g;
			}
		}
		return null;
	}

}
