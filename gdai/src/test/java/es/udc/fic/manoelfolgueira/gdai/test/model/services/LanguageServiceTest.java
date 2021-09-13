package es.udc.fic.manoelfolgueira.gdai.test.model.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.services.languageservice.LanguageService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SPRING_CONFIG_FILE,
		es.udc.fic.manoelfolgueira.gdai.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE })
@Transactional
public class LanguageServiceTest {

	@Autowired
	private LanguageService languageService;

	@Test
	public void testNumberOfLanguagesCreateData() throws DuplicateInstanceException, InstanceNotFoundException {

		assertEquals(3, languageService.getOptionsOrdered().size());

	}

	@Test
	public void getNames()
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidDateException {
		
		assertEquals("en,es,gl", languageService.getNames());

	}


}
