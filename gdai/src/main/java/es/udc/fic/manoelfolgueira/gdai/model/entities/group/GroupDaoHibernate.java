package es.udc.fic.manoelfolgueira.gdai.model.entities.group;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("groupDao")
public class GroupDaoHibernate extends GenericDaoHibernate<Group, Long> implements GroupDao {

	/**
	 * {@inheritDoc}
	 */
	public Group findByName(String groupName) throws InstanceNotFoundException {

		Group group = (Group) getSession().createQuery("SELECT g FROM Group g WHERE g.groupName = :groupName")
				.setParameter("groupName", groupName).uniqueResult();
		if (group == null) {
			throw new InstanceNotFoundException(groupName, Group.class.getName());
		} else {
			return group;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> findAllOrderedByGroupName() {
		List<Group> groups = getSession().createQuery("SELECT g FROM Group g ORDER BY lower(g.groupName)").list();
		return groups;
	}

}