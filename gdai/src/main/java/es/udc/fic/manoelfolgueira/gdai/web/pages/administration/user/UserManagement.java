package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;

/**
 * Web page that allows User Management
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   UserManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UserManagement {
	@Property
    private User user;

    @Property
    private String loginName;
    
    @Property
    private String creationDate;
    
    @Property
    private String expirationDate;
    
    @Inject
    private UserService userService;
    
    @Inject
    private SelectModelFactory selectModelFactory;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;
    
    @Property
    List<User> users;
    
    void setupRender() {
    	// A GridDataSource is not provided due to the little ammount of groups which are going to be in the app at a time
        users = userService.findAllSortedByName();
    }
}
