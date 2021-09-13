package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.group;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows Group Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GroupManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GroupManagement {

	// Properties

	@Property
	private GroupDetails groupDetails;

	@Property
	private String groupName;

	@Property
	private String creationDate;

	@Property
	private String expirationDate;

	@Property
	List<GroupDetails> groupsDetails;

	// Services

	@Inject
	private GroupService groupService;

	// Extra Components

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;
	
	@Inject
	private HttpServletRequest servletRequest;
	
	public String getCurrentPath() {
		return servletRequest.getRequestURL().toString();
	}

	// Methods

	void setupRender() {
		// A GridDataSource is not provided due to the little ammount of groups which
		// are going to be in the app at a time
		groupsDetails = groupService.findAllOrderedByGroupName();

	}

}
