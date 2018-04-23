package es.udc.fic.manoelfolgueira.gdai.model.group;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("userDao")
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

}