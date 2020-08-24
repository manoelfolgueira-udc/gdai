package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Projects
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserStoryRegister {

	@Property
	private String userStoryName;

	@Property
	private String userStoryDescription;

	@Component(id = "userStoryName")
	private TextField userStoryNameField;

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

	@Inject
	private PageRenderLinkSource pageRenderLS;

	Object onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return null;
		}

		UserStoryDetails userStoryDetails;

		try {

			UserDetails userDetails = userService.findUser(userSession.getUserId());
			Calendar calCreationDate = Calendar.getInstance();
			userStoryDetails = userStoryService.registerUserStory(userStoryName,
					new UserStoryDetails(null, userStoryName, userStoryDescription, calCreationDate, userDetails));
			result = messages.getFormatter("result-UserStoryRegister-ok").format(userStoryName);
			return pageRenderLS.createPageRenderLinkWithContext("tools/userstory/userstorycreated",
					userStoryDetails.getUserStoryId());
		} catch (DuplicateInstanceException e) {
			registrationForm.recordError(userStoryNameField, messages.get("error-userStoryNameAlreadyExists"));
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}

		return null;

	}
}
