package es.udc.fic.manoelfolgueira.gdai.web.pages.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;
import es.udc.fic.manoelfolgueira.gdai.web.util.Utils;

/**
 * Web page that allow users to see their own profile
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ViewProfile.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ViewProfile {

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;
    
    @Property
    private User user;
    
    @Property
    private String hireDateParsed = null;
    
    @Property
    private String dateOfBirthParsed = null;
    
    @Inject
    private Locale locale;

    void onActivate() throws InstanceNotFoundException {
    	
    	if (userSession != null) {

	        user = userService.findUser(userSession
	                .getUserId());
	        
	        if (user.getHireDate() == null) hireDateParsed = "";
	        else {
	        	Calendar cal = user.getHireDate();
	            cal.add(Calendar.DATE, 1);
	            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	            hireDateParsed = f.format(cal.getTime());
	        }
	        
	        if (user.getDateOfBirth() == null) dateOfBirthParsed = "";
	        else {
	        	Calendar cal = user.getHireDate();
	            cal.add(Calendar.DATE, 1);
	            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	            dateOfBirthParsed = f.format(cal.getTime());
	        }

	        
    	}

    }
    
    public boolean getIsUserMale() {
    	if (user.getGender() == null) return true;
    	else return user.getGender().equals("M");
    }
    
    public String getGroupName() {
    	return user.getGroup() == null ? "" : user.getGroup().getGroupName();
    }
    
    public String getHireDateDBValue() {
    	
    	return user.getHireDate().getTime() == null ? "" : Utils.getFormattedDate(user.getHireDate().getTime(), locale);
    }
    
    public String getDateOfBirthDBValue() {
    	return user.getDateOfBirth() == null ? "" : Utils.getFormattedDate(user.getDateOfBirth().getTime(), locale);
    }
    
    public String getIsManagerUI() {
    	return user.getIsManager() ? "Y" : "N";
    }

}