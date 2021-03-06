package es.udc.fic.manoelfolgueira.gdai.model;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.model.applicationservice.ApplicationService;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class ApplicationServiceTest {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private GroupService groupService;

	/**
	 * This tests the abscence of an application.
	 * 
	 * @throws DuplicateInstanceException
	 * @throws InstanceNotFoundException
	 */
	@Test
	public void testNumberOfApplicationsCreateData() throws DuplicateInstanceException, InstanceNotFoundException {

		assertEquals(3, applicationService.findAllOrderedByApplicationName().size());

	}

	/**
	 * This tests the creation of an application.
	 * 
	 * @throws DuplicateInstanceException
	 * @throws InstanceNotFoundException
	 * @throws InvalidDateException
	 */
	@Test
	public void testRegisterAndFindApplication()
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidDateException {

		GregorianCalendar gcCreationDate = new GregorianCalendar();
		GregorianCalendar gcExpirationDate = new GregorianCalendar();

		gcExpirationDate.add(GregorianCalendar.YEAR, 1);

		GroupDetails groupDetails = new GroupDetails(null, "Group", "GroupDescription", gcCreationDate,
				gcExpirationDate, null, null);

		GroupDetails groupDetailsRegistered = groupService.registerGroup(groupDetails);

		gcCreationDate = new GregorianCalendar();

		SystemDetails systemDetails = new SystemDetails(null, "SystemName", "SystemDescription", gcCreationDate,
				gcExpirationDate, groupDetailsRegistered);
		SystemDetails systemDetailsRegistered = systemService.registerSystem(systemDetails);

		GregorianCalendar acCreationDate = new GregorianCalendar();
		GregorianCalendar acExpirationDate = new GregorianCalendar();

		acExpirationDate.add(GregorianCalendar.YEAR, 2);

		ApplicationDetails applicationDetails = applicationService.registerApplication(new ApplicationDetails(null,
				"ApplicationName", "ApplicationDescription", acCreationDate, acExpirationDate, systemDetailsRegistered));

		ApplicationDetails applicationDetailsTest = applicationService
				.findApplication(applicationDetails.getApplicationId());

		assertEquals(applicationDetailsTest, applicationDetails);

	}

}
