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
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
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
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   UserRegister.java
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

	@SessionState(create=false)
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
	private Group group;

	@Component
	private Form registrationForm;

	@Component(id = "loginName")
	private TextField loginNameField;

	@Component(id = "password")
	private PasswordField passwordField;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	void onValidateFromRegistrationForm() {

		groupId = group == null ? null : group.getGroupId();

		if (!registrationForm.isValid()) {
			return;
		}

		if (!password.equals(retypePassword)) {
			registrationForm.recordError(passwordField, messages
					.get("error-passwordsDontMatch"));
		} else {

			try {

				Calendar calHireDate = Calendar.getInstance();
				Calendar calDateOfBirth = Calendar.getInstance();
				Calendar calExpirationDate = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				if (hireDate != null) calHireDate.setTime(sdf.parse(hireDate)); else calHireDate = null;
				if (dateOfBirth != null) calDateOfBirth.setTime(sdf.parse(dateOfBirth)); else calDateOfBirth = null;
				if (expirationDate != null) calExpirationDate.setTime(sdf.parse(expirationDate)); else calExpirationDate = null;

				userService.registerUser(loginName, password,
						new UserDetails(loginName, firstName, lastName, genderValue, email, phoneNumber, avatarUrl, calHireDate, calDateOfBirth, calExpirationDate, group));

			} catch (DuplicateInstanceException e) {
				registrationForm.recordError(loginNameField, messages
						.get("error-loginNameAlreadyExists"));
			} catch (Exception e) {
				registrationForm.recordError(messages
						.get("error-unexpectedError"));
			}

		}

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

		List<Group> groups = groupService.findAllOrderedByGroupName();

		if (groupId != null) {
			group = findGroupInList(groupId, groups);
		}

		groupsModel = selectModelFactory.create(groups, "groupName");
	}

	private Group findGroupInList(Long groupId, List<Group> groups) {
		for (Group g : groups) {
			if (g.getGroupId().equals(groupId)) {
				return g;
			}
		}
		return null;
	}

}
