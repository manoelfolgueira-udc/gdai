package es.udc.fic.manoelfolgueira.gdai.model.applicationservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.application.Application;
import es.udc.fic.manoelfolgueira.gdai.model.application.ApplicationDao;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("applicationService")
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationDao applicationDao;

	/**
	 * {@inheritDoc}
	 */
	public ApplicationDetails registerApplication(ApplicationDetails applicationDetails)
			throws DuplicateInstanceException {

		try {
			applicationDao.findByName(applicationDetails.getApplicationName());
			throw new DuplicateInstanceException(applicationDetails.getApplicationName(), Application.class.getName());
		} catch (InstanceNotFoundException e) {

			Application application = new Application(applicationDetails);

			applicationDao.save(application);
			return new ApplicationDetails(application);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public ApplicationDetails findApplication(Long applicationId) throws InstanceNotFoundException {

		return new ApplicationDetails(applicationDao.find(applicationId));
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateApplicationDetails(Long applicationId, ApplicationDetails applicationDetails)
			throws InstanceNotFoundException {

		Application application = applicationDao.find(applicationId);

		application.setApplicationName(applicationDetails.getApplicationName());
		application.setApplicationDescription(applicationDetails.getApplicationDescription());
		application.setExpirationDate(applicationDetails.getExpirationDate());

		application.setSystem(new System(applicationDetails.getSystem()));

		applicationDao.save(application);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ApplicationDetails> findAllOrderedByApplicationName() {

		LinkedList<ApplicationDetails> applicationsDetails = new LinkedList<>();
		List<Application> applications = applicationDao.findAllOrderedByApplicationName();

		for (Application application : applications) {
			applicationsDetails.add(new ApplicationDetails(application));
		}

		return applicationsDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Long applicationId) throws InstanceNotFoundException {
		applicationDao.remove(applicationId);
	}

}
