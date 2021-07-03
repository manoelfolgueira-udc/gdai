package es.udc.fic.manoelfolgueira.gdai.model.services.groupservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

public interface GroupService {

	/**
	 * Creates a new group in GDAI
	 * 
	 * @param groupName
	 *            group name
	 * @param groupDetails
	 *            a group DTO
	 * @return the group details being created
	 * @throws DuplicateInstanceException
	 */
	public GroupDetails registerGroup(GroupDetails groupDetails)
			throws DuplicateInstanceException, InvalidDateException;

	/**
	 * Finds a group by its identifier
	 * 
	 * @param groupId
	 *            the identifier
	 * @return the group details that has been found
	 * @throws InstanceNotFoundException
	 */
	public GroupDetails findGroup(Long groupId) throws InstanceNotFoundException;

	/**
	 * Updates a group by its DTO interface details
	 * 
	 * @param groupId
	 *            the id of the group being update
	 * @param groupDetails
	 *            the details
	 * @throws InstanceNotFoundException
	 */
	public void updateGroupDetails(GroupDetails groupDetails) throws InstanceNotFoundException, InvalidDateException;

	/**
	 * Returns an ordered list by name (case insensitive) of all the groups details
	 * in GDAI
	 * 
	 * @return the list
	 */
	public List<GroupDetails> findAllOrderedByGroupName();

	/**
	 * Deletes a group
	 * 
	 * @param groupId
	 *            the identifier to locate a group to be deleted
	 * @throws InstanceNotFoundException
	 */
	public void removeGroup(Long groupId) throws InstanceNotFoundException;

}
