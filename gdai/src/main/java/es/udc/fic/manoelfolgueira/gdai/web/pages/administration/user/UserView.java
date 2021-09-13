package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that lets Administrator see any user profile
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserView.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserView {

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	@Property
	private UserDetails userDetails;

	@Property
	private String hireDateParsed = null;

	@Property
	private String dateOfBirthParsed = null;

	@Inject
	private Locale locale;

	void onActivate(Long userId) throws InstanceNotFoundException {

		userDetails = userService.findUser(userId);

		if (userDetails.getHireDate() == null)
			hireDateParsed = "";
		else {
			Calendar cal = userDetails.getHireDate();
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			hireDateParsed = f.format(cal.getTime());
		}

		if (userDetails.getDateOfBirth() == null)
			dateOfBirthParsed = "";
		else {
			Calendar cal = userDetails.getDateOfBirth();
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			dateOfBirthParsed = f.format(cal.getTime());
		}

	}

	public boolean getIsUserMale() {
		if (userDetails.getGender() == null)
			return true;
		else
			return userDetails.getGender().equals("M");
	}

	public String getGroupName() {
		return userDetails.getGroup() == null ? "" : userDetails.getGroup().getGroupName();
	}

	public String getHireDateDBValue() {

		return userDetails.getHireDate().getTime() == null ? "" : Utils.getFormattedDate(userDetails.getHireDate().getTime(), locale);
	}

	public String getDateOfBirthDBValue() {
		return userDetails.getDateOfBirth() == null ? "" : Utils.getFormattedDate(userDetails.getDateOfBirth().getTime(), locale);
	}
	
	public String getExpirationDate() {
		return userDetails.getExpirationDate() == null ? "" : Utils.getFormattedDate(userDetails.getExpirationDate().getTime(), locale);
	}

	public String getIsManagerUI() {
		return userDetails.getIsManager() ? "Y" : "N";
	}

}