package es.udc.fic.manoelfolgueira.gdai.model.project;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("projectDao")
public class ProjectDaoHibernate extends GenericDaoHibernate<Project, Long> implements ProjectDao {

	/**
	 * {@inheritDoc}
	 */
	public Project findByName(String projectName) throws InstanceNotFoundException {

    	Project project = (Project) getSession().createQuery(
    			"SELECT p FROM Project p WHERE g.projectName = :projectName")
    			.setParameter("projectName", projectName)
    			.uniqueResult();
    	if (project == null) {
   			throw new InstanceNotFoundException(projectName, Project.class.getName());
    	} else {
    		return project;
    	}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findAllOrderedByProjectName() {
		List<Project> projects = getSession().createQuery("SELECT p FROM Project p ORDER BY lower(p.projectName)").list();
		return projects;
	}

}