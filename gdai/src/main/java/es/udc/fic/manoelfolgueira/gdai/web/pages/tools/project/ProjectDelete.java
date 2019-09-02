package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete a project web page
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ProjectDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProjectDelete {
	
	@Inject
	private PageRenderLinkSource pageRenderLS;	
	
	@Inject
    private Messages messages;
	
	@Inject
	private ProjectService projectService;
	
	@Property
	private String infoDeleteProject;
	
	private Long projectId;
	
	@Property
	private Project project;
	
	@Component
	private Form deleteForm;
    
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Locale locale;
	
	void setupRender() {
		infoDeleteProject = messages.format("surePerformAction", messages.get("info-delete-project"));
		try {
			project = projectService.findProject(projectId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void onActivate(Long projectId) {
		this.projectId = projectId;
	}
	
	Long onPassivate() {
        return projectId;
    }
	
	public String getDeleteProjectInfo() {
		return this.infoDeleteProject;
	}
	
	Object onValidateFromDeleteForm() {
		try {
			projectService.removeProject(projectId);
			return pageRenderLS.createPageRenderLinkWithContext("tools/project/ProjectDeleted", projectId);
		} catch (InstanceNotFoundException e) {
			deleteForm.recordError(messages
                    .get("error-projectDoesNotExist") + project.getProjectName());
			return null;
		}
	}
	
}
