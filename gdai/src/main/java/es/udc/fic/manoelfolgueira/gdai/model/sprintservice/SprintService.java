package es.udc.fic.manoelfolgueira.gdai.model.sprintservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface SprintService {

	/**
	 * Saves a new sprint
	 * @param sprintName sprint name
	 * @param sprintDetails its DTO being transformed and saved
	 * @return the real sprint being registered
	 * @throws DuplicateInstanceException
	 */
    public Sprint registerSprint(String sprintName,
            SprintDetails sprintDetails)
            throws DuplicateInstanceException;

    /**
     * Find a sprint by its identifier
     * @param sprintId the id
     * @return the actual Sprint
     * @throws InstanceNotFoundException
     */
    public Sprint findSprint(Long sprintId)
            throws InstanceNotFoundException;

    /**
     * Put a sprint up-to-date using a dto instnace and its identifier
     * @param sprintId the id
     * @param sprintDetails the dto instance
     * @throws InstanceNotFoundException
     */
    public void updateSprintDetails(Long sprintId,
            SprintDetails sprintDetails)
            throws InstanceNotFoundException;
    
    
    /**
     * Finds all sprints and returns them ordered by name in the sorting type specified
     * @return the list of sprints
     */
    public List<Sprint> findAllOrderedBySprintName(SortingType sortingType);
    
    /**
     * Erases a sprint by its id
     * @param sprintId the id
     * @throws InstanceNotFoundException
     */
    public void removeSprint(Long sprintId) throws InstanceNotFoundException;

}
