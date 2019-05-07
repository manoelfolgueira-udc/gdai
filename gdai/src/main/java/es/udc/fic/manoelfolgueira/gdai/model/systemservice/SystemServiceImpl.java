package es.udc.fic.manoelfolgueira.gdai.model.systemservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.system.SystemDao;
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
    public System registerSystem(String name,
            SystemDetails systemDetails)
            throws DuplicateInstanceException {

        try {
            systemDao.findByName(name);
            throw new DuplicateInstanceException(name,
                    System.class.getName());
        } catch (InstanceNotFoundException e) {
        	
            System system = new System(systemDetails.getSystemName(), systemDetails.getSystemDescription(), systemDetails.getGroup());

            systemDao.save(system);
            return system;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public System findSystem(Long systemId)
            throws InstanceNotFoundException {

        return systemDao.find(systemId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateSystemDetails(Long systemId,
            SystemDetails systemDetails)
            throws InstanceNotFoundException {

        System system = systemDao.find(systemId);
        
        system.setSystemName(systemDetails.getSystemName());
        system.setSystemDescription(systemDetails.getSystemDescription());
        system.setGroup(systemDetails.getGroup());
        
        systemDao.save(system);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<System> findAllOrderedBySystemName() {
    	return systemDao.findAllOrderedBySystemName();
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public void remove(Long systemId) throws InstanceNotFoundException {
		systemDao.remove(systemId);
	}
    
}
