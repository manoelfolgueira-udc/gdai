package es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectDao.java
 */
public interface ProjectsFilterDao extends GenericDao<ProjectsFilter, Long> {

	public ProjectsFilter load(Long userId) throws InstanceNotFoundException;
	
}
