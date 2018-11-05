package es.udc.fic.manoelfolgueira.gdai.model.applicationservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.application.Application;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ApplicationService {

    public Application registerApplication(String applicationName,
            ApplicationDetails applicationDetails)
            throws DuplicateInstanceException;

    public Application findApplication(Long applicationId)
            throws InstanceNotFoundException;

    public void updateApplicationDetails(Long applicationId,
            ApplicationDetails applicationDetails)
            throws InstanceNotFoundException;
    
    public List<Application> findAllOrderedByApplicationName();
    
    public void remove(Long applicationId) throws InstanceNotFoundException;

}
