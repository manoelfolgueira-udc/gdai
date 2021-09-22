package es.udc.fic.manoelfolgueira.gdai.web.pages.group;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets User see any Group
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GroupView.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class GroupView {

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private GroupService groupService;

	@Property
	private GroupDetails groupDetails;

	@Property
	private String expirationDate = null;

	@Inject
	private Locale locale;
	
	@Property
	private String goBackUrl;
	
	void onActivate(Long groupId, String goBack) throws InstanceNotFoundException {
		goBackUrl = (goBack.indexOf("tools") > -1 ? goBack.substring(goBack.indexOf("tools")) : "administration/group/management");
		groupDetails = groupService.findGroup(groupId);
	}

}