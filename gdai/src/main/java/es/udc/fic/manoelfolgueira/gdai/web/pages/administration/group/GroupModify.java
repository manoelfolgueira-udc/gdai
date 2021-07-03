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

import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify a Group
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GroupModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GroupModify {

	private Long groupId;

	// Properties

	@Property
	private String groupName;

	@Component(id = "groupName")
	private TextField groupNameField;

	@Property
	private String groupDescription;

	@Component(id = "groupDescription")
	private TextField groupDescriptionField;

	@Property
	private Date creationDate;

	@Property
	private Date expirationDate;

	@Property
	private String result = null;

	@Property
	private GroupDetails groupDetails;

	// Services

	@Inject
	private GroupService groupService;

	// Extra Components

	@Inject
	private PageRenderLinkSource pageRenderLS;

	@SessionState(create = false)
	private UserSession userSession;

	@Component
	private Form modificationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

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
	 * @param groupId
	 */
	Long onPassivate() {
		return groupId;
	}

	/**
	 * Setup the render
	 * 
	 * @throws InstanceNotFoundException
	 */
	void setupRender() throws InstanceNotFoundException {
		groupDetails = groupService.findGroup(groupId);

		groupName = groupDetails.getGroupName();
		groupDescription = groupDetails.getGroupDescription();
		creationDate = groupDetails.getCreationDate().getTime();
		expirationDate = groupDetails.getExpirationDate().getTime();

	}

	/**
	 * We validate the form
	 */
	void onValidateFromModificationForm() {

		if (!modificationForm.isValid()) {
			return;
		}

		try {

			groupDetails = groupService.findGroup(groupId);

			Calendar calExpirationDate = Calendar.getInstance();
			calExpirationDate.setTime(expirationDate);

			groupService.updateGroupDetails(
					new GroupDetails(groupId, groupName, groupDescription, groupDetails.getCreationDate(),
							calExpirationDate, groupDetails.getUsers(), groupDetails.getSystems()));

			result = messages.getFormatter("result-GroupRegister-ok").format(groupName);
		} catch (InvalidDateException e) {
			modificationForm.recordError(messages.get("error-expirationDateLEQToday"));
		} catch (Exception e) {
			modificationForm.recordError(messages.get("error-unexpectedError"));
			e.printStackTrace();
		}

	}

	/**
	 * Once it all is done and it succeeded
	 * 
	 * @return
	 */
	Object onSuccess() {
		return pageRenderLS.createPageRenderLinkWithContext("administration/group/GroupModified", groupId);
	}

}
