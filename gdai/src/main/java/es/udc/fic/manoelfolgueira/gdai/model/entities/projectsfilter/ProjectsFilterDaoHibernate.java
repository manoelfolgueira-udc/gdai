package es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("projectsFilterDao")
public class ProjectsFilterDaoHibernate extends GenericDaoHibernate<ProjectsFilter, Long> implements ProjectsFilterDao {

	@Override
	public ProjectsFilter load(Long userId) throws InstanceNotFoundException {
		return (ProjectsFilter) getSession()
				.createQuery("SELECT pf FROM ProjectsFilter pf join pf.createdBy u on u.userId = :userId")
				.setParameter("userId", userId).uniqueResult();
	}

}
