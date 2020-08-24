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

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allows an Administrator modify a User
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserModify {

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
	private GroupDetails groupDetails;

	@Component
	private Form updateProfileForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private Long userId;

	@Property
	private Boolean isManager = false;;

	private Calendar calHireDate = Calendar.getInstance();
	private Calendar calDateOfBirth = Calendar.getInstance();
	private Calendar calExpirationDate = Calendar.getInstance();

	void onActivate(Long userId) {
		this.userId = userId;
	}

	Long onPassivate() {
		return userId;
	}

	void setupRender() throws InstanceNotFoundException {
		userDetails = userService.findUser(userId);

		loginName = userDetails.getLoginName();
		firstName = userDetails.getFirstName();
		lastName = userDetails.getLastName();
		email = userDetails.getEmail();
		phoneNumber = userDetails.getPhoneNumber();

		hireDate = userDetails.getHireDate().getTime();
		dateOfBirth = userDetails.getDateOfBirth().getTime();
		expirationDate = userDetails.getExpirationDate().getTime();

		avatarUrl = userDetails.getAvatarUrl() == null ? "" : userDetails.getAvatarUrl();

		groupName = userDetails.getGroup().getGroupName();

		isManager = userDetails.getIsManager();

		groupDetails = userDetails.getGroup();
	}

	void onPrepare() throws InstanceNotFoundException {

		List<GroupDetails> groupsDetails = groupService.findAllOrderedByGroupName();

		Long groupId = userService.findUser(userId).getGroup().getGroupId();
		if (groupId != null) {
			groupDetails = findGroupInList(groupId, groupsDetails);
		}

		groupsModel = selectModelFactory.create(groupsDetails, "groupName");
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
				userSession.setAdministrator(groupDetails.getGroupName().equals(Config.getInstance().getProperties()
						.getProperty(ConfigPropertyKeys.ADMINISTRATORS_GROUP_NAME)));
			}

			userService.updateUserDetails(userId,
					new UserDetails(userId, loginName, firstName, lastName, genderValue, email, phoneNumber, avatarUrl,
							calHireDate, calDateOfBirth, calExpirationDate, isManager, groupDetails));

		} catch (Exception e) {
			e.printStackTrace();
			updateProfileForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() throws InstanceNotFoundException {
		return pageRenderLS.createPageRenderLinkWithContext("administration/user/UserModified", userId);
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

	public GroupEncoder getGroupEncoder() {
		return new GroupEncoder(groupService);
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
