package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter.ProjectsFilter;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.services.projectservice.ProjectService;
import es.udc.fic.manoelfolgueira.gdai.model.services.projectsfilterservice.ProjectsFilterService;
import es.udc.fic.manoelfolgueira.gdai.model.services.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
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
	private String projectName = null;
	@Component(id = "projectName")
	private TextField projectNameField;

	@Property
	private String projectDescription = null;
	@Component(id = "projectDescription")
	private TextField projectDescriptionField;

	@Property
	private String userStoryName = null;
	@Component(id = "userStoryName")
	private TextField userStoryNameField;

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
	
	@Inject
	private ProjectsFilterService projectsFilterService;

	@Property
	private SelectModel sprintsModel;

	@Property
	private SelectModel groupsModel;

	@Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;
	
	@Property
	private SprintDetails sprintDetails = null;

	@Property
	private GroupDetails groupDetails = null;

	@Property
	private SystemDetails systemDetails = null;

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
	private List<ProjectDetails> projectsDetailsSearch;

	@Property
	private ProjectDetails projectDetails = null;

	@Inject
	private PageRenderLinkSource pageRenderLS;
	
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Property
	private UserDetails userDetails;
	
	@Property
	private ProjectsFilter projectsFilter;
	
	@Inject
	private HttpServletRequest servletRequest;
	
	public String getCurrentPath() {
		return servletRequest.getRequestURL().toString();
	}

	void setupRender() {
		try {
			userDetails = userService.findUser(userSession.getUserId());
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Object onSuccess() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

		return pageRenderLS.createPageRenderLinkWithContext("tools/project/management", projectName, projectDescription,
				userStoryName, userStoryDescription, (creationDateStart != null ? sdf.format(creationDateStart) : null),
				(creationDateEnd != null ? sdf.format(creationDateEnd) : null),
				(sprintDetails != null) ? sprintDetails.getSprintId() : null,
				(groupDetails != null) ? groupDetails.getGroupId() : null,
				(systemDetails != null) ? systemDetails.getSystemId() : null);
	}

	void onActivate(String projectName, String projectDescription, String userStoryName, String userStoryDescription,
			String creationDateStartStr, String creationDateEndStr, Long sprintId, Long groupId, Long systemId) {
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.userStoryName = userStoryName;
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

		List<SprintDetails> sprintsDetails = sprintService.findAllOrderedBySprintName(SortingType.DESC);

		if (sprintId != null) {
			sprintDetails = findSprintInList(sprintId, sprintsDetails);
		}

		sprintsModel = selectModelFactory.create(sprintsDetails, "sprintName");

		List<GroupDetails> groupsDetails = groupService.findAllOrderedByGroupName();

		if (groupId != null) {
			groupDetails = findGroupInList(groupId, groupsDetails);
		}

		groupsModel = selectModelFactory.create(groupsDetails, "groupName");

		List<SystemDetails> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			systemDetails = findSystemInList(systemId, systems);
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
 
		projectsDetailsSearch = projectService.findByCriteria(projectName, projectDescription, userStoryName, userStoryDescription,
				calCreationDateStart, calCreationDateEnd, (sprintDetails != null) ? sprintDetails.getSprintId() : null,
				(groupDetails != null) ? groupDetails.getGroupId() : null,
				(systemDetails != null) ? systemDetails.getSystemId() : null);
		
	}

	private SprintDetails findSprintInList(Long sprintId, List<SprintDetails> sprintsDetails) {
		for (SprintDetails s : sprintsDetails) {
			if (s.getSprintId().equals(sprintId)) {
				return s;
			}
		}
		return null;
	}

	private GroupDetails findGroupInList(Long groupId, List<GroupDetails> groupsDetails) {
		for (GroupDetails g : groupsDetails) {
			if (g.getGroupId().equals(groupId)) {
				return g;
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
	
	@OnEvent(component = "filterSave", value = "selected")
	private void onFilterSave() {
				
		ProjectsFilter projectsFilter = null;
		UserDetails userDetails = null;
		try {
			userDetails = userService.findUser(userSession.getUserId());
			projectsFilter = projectsFilterService.load(new User(userDetails));
			if (projectsFilter == null) projectsFilter = new ProjectsFilter();
		} catch (InstanceNotFoundException e) {
			projectsFilter = new ProjectsFilter();
		}
		
		projectsFilter.setCreatedBy(new User(userDetails));
		
		projectsFilter.setProjectName(projectName);
		projectsFilter.setProjectDescription(projectDescription);
		projectsFilter.setUserStoryName(userStoryName);
		projectsFilter.setUserStoryDescription(userStoryDescription);
		
		Calendar calendarStart = null;
		if (creationDateStart != null) {
			calendarStart = Calendar.getInstance();
			calendarStart.setTime(creationDateStart);
		}
		projectsFilter.setProjectCreationDateStart(calendarStart);
		
		Calendar calendarEnd = null;
		if (creationDateEnd != null) {
			calendarEnd = Calendar.getInstance();
			calendarEnd.setTime(creationDateEnd);
			
		}
		projectsFilter.setProjectCreationDateEnd(calendarEnd);
		
		projectsFilter.setSprintId((sprintDetails != null) ? sprintDetails.getSprintId() : null);
		projectsFilter.setGroupId((groupDetails != null) ? groupDetails.getGroupId() : null);
		projectsFilter.setSystemId((systemDetails != null) ? systemDetails.getSystemId() : null);
		
		projectsFilterService.save(projectsFilter);
	}

	@OnEvent(component = "filterLoad", value = "selected")
	private void onFilterLoad() {
		
		ProjectsFilter projectsFilter;
		try {
			projectsFilter = projectsFilterService.load(new User(userService.findUser(userSession.getUserId())));
		} catch (InstanceNotFoundException e1) {
			projectsFilter = new ProjectsFilter();
		}
		
		List<SprintDetails> sprintsDetails = sprintService.findAllOrderedBySprintName(SortingType.DESC);

		if (sprintId != null) {
			sprintDetails = findSprintInList(projectsFilter.getSprintId(), sprintsDetails);
		}

		sprintsModel = selectModelFactory.create(sprintsDetails, "sprintName");

		List<GroupDetails> groupsDetails = groupService.findAllOrderedByGroupName();

		if (groupId != null) {
			groupDetails = findGroupInList(projectsFilter.getGroupId(), groupsDetails);
		}

		groupsModel = selectModelFactory.create(groupsDetails, "groupName");

		List<SystemDetails> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			systemDetails = findSystemInList(projectsFilter.getSystemId(), systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");

		Calendar calCreationDateStart = projectsFilter.getProjectCreationDateStart();
		Calendar calCreationDateEnd = projectsFilter.getProjectCreationDateEnd();
		creationDateStart = calCreationDateStart != null ? calCreationDateStart.getTime() : null;
		creationDateEnd = calCreationDateEnd != null ? calCreationDateEnd.getTime() : null;
 
		projectsDetailsSearch = projectService.findByCriteria(projectName, projectDescription, userStoryName, userStoryDescription,
				calCreationDateStart, calCreationDateEnd, (sprintDetails != null) ? sprintDetails.getSprintId() : null,
				(groupDetails != null) ? groupDetails.getGroupId() : null,
				(systemDetails != null) ? systemDetails.getSystemId() : null);
	}

}
