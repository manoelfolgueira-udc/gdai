package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;

/**
 * Web page that allows User Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserManagement {
	@Property
	private UserDetails userDetails;

	@Property
	private String loginName;

	@Property
	private String creationDate;

	@Property
	private String expirationDate;

	@Inject
	private UserService userService;

	@Inject
	private SelectModelFactory selectModelFactory;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	List<UserDetails> usersDetails;
	
	@Inject
	private HttpServletRequest servletRequest;
	
	public String getCurrentPath() {
		return servletRequest.getRequestURL().toString();
	}

	void setupRender() {
		// A GridDataSource is not provided due to the little ammount of groups which
		// are going to be in the app at a time
		usersDetails = userService.findAllSortedByName();
	}
}
