package es.udc.fic.manoelfolgueira.gdai.model.groupservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface GroupService {

    public Group registerGroup(String groupName,
            GroupDetails groupDetails)
            throws DuplicateInstanceException;

    public Group findGroup(Long groupId)
            throws InstanceNotFoundException;

    public void updateGroupDetails(Long groupId,
            GroupDetails groupDetails)
            throws InstanceNotFoundException;
    
    public List<Group> findAllOrderedByGroupName();
    
    public void removeGroup(Long groupId) throws InstanceNotFoundException;

}
