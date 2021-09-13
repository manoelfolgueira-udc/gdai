package es.udc.fic.manoelfolgueira.gdai.model.services.projectsfilterservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter.ProjectsFilter;
import es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter.ProjectsFilterDao;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("projectsFilterService")
@Transactional
public class ProjectsFilterServiceImpl implements ProjectsFilterService {

	@Autowired
	private ProjectsFilterDao projectsFilterDao;

	@Override
	public ProjectsFilter save(ProjectsFilter pf) {
		projectsFilterDao.save(pf);
		return pf;
		
	}

	@Override
	public ProjectsFilter load(ProjectsFilter pf) {
		try {
			return projectsFilterDao.find(pf.getProjectsFilterId());
		} catch (InstanceNotFoundException e) {
			return new ProjectsFilter();
		}
	}

	@Override
	public ProjectsFilter load(User u) throws InstanceNotFoundException {
		return projectsFilterDao.load(u.getUserId());
	}
	
}
