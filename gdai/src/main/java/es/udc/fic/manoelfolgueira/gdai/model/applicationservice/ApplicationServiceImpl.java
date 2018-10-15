package es.udc.fic.manoelfolgueira.gdai.model.applicationservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.application.Application;
import es.udc.fic.manoelfolgueira.gdai.model.application.ApplicationDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("applicationService")
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    public Application registerApplication(String name,
            ApplicationDetails applicationDetails)
            throws DuplicateInstanceException {

        try {
            applicationDao.findByName(name);
            throw new DuplicateInstanceException(name,
                    Application.class.getName());
        } catch (InstanceNotFoundException e) {
        	
            Application application = new Application(applicationDetails.getApplicationName(), applicationDetails.getApplicationDescription());

            applicationDao.save(application);
            return application;
        }

    }

    @Transactional(readOnly = true)
    public Application findApplication(Long applicationId)
            throws InstanceNotFoundException {

        return applicationDao.find(applicationId);
    }

    public void updateApplicationDetails(Long applicationId,
            ApplicationDetails applicationDetails)
            throws InstanceNotFoundException {

        Application application = applicationDao.find(applicationId);
        
        application.setApplicationName(applicationDetails.getApplicationName());
        application.setApplicationDescription(applicationDetails.getApplicationDescription());
    }
    
    public List<Application> findAllOrderedByApplicationName() {
    	return applicationDao.findAllOrderedByApplicationName();
    }
    
}
