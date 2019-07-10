package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.project;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.GroupEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SprintEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.pages.administration.user.UserManagement;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows Project Management
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   ProjectManagement.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProjectManagement {

	private Long sprintId;
	private Long groupId;
	private Long systemId;

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
	
	@Property
	private SelectModel sprintsModel;

	@Property
	private SelectModel groupsModel;
	
	@Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;
	
	@Property
	private Sprint sprint;

	@Property
	private Group group;
	
	@Property
	private System system;

	@Component
	private Form registrationForm;
	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	void onValidateFromRegistrationForm() {

	}

	Object onSuccess() {
		return UserManagement.class;
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

	Long onPassivate() {
		return groupId;
	}

	void onPrepare() {
		
		List<Sprint> sprints = sprintService.findAllOrderedBySprintName(SortingType.DESC);

		if (sprintId != null) {
			sprint = findSprintInList(sprintId, sprints);
		} else {
			sprint = sprints.get(0); // we get the current sprint by default
		}

		sprintsModel = selectModelFactory.create(sprints, "sprintName");

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
