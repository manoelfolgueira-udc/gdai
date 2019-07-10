package es.udc.fic.manoelfolgueira.gdai.model.groupservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface GroupService {

	/**
	 * Creates a new group in GDAI
	 * @param groupName group name
	 * @param groupDetails a group DTO
	 * @return the group being created
	 * @throws DuplicateInstanceException
	 */
    public Group registerGroup(String groupName,
            GroupDetails groupDetails)
            throws DuplicateInstanceException;

    /**
     * Finds a group by its identifier
     * @param groupId the identifier
     * @return the group that has been found
     * @throws InstanceNotFoundException
     */
    public Group findGroup(Long groupId)
            throws InstanceNotFoundException;

    /**
     * Updates a group by its DTO interface details
     * @param groupId the id of the group being update
     * @param groupDetails the details
     * @throws InstanceNotFoundException
     */
    public void updateGroupDetails(Long groupId,
            GroupDetails groupDetails)
            throws InstanceNotFoundException;
    
    /**
     * Returns an ordered list by name (case insensitive) of all the groups in GDAI
     * @return the list
     */
    public List<Group> findAllOrderedByGroupName();
    
    /**
     * Deletes a group
     * @param groupId the identifier to locate a group to be deleted
     * @throws InstanceNotFoundException
     */
    public void removeGroup(Long groupId) throws InstanceNotFoundException;

}
