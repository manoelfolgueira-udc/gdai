package es.udc.fic.manoelfolgueira.gdai.model.services.groupservice;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.entities.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.entities.group.GroupDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InvalidDateException;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;

	/**
	 * {@inheritDoc}
	 */
	public GroupDetails registerGroup(GroupDetails groupDetails)
			throws DuplicateInstanceException, InvalidDateException {

		try {
			groupDao.findByName(groupDetails.getGroupName());
			throw new DuplicateInstanceException(groupDetails.getGroupName(), Group.class.getName());
		} catch (InstanceNotFoundException e) {

			// Group can expire the day created
			Calendar cal = Calendar.getInstance();
			cal.setTime(groupDetails.getExpirationDate().getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);

			if (Calendar.getInstance().after(cal)) {
				throw new InvalidDateException(groupDetails, GroupDetails.class.getCanonicalName());
			}

			Group group = new Group(groupDetails);
			groupDao.save(group);

			groupDetails.setGroupId(group.getGroupId());

			return groupDetails;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public GroupDetails findGroup(Long groupId) throws InstanceNotFoundException {

		return new GroupDetails(groupDao.find(groupId));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws InvalidDateException
	 */
	public void updateGroupDetails(GroupDetails groupDetails) throws InstanceNotFoundException, InvalidDateException {

		Group group = groupDao.find(groupDetails.getGroupId());

		// Group can expire the day created
		Calendar cal = Calendar.getInstance();
		cal.setTime(groupDetails.getExpirationDate().getTime());
		cal.add(Calendar.DAY_OF_MONTH, 1);

		if (Calendar.getInstance().after(cal)) {
			throw new InvalidDateException(groupDetails, GroupDetails.class.getCanonicalName());
		}

		group.setGroupName(groupDetails.getGroupName());
		group.setGroupDescription(groupDetails.getGroupDescription());
		group.setExpirationDate(groupDetails.getExpirationDate());

		groupDao.save(group);

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public List<GroupDetails> findAllOrderedByGroupName() {

		LinkedList<GroupDetails> groupsDetails = new LinkedList<>();
		List<Group> groups = groupDao.findAllOrderedByGroupName();

		for (Group group : groups) {
			groupsDetails.add(new GroupDetails(group));
		}

		return groupsDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeGroup(Long groupId) throws InstanceNotFoundException {
		groupDao.remove(groupId);
	}

}
