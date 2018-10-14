package es.udc.fic.manoelfolgueira.gdai.model.group;

import java.util.List;
import java.util.Map;

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
public interface GroupDao extends GenericDao<Group, Long> {

    /**
     * Returns a Group by name
     *
     * @param name the group identifier
     * @return the Group
     */
    public Group findByName(String name) throws InstanceNotFoundException;
    
    /**
     * Returns all groups
     *
     * @return all Group
     */
    public List<Group> findAllOrderedByGroupNameIC();
}
