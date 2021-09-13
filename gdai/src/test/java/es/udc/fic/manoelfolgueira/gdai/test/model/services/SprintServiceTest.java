package es.udc.fic.manoelfolgueira.gdai.test.model.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.services.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class SprintServiceTest {

	@Autowired
	private SprintService sprintService;

	@Test
	public void testNumberOfSprintsCreateData() throws DuplicateInstanceException, InstanceNotFoundException {

		assertEquals(2, sprintService.findAllOrderedBySprintName(SortingType.ASC).size());

	}

	@Test
	public void testUpdateAndFind()
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidDateException {

		GregorianCalendar gcCreationDate = new GregorianCalendar();
		GregorianCalendar gcExpirationDate = new GregorianCalendar();

		gcExpirationDate.add(GregorianCalendar.YEAR, 1);

		SprintDetails sprintDetails = new SprintDetails(null, "Sprint", gcCreationDate,
				gcExpirationDate, gcCreationDate, new LinkedList<ProjectDetails>());

		gcCreationDate = new GregorianCalendar();

		GregorianCalendar acExpirationDate = new GregorianCalendar();

		acExpirationDate.add(GregorianCalendar.YEAR, 2);

		sprintDetails = sprintService.registerSprint(sprintDetails.getSprintName(), sprintDetails);

		SprintDetails sprintDetailsTest = sprintService
				.findSprint(sprintDetails.getSprintId());

		assertEquals(sprintDetailsTest, sprintDetails);
		
		sprintDetails.setSprintName("Sprint 2");
		sprintService.updateSprintDetails(sprintDetails.getSprintId(), sprintDetails);

		sprintDetails.setSprintName("SprintNameUpdate");
		
		sprintDetailsTest = sprintService
				.findSprint(sprintDetails.getSprintId());

		assertNotEquals(sprintDetailsTest, sprintDetails);

	}

}
