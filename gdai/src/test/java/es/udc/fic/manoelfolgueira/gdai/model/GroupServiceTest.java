package es.udc.fic.manoelfolgueira.gdai.model;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class GroupServiceTest {

	@Autowired
	private GroupService groupService;

	/**
	 * This tests current number of groups stored in the database.
	 */
	@Test
	public void testNumberOfGroupsCreateData() {

		assertEquals(groupService.findAllOrderedByGroupName().size(), 7);

	}

	/**
	 * This tests the creation of a group.
	 * 
	 * @throws DuplicateInstanceException
	 * @throws InstanceNotFoundException
	 * @throws InvalidDateException
	 */
	@Test
	public void testRegisterAndFindGroup()
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidDateException {

		GregorianCalendar gcCreationDate = new GregorianCalendar();
		GregorianCalendar gcExpirationDate = new GregorianCalendar();

		gcExpirationDate.add(GregorianCalendar.YEAR, 1);

		GroupDetails groupDetails = new GroupDetails(null, "Group", "GroupDescription", gcCreationDate,
				gcExpirationDate, null, null);

		GroupDetails groupDetailsRegistered = groupService.registerGroup(groupDetails);

		GroupDetails groupDetailsFound = groupService.findGroup(groupDetailsRegistered.getGroupId());

		assertEquals(groupDetailsRegistered, groupDetailsFound);

	}

}
