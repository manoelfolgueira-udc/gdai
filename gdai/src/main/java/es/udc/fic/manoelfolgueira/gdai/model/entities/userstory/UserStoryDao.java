package es.udc.fic.manoelfolgueira.gdai.model.entities.userstory;

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
public interface UserStoryDao extends GenericDao<UserStory, Long> {

	/**
	 * Returns a UserStory by name
	 * 
	 * @param name
	 *            the userStory identifier
	 * @return the UserStory
	 */
	public UserStory findByName(String name) throws InstanceNotFoundException;

	/**
	 * Returns all userStorys
	 * 
	 * @return all UserStory
	 */
	public List<UserStory> findAllOrderedByUserStoryName();
}
