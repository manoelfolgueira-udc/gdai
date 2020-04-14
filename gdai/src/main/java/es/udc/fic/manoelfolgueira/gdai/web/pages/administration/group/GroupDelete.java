package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.group;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete an application web page
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GroupDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GroupDelete {

	private Long groupId;

	// Properties

	@Property
	private GroupDetails groupDetails;

	@Property
	private String infoDeleteGroup;

	// Services

	@Inject
	private GroupService groupService;

	// Extra Components

	@SessionState(create = false)
	private UserSession userSession;

	@Component
	private Form deleteForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	// Methods

	/**
	 * Activation Context
	 * 
	 * @param groupId
	 */
	void onActivate(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * Activation Context
	 * 
	 * @return
	 */
	Long onPassivate() {
		return groupId;
	}

	/**
	 * Set up the render
	 */
	void setupRender() {
		infoDeleteGroup = messages.format("surePerformAction", messages.get("info-delete-group"));
		try {
			groupDetails = groupService.findGroup(groupId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * We validate the delete form
	 * 
	 * @return a redirection to the GroupDeleted page if it successes
	 */
	Object onValidateFromDeleteForm() {
		try {
			groupService.removeGroup(groupId);
			return pageRenderLS.createPageRenderLinkWithContext("administration/group/GroupDeleted", groupId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages.get("error-groupDoesNotExist") + groupDetails.getGroupName());
			return null;
		}
	}

	/**
	 * @return the deletion message
	 */
	public String getDeleteGroupInfo() {
		return this.infoDeleteGroup;
	}

}
