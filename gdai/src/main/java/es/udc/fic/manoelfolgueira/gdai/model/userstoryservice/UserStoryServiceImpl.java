package es.udc.fic.manoelfolgueira.gdai.model.userstoryservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStoryDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("userStoryService")
@Transactional
public class UserStoryServiceImpl implements UserStoryService {

	@Autowired
	private UserStoryDao userStoryDao;

	/**
	 * {@inheritDoc}
	 */
	public UserStoryDetails registerUserStory(String name, UserStoryDetails userStoryDetails)
			throws DuplicateInstanceException {

		try {
			userStoryDao.findByName(name);
			throw new DuplicateInstanceException(name, UserStory.class.getName());
		} catch (InstanceNotFoundException e) {

			UserStory userStory = new UserStory(

					userStoryDetails.getUserStoryName(), userStoryDetails.getUserStoryDescription(),
					userStoryDetails.getCreationDate(), new User(userStoryDetails.getCreatedBy()));

			userStoryDao.save(userStory);
			return new UserStoryDetails(userStory);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public UserStoryDetails findUserStory(Long userStoryId) throws InstanceNotFoundException {

		return new UserStoryDetails(userStoryDao.find(userStoryId));
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateUserStoryDetails(Long userStoryId, UserStoryDetails userStoryDetails)
			throws InstanceNotFoundException {

		UserStory userStory = userStoryDao.find(userStoryId);

		userStory.setUserStoryName(userStoryDetails.getUserStoryName());
		userStory.setUserStoryDescription(userStoryDetails.getUserStoryDescription());

		userStoryDao.save(userStory);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UserStoryDetails> findAllOrderedByUserStoryName() {
		
		LinkedList<UserStoryDetails> userStoriesDetails = new LinkedList<>();
		
		userStoryDao.findAllOrderedByUserStoryName().forEach(us -> {
			userStoriesDetails.add(new UserStoryDetails(us));
		});
		
		return userStoriesDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeUserStory(Long userStoryId) throws InstanceNotFoundException {
		userStoryDao.remove(userStoryId);
	}

}
