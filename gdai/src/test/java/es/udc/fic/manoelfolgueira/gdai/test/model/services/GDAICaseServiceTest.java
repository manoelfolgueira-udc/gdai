package es.udc.fic.manoelfolgueira.gdai.test.model.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.services.gdaicaseservice.GDAICaseService;
import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GDAICaseDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class GDAICaseServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GDAICaseService gdaiCaseService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private SystemService systemService;


	@Test
	public void testRegisterAndFindByGroup()
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidDateException {

		GregorianCalendar gcCreationDate = new GregorianCalendar();
		GregorianCalendar gcExpirationDate = new GregorianCalendar();

		gcExpirationDate.add(GregorianCalendar.YEAR, 1);

		GroupDetails groupDetails = new GroupDetails(null, "Group", "GroupDescription", gcCreationDate,
				gcExpirationDate, null, null);

		GroupDetails groupDetailsRegistered = groupService.registerGroup(groupDetails);

		gcCreationDate = new GregorianCalendar();

		GregorianCalendar acCreationDate = new GregorianCalendar();
		GregorianCalendar acExpirationDate = new GregorianCalendar();

		acExpirationDate.add(GregorianCalendar.YEAR, 2);

		SystemDetails systemDetails = systemService.registerSystem(new SystemDetails(null,
				"SystemName", "SystemDescription", acCreationDate, acExpirationDate, groupDetailsRegistered));
		
		gcCreationDate = new GregorianCalendar();
		gcExpirationDate = new GregorianCalendar();

		gcExpirationDate.add(GregorianCalendar.YEAR, 1);

		groupDetails = new GroupDetails(null, "Group", "GroupDescription", gcCreationDate,
				gcExpirationDate, null, null);

		gcCreationDate = new GregorianCalendar();

		acCreationDate = new GregorianCalendar();
		acExpirationDate = new GregorianCalendar();

		acExpirationDate.add(GregorianCalendar.YEAR, 2);

		groupDetails = groupService.registerGroup(new GroupDetails(null,
				"GroupName", "GroupDescription", acCreationDate, acExpirationDate, groupDetails.getUsers(), groupDetails.getSystems()));

		gcExpirationDate = new GregorianCalendar();
		GregorianCalendar gcHireDate = new GregorianCalendar();
		GregorianCalendar gcDateOfBirth = new GregorianCalendar();

		gcExpirationDate.add(GregorianCalendar.YEAR, 1);

		UserDetails userDetails = new UserDetails(null, "login", "f", "l", "M", "a@a.com", "609", "url", gcHireDate, gcDateOfBirth, gcExpirationDate, true, groupDetails);

		gcHireDate = new GregorianCalendar();

		acCreationDate = new GregorianCalendar();
		acExpirationDate = new GregorianCalendar();

		acExpirationDate.add(GregorianCalendar.YEAR, 2);

		userDetails = userService.registerUser("pw", userDetails);

		GDAICaseDetails gdaiCaseDetails = gdaiCaseService.createGDAICase(new GDAICaseDetails(null,
				"gdaiCaseDescription", "gdaiCaseResolution", acCreationDate, userDetails, systemDetails));

		GDAICaseDetails gdaiCaseDetailsTest = gdaiCaseService
				.findGDAICase(gdaiCaseDetails.getGDAICaseId());

		assertEquals(gdaiCaseDetailsTest, gdaiCaseDetails);

	}

}
