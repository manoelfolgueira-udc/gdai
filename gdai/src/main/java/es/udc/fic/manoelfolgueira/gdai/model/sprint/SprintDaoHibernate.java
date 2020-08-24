package es.udc.fic.manoelfolgueira.gdai.model.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("sprintDao")
public class SprintDaoHibernate extends GenericDaoHibernate<Sprint, Long> implements SprintDao {

	/**
	 * {@inheritDoc}
	 */
	public Sprint findByName(String sprintName) throws InstanceNotFoundException {

		Sprint sprint = (Sprint) getSession().createQuery("SELECT s FROM Sprint s WHERE s.sprintName = :sprintName")
				.setParameter("sprintName", sprintName).uniqueResult();
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
	public List<Sprint> findAllOrderedBySprintName(SortingType sortingType) {
		List<Sprint> sprints = getSession()
				.createQuery("SELECT s FROM Sprint s ORDER BY lower(s.sprintName)" + sortingType.toString()).list();
		return sprints;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.udc.fic.manoelfolgueira.gdai.model.sprint.SprintDao#findBySprintStart(es.
	 * udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sprint> findBySprintStart(SortingType sortingType, int n) {
		List<Sprint> sprints = getSession()
				.createQuery("SELECT s FROM Sprint s ORDER BY lower(s.sprintStart)" + sortingType.toString()).list();
		if (n > 0) {
			if (n < sprints.size()) {
				return sprints.subList(0, n);
			}
		}

		return sprints;
	}

}