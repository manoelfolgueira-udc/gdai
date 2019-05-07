package es.udc.fic.manoelfolgueira.gdai.model.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("sprintDao")
public class SprintDaoHibernate extends GenericDaoHibernate<Sprint, Long> implements SprintDao {

	/**
	 * {@inheritDoc}
	 */
	public Sprint findByName(String sprintName) throws InstanceNotFoundException {

    	Sprint sprint = (Sprint) getSession().createQuery(
    			"SELECT s FROM Sprint s WHERE s.sprintName = :sprintName")
    			.setParameter("sprintName", sprintName)
    			.uniqueResult();
    	if (sprint == null) {
   			throw new InstanceNotFoundException(sprintName, Sprint.class.getName());
    	} else {
    		return sprint;
    	}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sprint> findAllOrderedBySprintName() {
		List<Sprint> sprints = getSession().createQuery("SELECT s FROM Sprint s ORDER BY lower(s.sprintName)").list();
		return sprints;
	}

}