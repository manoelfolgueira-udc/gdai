package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.upload.services.UploadedFile;

import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectService;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SprintEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.UserStoryEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify a project
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProjectModify {

	// The activation context
	private Long sprintId;

	// The activation context
	private Long systemId;

	// The activation context
	private Long userStoryId;

	@Property
	private Long projectId;

	@Property
	private String projectName;

	@Component(id = "projectName")
	private TextField projectNameField;

	@Property
	private String projectDescription;

	@Component(id = "projectDescription")
	private TextField projectDescriptionField;

	@Property
	private String requirementsPath;

	@SessionState(create = false)
	private UserSession userSession;

	@Property
	private UploadedFile projectReqs;

	@Inject
	private ProjectService projectService;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private SprintService sprintService;

	@Inject
	private SystemService systemService;

	@Inject
	private UserStoryService userStoryService;

	@Inject
	private UserService userService;

	@Property
	private String result = null;

	@Property
	private SprintDetails sprintDetails;

	@Property
	private SystemDetails systemDetails;

	@Property
	private UserStoryDetails userStoryDetails;

	@Property
	private SelectModel sprintsModel;

	@Property
	private SelectModel systemsModel;

	@Property
	private SelectModel userStoriesModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	private ProjectDetails projectDetails;

	@Property
	private List<SprintDetails> sprintsDetailsSelected = new LinkedList<>();

	@Inject
	private PageRenderLinkSource pageRenderLS;

	void onActivate(Long projectId) {
		this.projectId = projectId;
	}

	Long onPassivate() {
		return projectId;
	}

	void setupRender() {
		try {

			projectDetails = projectService.findProject(projectId);

			projectName = projectDetails.getProjectName();
			projectDescription = projectDetails.getProjectDescription();

			sprintsDetailsSelected = projectDetails.getSprintsDetails();
			userStoryDetails = projectDetails.getUserStoryDetails();
			systemDetails = projectDetails.getSystemDetails();

		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

		try {
			Calendar calCreationDate = Calendar.getInstance();
			UserDetails createdBy = userService.findUser(userSession.getUserId());
			LinkedList<SprintDetails> sprints = new LinkedList<>();
			sprints.add(sprintDetails);

			projectService.updateProjectDetails(projectId, new ProjectDetails(projectDetails.getProjectId(), projectName, projectDescription,
					calCreationDate, requirementsPath, createdBy, systemDetails, sprints, userStoryDetails));
			result = messages.getFormatter("result-ProjectRegister-ok").format(projectName);
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() {

		if (projectReqs != null) {

			try {
				projectDetails = projectService.findProject(projectId);
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File copied = new File(
					Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS),
					projectDetails.getGDAICode() + "_" + projectReqs.getFileName());
			projectReqs.write(copied);

		}

		return pageRenderLS.createPageRenderLinkWithContext("tools/project/projectmodified", projectId);
	}

	public SprintEncoder getSprintEncoder() {
		return new SprintEncoder(sprintService);
	}

	public SystemEncoder getSystemEncoder() {
		return new SystemEncoder(systemService);
	}

	public UserStoryEncoder getUserStoryEncoder() {
		return new UserStoryEncoder(userStoryService);
	}

	void onPrepare() {

		List<SprintDetails> sprints = sprintService.findAllOrderedBySprintName(SortingType.DESC);

		if (sprintId != null) {
			sprintDetails = findSprintInList(sprintId, sprints);
		} else {
			sprintDetails = sprints.get(0); // we get the current sprintDetails by default
		}

		sprintsModel = selectModelFactory.create(sprints, "sprintName");

		List<UserStoryDetails> userStories = userStoryService.findAllOrderedByUserStoryName();

		if (userStoryId != null) {
			userStoryDetails = findUserStoryInList(userStoryId, userStories);
		}

		userStoriesModel = selectModelFactory.create(userStories, "userStoryName");

		List<SystemDetails> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			systemDetails = findSystemDetailsInList(systemId, systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");
	}

	private SprintDetails findSprintInList(Long sprintId, List<SprintDetails> sprints) {
		for (SprintDetails s : sprints) {
			if (s.getSprintId().equals(sprintId)) {
				return s;
			}
		}
		return null;
	}

	private UserStoryDetails findUserStoryInList(Long userStoryId, List<UserStoryDetails> userStories) {
		for (UserStoryDetails u : userStories) {
			if (u.getUserStoryId().equals(userStoryId)) {
				return u;
			}
		}
		return null;
	}

	private SystemDetails findSystemDetailsInList(Long systemId, List<SystemDetails> systems) {
		for (SystemDetails s : systems) {
			if (s.getSystemId().equals(systemId)) {
				return s;
			}
		}
		return null;
	}

}
