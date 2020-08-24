package es.udc.fic.manoelfolgueira.gdai.model.userstory;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("userStoryDao")
public class UserStoryDaoHibernate extends GenericDaoHibernate<UserStory, Long> implements UserStoryDao {

	/**
	 * {@inheritDoc}
	 */
	public UserStory findByName(String userStoryName) throws InstanceNotFoundException {

		UserStory userStory = (UserStory) getSession()
				.createQuery("SELECT us FROM UserStory us WHERE us.userStoryName = :userStoryName")
				.setParameter("userStoryName", userStoryName).uniqueResult();
		if (userStory == null) {
			throw new InstanceNotFoundException(userStoryName, UserStory.class.getName());
		} else {
			return userStory;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserStory> findAllOrderedByUserStoryName() {
		List<UserStory> userStorys = getSession()
				.createQuery("SELECT us FROM UserStory us ORDER BY lower(us.userStoryName)").list();
		return userStorys;
	}

}