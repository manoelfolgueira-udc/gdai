package es.udc.fic.manoelfolgueira.gdai.model.userstoryservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStoryDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("userStoryService")
@Transactional
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired
    private UserStoryDao userStoryDao;

    public UserStory registerUserStory(String name,
            UserStoryDetails userStoryDetails)
            throws DuplicateInstanceException {

        try {
            userStoryDao.findByName(name);
            throw new DuplicateInstanceException(name,
                    UserStory.class.getName());
        } catch (InstanceNotFoundException e) {
        	
            UserStory userStory = new UserStory(
            		
            		userStoryDetails.getUserStoryName(),
            		userStoryDetails.getStartDate(),
            		userStoryDetails.getEndDate(),
            		userStoryDetails.getCreationDate(),
            		userStoryDetails.getProjects()
            		
            		);

            userStoryDao.save(userStory);
            return userStory;
        }

    }

    @Transactional(readOnly = true)
    public UserStory findUserStory(Long userStoryId)
            throws InstanceNotFoundException {

        return userStoryDao.find(userStoryId);
    }

    public void updateUserStoryDetails(Long userStoryId,
            UserStoryDetails userStoryDetails)
            throws InstanceNotFoundException {

        UserStory userStory = userStoryDao.find(userStoryId);
        
        userStory.setUserStoryName(userStoryDetails.getUserStoryName());
        userStory.setStartDate(userStoryDetails.getStartDate());
        userStory.setEndDate(userStoryDetails.getEndDate());
        
        userStoryDao.save(userStory);
    }
    
    public List<UserStory> findAllOrderedByUserStoryNameIC() {
    	return userStoryDao.findAllOrderedByUserStoryNameIC();
    }

	@Override
	public void removeUserStory(Long userStoryId) throws InstanceNotFoundException {
		userStoryDao.remove(userStoryId);
	}
    
}
