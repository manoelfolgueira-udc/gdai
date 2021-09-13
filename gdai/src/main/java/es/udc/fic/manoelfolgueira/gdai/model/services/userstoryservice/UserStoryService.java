package es.udc.fic.manoelfolgueira.gdai.model.services.userstoryservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserStoryDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface UserStoryService {

	/**
	 * Adds a new UserStory
	 * 
	 * @param userStoryName
	 *            its name
	 * @param userStoryDetails
	 *            its details as a DTO
	 * @return a real UserStory being saved
	 * @throws DuplicateInstanceException
	 */
	public UserStoryDetails registerUserStory(String userStoryName, UserStoryDetails userStoryDetails)
			throws DuplicateInstanceException;

	/**
	 * Find a UserStory by its id
	 * 
	 * @param userStoryId
	 *            the id
	 * @return the UserStory
	 * @throws InstanceNotFoundException
	 */
	public UserStoryDetails findUserStory(Long userStoryId) throws InstanceNotFoundException;

	/**
	 * Updates a UserStory
	 * 
	 * @param userStoryId
	 *            its id to look for it
	 * @param userStoryDetails
	 *            the details as a DTO
	 * @throws InstanceNotFoundException
	 */
	public void updateUserStoryDetails(Long userStoryId, UserStoryDetails userStoryDetails)
			throws InstanceNotFoundException;

	/**
	 * Returns all userStories ordered by their names
	 * 
	 * @return All userStories stored in GDAI ordered by their names
	 */
	public List<UserStoryDetails> findAllOrderedByUserStoryName();

	/**
	 * Removes a userStory by its id
	 * 
	 * @param userStoryId
	 *            the id
	 * @throws InstanceNotFoundException
	 */
	public void removeUserStory(Long userStoryId) throws InstanceNotFoundException;

}
