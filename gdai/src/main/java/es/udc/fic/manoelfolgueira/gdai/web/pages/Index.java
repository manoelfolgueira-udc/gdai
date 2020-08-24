package es.udc.fic.manoelfolgueira.gdai.web.pages;

import org.apache.tapestry5.annotations.SessionState;

import es.udc.fic.manoelfolgueira.gdai.web.pages.user.ControlPanel;
import es.udc.fic.manoelfolgueira.gdai.web.pages.user.Login;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Index Web Page that forwards the user to the ControlPanel or the Login Web
 * Pages whether they are logged in GDAI or not respectively.
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file Index.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Index {

	@SessionState(create = false)
	private UserSession userSession;

	Object onActivate() {

		if (userSession != null)
			return ControlPanel.class;
		else
			return Login.class;

	}

}
