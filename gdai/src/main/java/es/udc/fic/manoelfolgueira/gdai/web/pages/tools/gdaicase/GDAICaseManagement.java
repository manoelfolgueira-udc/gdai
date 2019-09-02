package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.gdaicase;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.gdaicase.GDAICase;
import es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows gdaiCase Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GDAICaseManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GDAICaseManagement {
	
	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	@Inject
	private GDAICaseService gdaiCaseService;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private List<GDAICase> gdaiCasesSearch;

	@Property
	private GDAICase gdaiCase = null;
	
	void setupRender() {
		try {
			User user = userService.findUser(userSession.getUserId());
			gdaiCasesSearch = gdaiCaseService.findByGroup(user.getGroup().getGroupId());
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
