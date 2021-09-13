package es.udc.fic.manoelfolgueira.gdai.test.model.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter.ProjectsFilter;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.services.projectsfilterservice.ProjectsFilterService;
import es.udc.fic.manoelfolgueira.gdai.model.services.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class ProjectsFilterServiceTest {

	@Autowired
	private ProjectsFilterService projectsFilterService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private UserService userService;;

	@Test
	public void testSaveAndLoadProjectsFilter()
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidDateException {

		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		
		GroupDetails groupDetails = new GroupDetails(null, "Group", "GroupDescription", start,
				end, null, null);
		
		groupDetails = groupService.registerGroup(new GroupDetails(null,
				"GroupName", "GroupDescription", start, end, groupDetails.getUsers(), groupDetails.getSystems()));
		
		SystemDetails systemDetails = new SystemDetails(null, "SystemName", "SystemDescription", start,
				end, groupDetails);
		
		SprintDetails sprintDetails = new SprintDetails(null, "spname", start, end, start, new LinkedList<ProjectDetails>());
		sprintDetails = sprintService.registerSprint(sprintDetails.getSprintName(), sprintDetails);
		
		GregorianCalendar gcExpirationDate = new GregorianCalendar();
		GregorianCalendar gcHireDate = new GregorianCalendar();
		GregorianCalendar gcDateOfBirth = new GregorianCalendar();

		gcExpirationDate.add(GregorianCalendar.YEAR, 1);

		UserDetails userDetails = new UserDetails(null, "login", "f", "l", "M", "a@a.com", "609", "url", gcHireDate, gcDateOfBirth, gcExpirationDate, true, groupDetails);
		
		gcHireDate = new GregorianCalendar();

		GregorianCalendar acExpirationDate = new GregorianCalendar();

		acExpirationDate.add(GregorianCalendar.YEAR, 2);

		userDetails = userService.registerUser("pw", userDetails);
		
		ProjectsFilter pf = new ProjectsFilter(null, "*c", "a", "d", "e", start, end, sprintDetails.getSprintId(), groupDetails.getGroupId(), systemDetails.getSystemId(), new User(userDetails));

		ProjectsFilter pf2 = projectsFilterService.save(pf);
		
		pf = projectsFilterService.load(new User(userDetails));
		
		assertEquals(pf2, pf);
		
		pf = projectsFilterService.load(pf);
		
		assertEquals(pf2, pf);
		
	}

}
