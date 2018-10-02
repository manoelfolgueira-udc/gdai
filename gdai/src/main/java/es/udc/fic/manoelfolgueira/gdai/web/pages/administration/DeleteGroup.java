package es.udc.fic.manoelfolgueira.gdai.web.pages.administration;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class DeleteGroup {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
	@Inject
    private Messages messages;
	
	@Inject
	private GroupService groupService;
	
	@Property
	private String infoDeleteGroup;
	
	private Long groupId;
	
	@Property
	private Group group;
	
	@Component
	private Form deleteForm;
    
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Locale locale;
	
	void setupRender() {
		infoDeleteGroup = messages.format("surePerformAction", messages.get("info-delete-group"));
		try {
			group = groupService.findGroup(groupId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void onActivate(Long groupId) {
		this.groupId = groupId;
	}
	
	Long onPassivate() {
        return groupId;
    }
	
	public String getDeleteGroupInfo() {
		return this.infoDeleteGroup;
	}
	
	Object onValidateFromDeleteForm() {
		try {
			groupService.removeGroup(groupId);
			return pageRenderLS.createPageRenderLinkWithContext("administration/GroupDeleted", groupId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages
                    .get("error-groupDoesNotExist") + group.getGroupName());
			return null;
		}
	}
	
}
