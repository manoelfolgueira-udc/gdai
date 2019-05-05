package es.udc.fic.manoelfolgueira.gdai.model.systemservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface SystemService {

    public System registerSystem(String systemName,
            SystemDetails systemDetails)
            throws DuplicateInstanceException;

    public System findSystem(Long systemId)
            throws InstanceNotFoundException;

    public void updateSystemDetails(Long systemId,
            SystemDetails systemDetails)
            throws InstanceNotFoundException;
    
    public List<System> findAllOrderedBySystemNameIC();
    
    public void remove(Long systemId) throws InstanceNotFoundException;

}
