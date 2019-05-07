package es.udc.fic.manoelfolgueira.gdai.model.system;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface SystemDao extends GenericDao<System, Long> {

    /**
     * Returns a System by name
     *
     * @param name the system identifier
     * @return the System
     */
    public System findByName(String name) throws InstanceNotFoundException;
    
    /**
     * Returns all systems
     *
     * @return all System
     */
    public List<System> findAllOrderedBySystemName();
}
