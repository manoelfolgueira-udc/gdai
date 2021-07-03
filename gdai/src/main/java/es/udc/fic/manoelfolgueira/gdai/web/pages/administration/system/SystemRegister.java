package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Systems
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SystemRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemRegister {

	// The activation context
	private Long groupId;

	@Property
	private String systemName;

	@Component(id = "systemName")
	private TextField systemNameField;

	@Property
	private String systemDescription;

	@Component(id = "systemDescription")
	private TextField systemDescriptionField;

	@Property
	private Date expirationDate;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private SystemService systemService;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private GroupService groupService;

	@Property
	private String result = null;

	@Property
	private GroupDetails groupDetails;

	@Property
	private SelectModel groupsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	Object onValidateFromRegistrationForm() {

		SystemDetails systemDetails;

		if (!registrationForm.isValid()) {
			return null;
		}

		try {
			Calendar calCreationDate = Calendar.getInstance();

			Calendar calExpirationDate = Calendar.getInstance();
			calExpirationDate.setTime(expirationDate);

			systemDetails = systemService.registerSystem(new SystemDetails(null, systemName, systemDescription,
					calCreationDate, calExpirationDate, groupDetails));
			result = messages.getFormatter("result-SystemRegister-ok").format(systemName);
			return pageRenderLS.createPageRenderLinkWithContext("administration/system/SystemCreated",
					systemDetails.getSystemId());
		} catch (DuplicateInstanceException e) {
			registrationForm.recordError(systemNameField, messages.get("error-systemNameAlreadyExists"));
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}
		return null;

	}

	Object onSuccess() {
		return SystemManagement.class;
	}

	String onPassivate() {
		return result;
	}

	public GroupEncoder getGroupEncoder() {
		return new GroupEncoder(groupService);
	}

	void onPrepare() {

		List<GroupDetails> groupsDetails = groupService.findAllOrderedByGroupName();

		if (groupId != null) {
			groupDetails = findGroupInList(groupId, groupsDetails);
		}

		groupsModel = selectModelFactory.create(groupsDetails, "groupName");
	}

	private GroupDetails findGroupInList(Long groupId, List<GroupDetails> groupsDetails) {
		for (GroupDetails g : groupsDetails) {
			if (g.getGroupId().equals(groupId)) {
				return g;
			}
		}
		return null;
	}

}
