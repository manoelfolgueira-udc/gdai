package es.udc.fic.manoelfolgueira.gdai.model.application;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ApplicationDao extends GenericDao<Application, Long> {

    /**
     * Returns an Application by name
     * @param name the application identifier
     * @return the Application
     */
    public Application findByName(String name) throws InstanceNotFoundException;
    
    /**
     * Returns all applications
     * @return all Application
     */
    public List<Application> findAllOrderedByApplicationName();
}
