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

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Groups
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GroupRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GroupRegister {

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
	private Date expirationDate;

	@Property
	private String result = null;

	// Services

	@Inject
	private GroupService groupService;

	// Extra Components

	@SessionState(create = false)
	private UserSession userSession;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	// Methods

	/**
	 * We validate the form
	 * 
	 * @return a redirection to GroupCreated if it succeeded
	 */
	Object onValidateFromRegistrationForm() {

		GroupDetails groupDetails;

		if (!registrationForm.isValid()) {
			return null;
		}

		try {

			Calendar calExpirationDate = Calendar.getInstance();
			calExpirationDate.setTime(expirationDate);

			groupDetails = groupService.registerGroup(new GroupDetails(null, groupName, groupDescription,
					Calendar.getInstance(), calExpirationDate, null, null));

			result = messages.getFormatter("result-GroupRegister-ok").format(groupName);

			return pageRenderLS.createPageRenderLinkWithContext("administration/group/GroupCreated",
					groupDetails.getGroupId());
		} catch (DuplicateInstanceException e) {
			registrationForm.recordError(groupNameField, messages.get("error-groupNameAlreadyExists"));
		} catch (InvalidDateException e) {
			registrationForm.recordError(messages.get("error-expirationDateLEQToday"));
		} catch (Exception e) {
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}
		return null;

	}

}
