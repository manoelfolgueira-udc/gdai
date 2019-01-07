package es.udc.fic.manoelfolgueira.gdai.model.userstory;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("userStoryDao")
public class UserStoryDaoHibernate extends GenericDaoHibernate<UserStory, Long> implements UserStoryDao {

	public UserStory findByName(String userStoryName) throws InstanceNotFoundException {

    	UserStory userStory = (UserStory) getSession().createQuery(
    			"SELECT g FROM UserStory g WHERE g.userStoryName = :userStoryName")
    			.setParameter("userStoryName", userStoryName)
    			.uniqueResult();
    	if (userStory == null) {
   			throw new InstanceNotFoundException(userStoryName, UserStory.class.getName());
    	} else {
    		return userStory;
    	}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserStory> findAllOrderedByUserStoryNameIC() {
		List<UserStory> userStorys = getSession().createQuery("SELECT g FROM UserStory g ORDER BY lower(g.userStoryName)").list();
		return userStorys;
	}

}