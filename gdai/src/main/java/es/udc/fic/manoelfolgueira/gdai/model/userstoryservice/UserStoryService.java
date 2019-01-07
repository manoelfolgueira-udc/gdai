package es.udc.fic.manoelfolgueira.gdai.model.userstoryservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.userstory.UserStory;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface UserStoryService {

    public UserStory registerUserStory(String userStoryName,
            UserStoryDetails userStoryDetails)
            throws DuplicateInstanceException;

    public UserStory findUserStory(Long userStoryId)
            throws InstanceNotFoundException;

    public void updateUserStoryDetails(Long userStoryId,
            UserStoryDetails userStoryDetails)
            throws InstanceNotFoundException;
    
    public List<UserStory> findAllOrderedByUserStoryNameIC();
    
    public void removeUserStory(Long userStoryId) throws InstanceNotFoundException;

}
