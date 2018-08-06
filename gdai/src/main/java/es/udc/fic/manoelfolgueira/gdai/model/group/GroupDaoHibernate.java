package es.udc.fic.manoelfolgueira.gdai.model.group;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("groupDao")
public class GroupDaoHibernate extends GenericDaoHibernate<Group, Long> implements GroupDao {

	public Group findByName(String name) throws InstanceNotFoundException {

    	Group group = (Group) getSession().createQuery(
    			"SELECT g FROM Group g WHERE u.name = :name")
    			.setParameter("name", name)
    			.uniqueResult();
    	if (group == null) {
   			throw new InstanceNotFoundException(name, Group.class.getName());
    	} else {
    		return group;
    	}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> findAll() {
		List<Group> groups = getSession().createQuery("SELECT g FROM Group g").list();
		return groups;
	}

}