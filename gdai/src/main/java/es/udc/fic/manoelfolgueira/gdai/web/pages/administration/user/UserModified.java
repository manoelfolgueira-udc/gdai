package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that shows the result of a User modification
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   UserModified.java
 */
public class UserModified {

	@Property
	private Long userId;

	@SessionState(create=false)
	private UserSession userSession;

	Long onPassivate() {
		return userId;
	}

	void onActivate(Long userId) {
		this.userId = userId;
	}

	public String getBackLink() {

		String backLink = !userSession.isAdministrator()
				? "user/controlpanel"
						: "administration/user/management";

		return backLink;
	}

}
