package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import java.util.Date;
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
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify a System
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   SystemModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemModify {
	
	// The activation context
    private Long groupId;
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
    @Property
    private String systemName;
    
    @Component(id = "systemName")
    private TextField systemNameField;
    
    @Property
    private String systemDescription;
    
    @Component(id = "systemDescription")
    private TextField systemDescriptionField;

    @Property
    private Date expirationDate;

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

    
    private Long systemId;
    
    @Property
    private System system;
    
    void onActivate(Long systemId) {
		this.systemId = systemId;
	}
	
	Long onPassivate() {
        return systemId;
    }
    
    void setupRender() throws InstanceNotFoundException {
    	system = systemService.findSystem(systemId);

    	systemName = system.getSystemName();
    	systemDescription = system.getSystemDescription();
    	group = system.getGroup();
    }

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        try {
        	
        	system = systemService.findSystem(systemId);
        	
         	systemService.updateSystemDetails(systemId, new SystemDetails(systemName, systemDescription, system.getCreationDate(), group));
        	
        	result = messages.getFormatter("result-SystemRegister-ok").format(systemName);
        	        	
        } catch (Exception e) {
        	e.printStackTrace();
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }

    }

    Object onSuccess() {
        return pageRenderLS.createPageRenderLinkWithContext("administration/system/SystemModified", systemId);
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
