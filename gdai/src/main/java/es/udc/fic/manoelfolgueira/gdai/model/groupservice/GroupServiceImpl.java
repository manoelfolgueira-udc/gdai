package es.udc.fic.manoelfolgueira.gdai.model.groupservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.group.GroupDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    public Group registerGroup(String name,
            GroupDetails groupDetails)
            throws DuplicateInstanceException {

        try {
            groupDao.findByName(name);
            throw new DuplicateInstanceException(name,
                    Group.class.getName());
        } catch (InstanceNotFoundException e) {
        	
            Group group = new Group(groupDetails.getGroupName(), null, groupDetails.getExpirationDate(), null);

            groupDao.save(group);
            return group;
        }

    }

    @Transactional(readOnly = true)
    public Group findGroup(Long groupId)
            throws InstanceNotFoundException {

        return groupDao.find(groupId);
    }

    public void updateGroupDetails(Long groupId,
            GroupDetails groupDetails)
            throws InstanceNotFoundException {

        Group group = groupDao.find(groupId);
        
        group.setGroupName(groupDetails.getGroupName());
        group.setExpirationDate(groupDetails.getExpirationDate());
    }
    
    public List<Group> findAllOrderedByGroupNameIC() {
    	return groupDao.findAllOrderedByGroupNameIC();
    }

	@Override
	public void removeGroup(Long groupId) throws InstanceNotFoundException {
		groupDao.remove(groupId);
	}
    
}
