package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allows users to change some of their profile information and
 * settings
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UpdateProfile.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UpdateProfile {

	@Property
	private String firstName;

	@Property
	private String lastName;

	@Property
	private String email;

	@Property
	private String phoneNumber;

	@Property
	private String avatarUrl;

	@Property
	private String groupName;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	@Property
	private UserDetails userDetails;

	@Property
	private String loginName;

	@Property
	private String genderValue;

	@Component
	private RadioGroup gender;

	@Property
	private String hireDate;

	@Property
	private String dateOfBirth;

	@Property
	private String expirationDate;

	@Inject
	private GroupService groupService;

	@Property
	private Group group;

	@Component
	private Form updateProfileForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private String isManager;

	void onPrepareForRender() throws InstanceNotFoundException {

		userDetails = userService.findUser(userSession.getUserId());

		loginName = userDetails.getLoginName();
		firstName = userDetails.getFirstName();
		lastName = userDetails.getLastName();
		email = userDetails.getEmail();
		phoneNumber = userDetails.getPhoneNumber();

		hireDate = getHireDateDBValue();
		dateOfBirth = getDateOfBirthDBValue();
		expirationDate = getExpirationDateDBValue();

		avatarUrl = userDetails.getAvatarUrl() == null ? "" : userDetails.getAvatarUrl();

		isManager = userDetails.getIsManager() ? "Y" : "N";

		groupName = userDetails.getGroup().getGroupName();

	}

	void onValidateFromUpdateProfileForm() {

		if (!updateProfileForm.isValid()) {
			return;
		}

		try {

		} catch (Exception e) {
			e.printStackTrace();
			updateProfileForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() throws InstanceNotFoundException {

		userDetails = userService.findUser(userSession.getUserId());

		userService.updateUserDetails(userSession.getUserId(),
				new UserDetails(userDetails.getUserId(), loginName, firstName, lastName, genderValue, email, phoneNumber, avatarUrl,
						userDetails.getHireDate(), userDetails.getDateOfBirth(), userDetails.getExpirationDate(), userDetails.getIsManager(),
						userDetails.getGroup()));
		userSession.setLoginName(loginName);

		return ViewProfile.class;

	}

	public String getHireDateDBValue() {
		return Utils.getFormattedDate(userDetails.getHireDate().getTime(), locale);
	}

	public String getDateOfBirthDBValue() {
		return Utils.getFormattedDate(userDetails.getDateOfBirth().getTime(), locale);
	}

	public String getExpirationDateDBValue() {
		return Utils.getFormattedDate(userDetails.getExpirationDate().getTime(), locale);
	}

	public boolean getTestMale() {
		return userDetails.getGender().equals("M");
	}

}