package es.udc.fic.manoelfolgueira.gdai.model.entities.group;

import java.util.List;

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
	 * @param name
	 *            the group identifier
	 * @return the Group
	 */
	public Group findByName(String groupName) throws InstanceNotFoundException;

	/**
	 * Returns all groups
	 * 
	 * @return all Group
	 */
	public List<Group> findAllOrderedByGroupName();
}
