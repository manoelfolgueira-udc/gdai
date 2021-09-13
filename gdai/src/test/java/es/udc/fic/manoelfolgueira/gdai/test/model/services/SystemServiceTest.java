package es.udc.fic.manoelfolgueira.gdai.test.model.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.services.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class SystemServiceTest {

	@Autowired
	private SystemService systemService;

	@Autowired
	private GroupService groupService;

	@Test
	public void testNumberOfSystemsCreateData() throws DuplicateInstanceException, InstanceNotFoundException {

		assertEquals(2, systemService.findAllOrderedBySystemName().size());

	}

	@Test
	public void testRegisterAndFindSystem()
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

		SystemDetails systemDetailsTest = systemService
				.findSystem(systemDetails.getSystemId());

		assertEquals(systemDetailsTest, systemDetails);

	}
	
	@Test
	public void testUpdateAndFind()
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

		SystemDetails systemDetailsTest = systemService
				.findSystem(systemDetails.getSystemId());

		assertEquals(systemDetailsTest, systemDetails);
		
		systemService.updateSystemDetails(systemDetails.getSystemId(), new SystemDetails(systemDetails.getSystemId(),
				"SystemNameUpdate", "SystemDescription", acCreationDate, acExpirationDate, groupDetailsRegistered));

		systemDetails.setSystemName("SystemNameUpdate");
		
		systemDetailsTest = systemService
				.findSystem(systemDetails.getSystemId());

		assertEquals(systemDetailsTest, systemDetails);

	}

}
