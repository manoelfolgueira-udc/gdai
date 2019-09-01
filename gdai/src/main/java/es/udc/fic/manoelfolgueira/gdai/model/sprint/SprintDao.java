package es.udc.fic.manoelfolgueira.gdai.model.sprint;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * @author Manu
 *
 */
/**
 * @author Manu
 *
 */
public interface SprintDao extends GenericDao<Sprint, Long> {

    /**
     * Returns a Sprint by name
     * @param name the sprint identifier
     * @return the Sprint
     */
    public Sprint findByName(String name) throws InstanceNotFoundException;
    
    /**
     * Returns all sprints sorted
     * @return all Sprint
     */
    public List<Sprint> findAllOrderedBySprintName(SortingType sortingType);

	/**
	 * @param sortingType
	 * @return
	 */
	public List<Sprint> findBySprintStart(SortingType sortingType);

}
