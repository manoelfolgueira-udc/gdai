package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.sprint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Sprints
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   SprintRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SprintRegister {
	
    @Property
    private String sprintName;
    
    @Component(id = "sprintName")
    private TextField sprintNameField;
    
    @Property
	private String sprintStart;
    
    @Property
	private String sprintEnd;
    
    @SessionState(create=false)
    private UserSession userSession;
    
    @Inject
    private SprintService sprintService;

    @Component
    private Form registrationForm;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;

    @Property
    private String result = null;
	
	@Inject
	private PageRenderLinkSource pageRenderLS;

    Object onValidateFromRegistrationForm() {
    	
    	Sprint sprint;

        if (!registrationForm.isValid()) {
            return null;
        }

        try {
        	
        	Calendar calSprintStart = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			if (sprintStart != null)
				calSprintStart.setTime(sdf.parse(sprintStart));
			else
				calSprintStart = null;
			
			Calendar calSprintEnd = Calendar.getInstance();
			if (sprintEnd != null)
				calSprintEnd.setTime(sdf.parse(sprintEnd));
			else
				calSprintEnd = null;
        	
        	Calendar calCreationDate = Calendar.getInstance();
        	sprint = sprintService.registerSprint(sprintName, new SprintDetails(sprintName, calSprintStart, calSprintEnd, calCreationDate, null));
        	result = messages.getFormatter("result-SprintRegister-ok").format(sprintName);
        	return pageRenderLS.createPageRenderLinkWithContext("administration/sprint/SprintCreated", sprint.getSprintId());
        } catch (DuplicateInstanceException e) {
            registrationForm.recordError(sprintNameField, messages
                    .get("error-sprintNameAlreadyExists"));
        } catch (Exception e) {
        	e.printStackTrace();
        	registrationForm.recordError(messages
                    .get("error-unexpectedError"));
        }
		return null;

    }
    
    
    String onPassivate() {
    	return result;
    }
}
