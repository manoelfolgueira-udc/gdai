package es.udc.fic.manoelfolgueira.gdai.test.model.services;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.services.projectservice.ProjectService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;

//	('Billing Entities', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 1, 1),
//	('GDAIBilling', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 1, 1),
//	('CRM', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 2, 1);
	
//	(String projectName, String projectDescription, String userStoryName,
//			String userStoryDescription, Calendar creationDateStart, Calendar creationDateEnd, Long sprintId,
//			Long groupId, Long systemId)

	@Test
	public void testSearches()
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidDateException {
		
		Calendar start = Calendar.getInstance();
		start.add(GregorianCalendar.YEAR, -1);
		Calendar end = Calendar.getInstance();
		end.add(GregorianCalendar.YEAR, 1);
		
		assertEquals(3,projectService.findByCriteria(null, null, null, null, null, null, null, null, null).size());
		assertEquals(2,projectService.findByCriteria("*bi*", null, null, null, null, null, null, null, null).size());
		assertEquals(3,projectService.findByCriteria(null, "*de*", null, null, null, null, null, null, null).size());
		assertEquals(0,projectService.findByCriteria(null, null, "*a*", null, null, null, null, null, null).size());
		assertEquals(3,projectService.findByCriteria(null, null, null, "*u*", null, null, null, null, null).size());
		assertEquals(3,projectService.findByCriteria(null, null, null, null, start, null, null, null, null).size());
		assertEquals(0,projectService.findByCriteria(null, null, null, null, null, start, null, null, null).size());
		assertEquals(3,projectService.findByCriteria(null, null, null, null, start, end, null, null, null).size());
		assertEquals(1,projectService.findByCriteria(null, null, null, null, null, null, null, null, Long.valueOf(2)).size());
		assertEquals(2,projectService.findByCriteria(null, null, null, null, null, null, null, null, Long.valueOf(1)).size());

	}

}
