package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ModifyUser {

	@Inject
	private PageRenderLinkSource pageRenderLS;	

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

	@SessionState(create=false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	@Property
	private User user;

	@Property
	private String loginName;

	@Property
	private String genderValue;

	@Component
	private RadioGroup gender;

	@Property
	private Date hireDate;

	@Property
	private Date dateOfBirth;

	@Property
	private Date expirationDate;

	@Inject
	private GroupService groupService;

	@Property
	private SelectModel groupsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	@Property
	private Group group;

	@Component
	private Form updateProfileForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private Long userId;

	private Calendar calHireDate = Calendar.getInstance();
	private Calendar calDateOfBirth = Calendar.getInstance();
	private Calendar calExpirationDate = Calendar.getInstance();

	void onActivate(Long userId) {
		this.userId = userId;
	}

	Long onPassivate() {
		return userId;
	}

	void onPrepare() throws InstanceNotFoundException {

		user = userService.findUserProfile(userId);

		loginName = user.getLoginName();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		phoneNumber = user.getPhoneNumber();

		hireDate = user.getHireDate().getTime();
		dateOfBirth = user.getDateOfBirth().getTime();
		expirationDate = user.getExpirationDate().getTime();

		avatarUrl = user.getAvatarUrl() == null ? "" : user.getAvatarUrl();

		groupName = user.getGroup().getGroupName();

		List<Group> groups = groupService.findAllOrderedByGroupNameIC();

		Long groupId = user.getGroup().getGroupId();
		if (groupId != null) {
			group = findGroupInList(groupId, groups);
		}

		groupsModel = selectModelFactory.create(groups, "groupName");
	}

	void onValidateFromUpdateProfileForm() {

		if (!updateProfileForm.isValid()) {
			return;
		}

		try {

			calHireDate.setTime(hireDate);
			calDateOfBirth.setTime(dateOfBirth);
			calExpirationDate.setTime(expirationDate);
			
			// Modifying myself
			if (userId.equals(userSession.getUserId())) {
				userSession.setAdministrator(group.getGroupName().equals(
						Config.getInstance().getProperties().getProperty(Config.ADMINISTRATORS_GROUP_NAME)));
			}

			userService.updateUserDetails(
					userId, new UserDetails(loginName, firstName, lastName, genderValue, email, phoneNumber,
							avatarUrl, calHireDate, calDateOfBirth, calExpirationDate, group));

		} catch (Exception e) {
			e.printStackTrace();
			updateProfileForm.recordError(messages
					.get("error-unexpectedError"));
		}

	}

	Object onSuccess() throws InstanceNotFoundException {
		return pageRenderLS.createPageRenderLinkWithContext("administration/user/UserModified", userId);
	}

	public String getHireDateDBValue() {
		return Utils.getFormattedDate(user.getHireDate().getTime(), locale);
	}

	public String getDateOfBirthDBValue() {
		return Utils.getFormattedDate(user.getDateOfBirth().getTime(), locale);
	}

	public String getExpirationDateDBValue() {
		return Utils.getFormattedDate(user.getExpirationDate().getTime(), locale);
	}

	public boolean getTestMale() {
		return user.getGender().equals("M");
	}

	public GroupEncoder getGroupEncoder() {
		return new GroupEncoder(groupService);
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
