package es.udc.fic.manoelfolgueira.gdai.model.services.userservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.IncorrectPasswordException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface UserService {

	/**
	 * Adds a new user
	 * 
	 * @param loginName
	 *            the login name to be registered
	 * @param clearPassword
	 *            their password not encrypted
	 * @param userProfileDetails
	 *            their dto
	 * @return the actual user created in GDAI
	 * @throws DuplicateInstanceException
	 */
	public UserDetails registerUser(String clearPassword, UserDetails userProfileDetails)
			throws DuplicateInstanceException;

	/**
	 * Lets a user get to GDAI if they enter they credentials properly
	 * 
	 * @param loginName
	 *            the user login name
	 * @param password
	 *            their password
	 * @param passwordIsEncrypted
	 *            a boolean to tell if the password is encrypted
	 * @return the user being logged into GDAI
	 * @throws InstanceNotFoundException
	 * @throws IncorrectPasswordException
	 */
	public UserDetails login(String loginName, String password, boolean passwordIsEncrypted)
			throws InstanceNotFoundException, IncorrectPasswordException;

	/**
	 * Find a user by its identifier
	 * 
	 * @param userProfileId
	 *            the user id
	 * @return the actual user
	 * @throws InstanceNotFoundException
	 */
	public UserDetails findUser(Long userId) throws InstanceNotFoundException;

	/**
	 * Returns a list of users ordered by their login name
	 * 
	 * @return the list of users
	 */
	public List<UserDetails> findAllSortedByName();

	/**
	 * Updates a user given its dto and its id
	 * 
	 * @param userProfileId
	 *            the id
	 * @param userProfileDetails
	 *            the dto
	 * @throws InstanceNotFoundException
	 */
	public void updateUserDetails(Long userProfileId, UserDetails userProfileDetails) throws InstanceNotFoundException;

	/**
	 * Allowes to change a password from a user
	 * 
	 * @param userProfileId
	 *            user's id
	 * @param oldClearPassword
	 *            current clear password
	 * @param newClearPassword
	 *            new clear password
	 * @throws IncorrectPasswordException
	 * @throws InstanceNotFoundException
	 */
	public void changePassword(Long userProfileId, String oldClearPassword, String newClearPassword)
			throws IncorrectPasswordException, InstanceNotFoundException;

	/**
	 * Removes a user from GDAI
	 * 
	 * @param userId
	 *            the id to remove the user
	 * @throws InstanceNotFoundException
	 */
	public void remove(Long userId) throws InstanceNotFoundException;

}
