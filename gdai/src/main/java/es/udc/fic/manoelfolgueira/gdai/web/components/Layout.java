package es.udc.fic.manoelfolgueira.gdai.web.components;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.CookiesManager;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

@Import(library = {"tapestry5/bootstrap/js/collapse.js", "tapestry5/bootstrap/js/dropdown.js"},
        stylesheet= {"tapestry5/bootstrap/css/bootstrap-theme.css", "context:css/gdai_layout.css"})
public class Layout {

    @Property
    @Parameter(required = true, defaultPrefix = "message")
    private String title;
    
    @Parameter(defaultPrefix = "literal")
    private Boolean showTitleInBody;

    @Property
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private Cookies cookies;
    
    public boolean getShowTitleInBody() {
    	
    	if (showTitleInBody == null) {
    		return true;
    	} else {
    		return showTitleInBody;
    	}
    	
    }
    
    @AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
    Object onActionFromLogout() {
        userSession = null;
        CookiesManager.removeCookies(cookies);
        return Index.class;
    }

}
