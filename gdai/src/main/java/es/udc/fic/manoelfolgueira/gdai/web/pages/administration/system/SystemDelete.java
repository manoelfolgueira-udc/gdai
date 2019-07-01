package es.udc.fic.manoelfolgueira.gdai.web.pages.administration.system;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete a System web page
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   SystemDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class SystemDelete {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
	@Inject
    private Messages messages;
	
	@Inject
	private SystemService systemService;
	
	@Property
	private String infoSystemDelete;
	
	private Long systemId;
	
	@Property
	private System system;
	
	@Component
	private Form deleteForm;
    
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Locale locale;
	
	void setupRender() {
		infoSystemDelete = messages.format("surePerformAction", messages.get("info-delete-system"));
		try {
			system = systemService.findSystem(systemId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void onActivate(Long systemId) {
		this.systemId = systemId;
	}
	
	Long onPassivate() {
        return systemId;
    }
	
	public String getSystemDeleteInfo() {
		return this.infoSystemDelete;
	}
	
	Object onValidateFromDeleteForm() {
		try {
			systemService.remove(systemId);
			return pageRenderLS.createPageRenderLinkWithContext("administration/system/SystemDeleted", systemId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages
                    .get("error-systemDoesNotExist") + system.getSystemName());
			return null;
		}
	}
	
}
