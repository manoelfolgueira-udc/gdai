package es.udc.fic.manoelfolgueira.gdai.model.services.systemservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.entities.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.entities.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.entities.system.SystemDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("systemService")
@Transactional
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao systemDao;

	/**
	 * {@inheritDoc}
	 */
	public SystemDetails registerSystem(SystemDetails systemDetails) throws DuplicateInstanceException {

		try {
			systemDao.findByName(systemDetails.getSystemName());
			throw new DuplicateInstanceException(systemDetails.getSystemName(), System.class.getName());
		} catch (InstanceNotFoundException e) {

			System system = new System(systemDetails);

			systemDao.save(system);
			return new SystemDetails(system);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public SystemDetails findSystem(Long systemId) throws InstanceNotFoundException {

		return new SystemDetails(systemDao.find(systemId));
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateSystemDetails(Long systemId, SystemDetails systemDetails) throws InstanceNotFoundException {

		System system = systemDao.find(systemId);

		system.setSystemName(systemDetails.getSystemName());
		system.setSystemDescription(systemDetails.getSystemDescription());
		system.setExpirationDate(systemDetails.getExpirationDate());
		system.setGroup(new Group(systemDetails.getGroup()));

		systemDao.save(system);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SystemDetails> findAllOrderedBySystemName() {

		LinkedList<SystemDetails> systemsDetails = new LinkedList<>();
		List<System> systems = systemDao.findAllOrderedBySystemName();

		for (System system : systems) {
			systemsDetails.add(new SystemDetails(system));
		}

		return systemsDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Long systemId) throws InstanceNotFoundException {
		systemDao.remove(systemId);
	}

}
