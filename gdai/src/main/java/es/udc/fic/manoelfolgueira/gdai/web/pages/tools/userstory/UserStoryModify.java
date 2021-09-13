package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserStoryDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify a user story
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserStoryModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserStoryModify {

	@Inject
	private PageRenderLinkSource pageRenderLS;

	@Property
	private String userStoryName;

	@Component(id = "userStoryName")
	private TextField userStoryNameField;

	@Property
	private String userStoryDescription;

	@Component(id = "userStoryDescription")
	private TextField userStoryDescriptionField;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserStoryService userStoryService;

	@Inject
	private UserService userService;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private String result = null;

	private Long userStoryId;

	@Property
	private UserStoryDetails userStoryDetails;

	void onActivate(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	Long onPassivate() {
		return userStoryId;
	}

	void setupRender() throws InstanceNotFoundException {
		userStoryDetails = userStoryService.findUserStory(userStoryId);
		userStoryName = userStoryDetails.getUserStoryName();
		userStoryDescription = userStoryDetails.getUserStoryDescription();
	}

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

		try {

			userStoryDetails = userStoryService.findUserStory(userStoryId);
			UserDetails userDetails = userService.findUser(userSession.getUserId());
			userStoryService.updateUserStoryDetails(userStoryId,
					new UserStoryDetails(userStoryDetails.getUserStoryId(), userStoryName, userStoryDescription, userStoryDetails.getCreationDate(), userDetails));

			result = messages.getFormatter("result-UserStoryRegister-ok").format(userStoryName);

		} catch (Exception e) {
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() {
		return pageRenderLS.createPageRenderLinkWithContext("tools/userStory/UserStoryModified", userStoryId);
	}

}
