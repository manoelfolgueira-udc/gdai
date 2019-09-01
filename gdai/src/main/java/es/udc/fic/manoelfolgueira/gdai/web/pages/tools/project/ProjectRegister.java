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
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.upload.services.UploadedFile;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectService;
import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SprintEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.UserStoryEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new Projects
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProjectRegister {

	// The activation context
	private Long sprintId;

	// The activation context
	private Long systemId;

	// The activation context
	private Long userStoryId;

	@Property
	private String projectName;

	@Component(id = "projectName")
	private TextField projectNameField;

	@Property
	private String projectDescription;

	@Component(id = "projectDescription")
	private TextField projectDescriptionField;

	@Property
	private String targetDate;

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
	private Sprint sprint;

	@Property
	private System system;

	@Property
	private UserStory userStory;

	@Property
	private SelectModel sprintsModel;

	@Property
	private SelectModel systemsModel;

	@Property
	private SelectModel userStoriesModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	private Project project;

	@Property
	private LinkedList<Sprint> sprintsSelected = new LinkedList<>();

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}
		
		if(!projectReqs.getFileName().endsWith(".pdf")) {
			registrationForm.recordError(messages.get("onlyPDF"));
		}

	}

	Object onSuccess() {

		Calendar calCreationDate = Calendar.getInstance();
		User createdBy;
		try {
			createdBy = userService.findUser(userSession.getUserId());
			project = projectService.createProject(new ProjectDetails(projectName, projectDescription, calCreationDate,
					null, createdBy, system, sprintsSelected, userStory));
		} catch (DuplicateInstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File copied = new File(
				Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS),
				project.getGDAICode() + "_" + projectReqs.getFileName());
		projectReqs.write(copied);

		try {
			
			String requirementsPath = Config.getInstance().getProperties()
					.getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS) + "/" + project.getGDAICode() + "_"
					+ projectReqs.getFileName();
			
			projectService.updateProjectDetails(project.getProjectId(),
					new ProjectDetails(project.getProjectName(), project.getProjectDescription(), project.getCreationDate(),
							requirementsPath, project.getCreatedBy(), project.getSystem(), project.getSprints(),
							project.getUserStory()));
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result = messages.getFormatter("result-ProjectRegister-ok").format(projectName);

		return ProjectManagement.class;
	}

	String onPassivate() {
		return result;
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

		List<Sprint> sprints = sprintService.findAllOrderedBySprintName(SortingType.DESC);

		if (sprintId != null) {
			sprint = findSprintInList(sprintId, sprints);
		}

		sprintsModel = selectModelFactory.create(sprints, "bSprintName");

		List<UserStory> userStories = userStoryService.findAllOrderedByUserStoryName();

		if (userStoryId != null) {
			userStory = findUserStoryInList(userStoryId, userStories);
		}

		userStoriesModel = selectModelFactory.create(userStories, "userStoryName");

		List<System> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			system = findSystemInList(systemId, systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");
	}

	private Sprint findSprintInList(Long sprintId, List<Sprint> sprints) {
		for (Sprint s : sprints) {
			if (s.getSprintId().equals(sprintId)) {
				return s;
			}
		}
		return null;
	}

	private UserStory findUserStoryInList(Long userStoryId, List<UserStory> userStories) {
		for (UserStory u : userStories) {
			if (u.getUserStoryId().equals(userStoryId)) {
				return u;
			}
		}
		return null;
	}

	private System findSystemInList(Long systemId, List<System> systems) {
		for (System s : systems) {
			if (s.getSystemId().equals(systemId)) {
				return s;
			}
		}
		return null;
	}

}
