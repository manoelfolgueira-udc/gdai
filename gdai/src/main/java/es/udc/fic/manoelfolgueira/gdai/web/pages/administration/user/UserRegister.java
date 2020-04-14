package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Users
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserRegister {

	// The activation context
	private Long groupId;

	@Property
	private String loginName;

	@Property
	private String password;

	@Property
	private String retypePassword;

	@Property
	private String firstName;

	@Property
	private String lastName;

	@Property
	private String genderValue;

	@Component
	private RadioGroup gender;

	@Property
	private String email;

	@Property
	private String phoneNumber;

	@Property
	private String avatarUrl;

	@Property
	private String hireDate;

	@Property
	private String dateOfBirth;

	@Property
	private String expirationDate;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	@Inject
	private GroupService groupService;

	@Property
	private SelectModel groupsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	@Property
	private GroupDetails groupDetails;

	@Component
	private Form registrationForm;

	@Component(id = "loginName")
	private TextField loginNameField;

	@Component(id = "password")
	private PasswordField passwordField;

	@Property
	private Boolean isManager = false;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	Object onValidateFromRegistrationForm() {

		groupId = groupDetails == null ? null : groupDetails.getGroupId();

		UserDetails userDetails;

		if (!registrationForm.isValid()) {
			return null;
		}

		if (!password.equals(retypePassword)) {
			registrationForm.recordError(passwordField, messages.get("error-passwordsDontMatch"));
		} else {

			try {

				Calendar calHireDate = Calendar.getInstance();
				Calendar calDateOfBirth = Calendar.getInstance();
				Calendar calExpirationDate = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
				if (hireDate != null)
					calHireDate.setTime(sdf.parse(hireDate));
				else
					calHireDate = null;
				if (dateOfBirth != null)
					calDateOfBirth.setTime(sdf.parse(dateOfBirth));
				else
					calDateOfBirth = null;
				if (expirationDate != null)
					calExpirationDate.setTime(sdf.parse(expirationDate));
				else
					calExpirationDate = null;

				userDetails = userService.registerUser(password,
						new UserDetails(null, loginName, firstName, lastName, genderValue, email, phoneNumber, avatarUrl,
								calHireDate, calDateOfBirth, calExpirationDate, isManager, groupDetails));

				return pageRenderLS.createPageRenderLinkWithContext("administration/user/UserCreated",
						userDetails.getUserId());
			} catch (DuplicateInstanceException e) {
				registrationForm.recordError(loginNameField, messages.get("error-loginNameAlreadyExists"));
			} catch (Exception e) {
				registrationForm.recordError(messages.get("error-unexpectedError"));
			}

		}
		return null;

	}

	Object onSuccess() {
		return UserManagement.class;
	}

	public GroupEncoder getGroupEncoder() {
		return new GroupEncoder(groupService);
	}

	Long onPassivate() {
		return groupId;
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
