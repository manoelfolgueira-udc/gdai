package es.udc.fic.manoelfolgueira.gdai.model.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("sprintDao")
public class SprintDaoHibernate extends GenericDaoHibernate<Sprint, Long> implements SprintDao {

	public Sprint findByName(String sprintName) throws InstanceNotFoundException {

    	Sprint sprint = (Sprint) getSession().createQuery(
    			"SELECT g FROM Sprint g WHERE g.sprintName = :sprintName")
    			.setParameter("sprintName", sprintName)
    			.uniqueResult();
    	if (sprint == null) {
   			throw new InstanceNotFoundException(sprintName, Sprint.class.getName());
    	} else {
    		return sprint;
    	}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sprint> findAllOrderedBySprintNameIC() {
		List<Sprint> sprints = getSession().createQuery("SELECT g FROM Sprint g ORDER BY lower(g.sprintName)").list();
		return sprints;
	}

}