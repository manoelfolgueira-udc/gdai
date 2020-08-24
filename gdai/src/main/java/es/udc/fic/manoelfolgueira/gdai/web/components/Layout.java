package es.udc.fic.manoelfolgueira.gdai.web.components;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.CookiesManager;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Defines the behaviour of the layout of the application
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file Layout.java
 */
@Import(library = { "tapestry5/bootstrap/js/collapse.js", "tapestry5/bootstrap/js/dropdown.js",
		"context:js/gdai.js" }, stylesheet = { "tapestry5/bootstrap/css/bootstrap-theme.css",
				"context:css/gdai_layout.css" })
public class Layout {

	@Property
	@Parameter(required = true, defaultPrefix = "message")
	private String title;

	@Parameter(defaultPrefix = "literal")
	private Boolean showTitleInBody;

	@Property
	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private Cookies cookies;

	@Inject
	private UserService userService;

	/**
	 * Title configuration
	 * 
	 * @return whether the title will be displayed
	 */
	public boolean getShowTitleInBody() {
		return (showTitleInBody == null) ? true : showTitleInBody;
	}

	/**
	 * Logs out the user
	 * 
	 * @return the Index view
	 */
	@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
	Object onActionFromLogout() {
		userSession = null;
		CookiesManager.removeCookies(cookies);
		return Index.class;
	}

	/**
	 * Utility method to know if an user has the role of Administrator
	 * 
	 * @return a boolean to tell if the logged user is an Administrator
	 */
	public boolean getIsUserAdministrator() {
		return (userSession == null) ? false : userSession.isAdministrator();
	}

	public boolean getIsManager() {
		try {
			return userService.findUser(userSession.getUserId()).getIsManager();
		} catch (InstanceNotFoundException e) {
			return false;
		}
	}

}
