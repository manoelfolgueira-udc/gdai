package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectService;
import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SprintEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows project Management
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProjectManagement {

	@Property
	private String projectId = null;
	@Component(id = "projectId")
	private TextField projectIdField;

	@Property
	private String projectDescription = null;
	@Component(id = "projectDescription")
	private TextField projectDescriptionField;

	@Property
	private String userStoryId = null;
	@Component(id = "userStoryId")
	private TextField userStoryIdField;

	@Property
	private String userStoryDescription = null;
	@Component(id = "userStoryDescription")
	private TextField userStoryDescriptionField;

	private Long sprintId = null;
	private Long groupId = null;
	private Long systemId = null;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private UserService userService;

	@Inject
	private SprintService sprintService;

	@Inject
	private GroupService groupService;

	@Inject
	private SystemService systemService;

	@Inject
	private ProjectService projectService;

	@Property
	private SelectModel sprintsModel;

	@Property
	private SelectModel groupsModel;

	@Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	@Property
	private Sprint sprint = null;

	@Property
	private Group group = null;

	@Property
	private System system = null;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Property
	private Date creationDateStart = null;

	@Property
	private Date creationDateEnd = null;

	@Property
	private String creationDateStartStr = null;

	@Property
	private String creationDateEndStr = null;

	@Property
	private List<Project> projectsSearch;

	@Property
	private Project project = null;

	@Inject
	private PageRenderLinkSource pageRenderLS;
	
	@Property
	private User user;
	
	void setupRender() {
		try {
			user = userService.findUser(userSession.getUserId());
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Object onSuccess() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

		java.lang.System.out.println("H058 # " + creationDateStart);

		return pageRenderLS.createPageRenderLinkWithContext("tools/project/management", projectId, projectDescription,
				userStoryId, userStoryDescription, (creationDateStart != null ? sdf.format(creationDateStart) : null),
				(creationDateEnd != null ? sdf.format(creationDateEnd) : null),
				(sprint != null) ? sprint.getSprintId() : null, (group != null) ? group.getGroupId() : null,
				(system != null) ? system.getSystemId() : null);
	}

	void onActivate(String projectId, String projectDescription, String userStoryId, String userStoryDescription,
			String creationDateStartStr, String creationDateEndStr, Long sprintId, Long groupId, Long systemId) {
		this.projectId = projectId;
		this.projectDescription = projectDescription;
		this.userStoryId = userStoryId;
		this.userStoryDescription = userStoryDescription;
		this.creationDateStartStr = creationDateStartStr;
		this.creationDateEndStr = creationDateEndStr;
		this.sprintId = sprintId;
		this.groupId = groupId;
		this.systemId = systemId;
	}

	public SprintEncoder getSprintEncoder() {
		return new SprintEncoder(sprintService);
	}

	public GroupEncoder getGroupEncoder() {
		return new GroupEncoder(groupService);
	}

	public SystemEncoder getSystemEncoder() {
		return new SystemEncoder(systemService);
	}

	void onPrepare() {
		
		List<Sprint> sprints = sprintService.findAllOrderedBySprintName(SortingType.DESC);

		if (sprintId != null) {
			sprint = findSprintInList(sprintId, sprints);
		}

		sprintsModel = selectModelFactory.create(sprints, "bSprintName");

		List<Group> groups = groupService.findAllOrderedByGroupName();

		if (groupId != null) {
			group = findGroupInList(groupId, groups);
		}

		groupsModel = selectModelFactory.create(groups, "groupName");

		List<System> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			system = findSystemInList(systemId, systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

		try {
			creationDateStart = sdf.parse(creationDateStartStr);
		} catch (ParseException | NullPointerException e) {
			creationDateStart = null;
		}
		try {
			creationDateEnd = sdf.parse(creationDateEndStr);
		} catch (ParseException | NullPointerException e) {
			creationDateEnd = null;
		}

		Calendar calCreationDateStart = Calendar.getInstance();
		if (creationDateStart != null)
			calCreationDateStart.setTime(creationDateStart);
		else
			calCreationDateStart = null;

		Calendar calCreationDateEnd = Calendar.getInstance();
		if (creationDateEnd != null)
			calCreationDateEnd.setTime(creationDateEnd);
		else
			calCreationDateEnd = null;

		projectsSearch = projectService.findByCriteria(projectId, projectDescription, userStoryId, userStoryDescription,
				calCreationDateStart, calCreationDateEnd, (sprint != null) ? sprint.getSprintId() : null,
				(group != null) ? group.getGroupId() : null, (system != null) ? system.getSystemId() : null);
	}

	private Sprint findSprintInList(Long sprintId, List<Sprint> sprints) {
		for (Sprint s : sprints) {
			if (s.getSprintId().equals(sprintId)) {
				return s;
			}
		}
		return null;
	}

	private Group findGroupInList(Long groupId, List<Group> groups) {
		for (Group g : groups) {
			if (g.getGroupId().equals(groupId)) {
				return g;
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
