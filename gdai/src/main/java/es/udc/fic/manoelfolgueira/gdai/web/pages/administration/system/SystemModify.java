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

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify a System
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SystemModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemModify {

	// The activation context
	private Long groupId;

	@Inject
	private PageRenderLinkSource pageRenderLS;

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

	private Long systemId;

	@Property
	private SystemDetails systemDetails;

	void onActivate(Long systemId) {
		this.systemId = systemId;
	}

	Long onPassivate() {
		return systemId;
	}

	void setupRender() throws InstanceNotFoundException {
		systemDetails = systemService.findSystem(systemId);

		systemName = systemDetails.getSystemName();
		systemDescription = systemDetails.getSystemDescription();
		expirationDate = systemDetails.getExpirationDate().getTime();
		groupDetails = systemDetails.getGroup();
	}

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

		try {

			systemDetails = systemService.findSystem(systemId);

			Calendar calExpirationDate = Calendar.getInstance();
			calExpirationDate.setTime(expirationDate);

			systemService.updateSystemDetails(systemId, new SystemDetails(systemDetails.getSystemId(), systemName,
					systemDescription, systemDetails.getCreationDate(), calExpirationDate, groupDetails));

			result = messages.getFormatter("result-SystemRegister-ok").format(systemName);

		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() {
		return pageRenderLS.createPageRenderLinkWithContext("administration/system/SystemModified", systemId);
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
