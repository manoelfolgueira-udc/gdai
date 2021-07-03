package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.userstory;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserStoryDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allows user story Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserStoryManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserStoryManagement {

	@Property
	private UserStoryDetails userStoryDetails;

	@Property
	private String UserStoryName;

	@Property
	private String creationDate;

	@Property
	private String expirationDate;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserStoryService UserStoryService;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	List<UserStoryDetails> userStoriesDetails;

	@Inject
	private UserService userService;

	@Property
	private boolean isManager;

	void setupRender() {
		// A GridDataSource is not provided due to the little ammount of UserStorys
		// which are going to be in the app at a time
		userStoriesDetails = UserStoryService.findAllOrderedByUserStoryName();

		try {
			isManager = userService.findUser(userSession.getUserId()).getIsManager();
		} catch (InstanceNotFoundException e) {
			isManager = false;
		}
	}

	public String getUserStoryCreationDateFormatted() {

		return (userStoryDetails.getCreationDate() != null)
				? Utils.getFormattedDate(userStoryDetails.getCreationDate().getTime(), locale)
				: "";
	}

}
