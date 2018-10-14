package es.udc.fic.manoelfolgueira.gdai.model.user;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface UserDao extends GenericDao<User, Long> {

    /**
     * Returns an User by login name
     *
     * @param loginName the user identifier
     * @return the User
     */
    public User findByLoginName(String loginName) throws InstanceNotFoundException;

	public List<User> findAllSortedByName();
	
	public void remove(Long userId) throws InstanceNotFoundException;
}
