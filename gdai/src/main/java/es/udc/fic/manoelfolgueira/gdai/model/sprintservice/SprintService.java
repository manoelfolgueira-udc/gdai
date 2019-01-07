package es.udc.fic.manoelfolgueira.gdai.model.sprintservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface SprintService {

    public Sprint registerSprint(String sprintName,
            SprintDetails sprintDetails)
            throws DuplicateInstanceException;

    public Sprint findSprint(Long sprintId)
            throws InstanceNotFoundException;

    public void updateSprintDetails(Long sprintId,
            SprintDetails sprintDetails)
            throws InstanceNotFoundException;
    
    public List<Sprint> findAllOrderedBySprintNameIC();
    
    public void removeSprint(Long sprintId) throws InstanceNotFoundException;

}
