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

import es.udc.fic.manoelfolgueira.gdai.model.services.projectservice.ProjectService;
import es.udc.fic.manoelfolgueira.gdai.model.services.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserStoryDetails;
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
	private LinkedList<SprintDetails> sprintsDetailsSelected = new LinkedList<>();

	@Inject
	private PageRenderLinkSource pageRenderLS;

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

		if (!projectReqs.getFileName().toLowerCase().endsWith(".pdf")) {
			registrationForm.recordError(messages.get("onlyPDF"));
		}

	}

	Object onSuccess() {

		Calendar calCreationDate = Calendar.getInstance();
		UserDetails createdBy;
		try {
			createdBy = userService.findUser(userSession.getUserId());
			sprintsDetailsSelected.add(sprintDetails);
			projectDetails = projectService.createProject(new ProjectDetails(null, projectName, projectDescription, calCreationDate,
					null, createdBy, systemDetails, sprintsDetailsSelected, userStoryDetails));
		} catch (DuplicateInstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		File copied = new File(
				Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS),
				projectDetails.getGDAICode() + "_" + projectReqs.getFileName());
		projectReqs.write(copied);

		try {

			String requirementsPath = Config.getInstance().getProperties()
					.getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS) + "/" + projectDetails.getGDAICode() + "_"
					+ projectReqs.getFileName();

			projectService.updateProjectDetails(projectDetails.getProjectId(),
					new ProjectDetails(projectDetails.getProjectId(), projectDetails.getProjectName(), projectDetails.getProjectDescription(),
							projectDetails.getCreationDate(), requirementsPath, projectDetails.getCreatedBy(), projectDetails.getSystemDetails(),
							projectDetails.getSprintsDetails(), projectDetails.getUserStoryDetails()));
			return pageRenderLS.createPageRenderLinkWithContext("tools/project/projectcreated", projectDetails.getProjectId());
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

		List<SprintDetails> sprints = sprintService.findAllOrderedBySprintStart(SortingType.DESC, 10);

		if (sprintId != null) {
			sprintDetails = findSprintInList(sprintId, sprints);
		}

		sprintsModel = selectModelFactory.create(sprints, "sprintName");

		List<UserStoryDetails> userStories = userStoryService.findAllOrderedByUserStoryName();

		if (userStoryId != null) {
			userStoryDetails = findUserStoryInList(userStoryId, userStories);
		}

		userStoriesModel = selectModelFactory.create(userStories, "userStoryName");

		List<SystemDetails> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			systemDetails = findSystemInList(systemId, systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");
	}

	private SprintDetails findSprintInList(Long sprintId, List<SprintDetails> sprintsDetails) {
		for (SprintDetails s : sprintsDetails) {
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

	private SystemDetails findSystemInList(Long systemId, List<SystemDetails> systems) {
		for (SystemDetails s : systems) {
			if (s.getSystemId().equals(systemId)) {
				return s;
			}
		}
		return null;
	}

}
