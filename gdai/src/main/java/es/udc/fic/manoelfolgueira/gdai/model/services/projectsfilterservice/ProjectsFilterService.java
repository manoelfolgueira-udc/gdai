package es.udc.fic.manoelfolgueira.gdai.model.services.projectsfilterservice;

import es.udc.fic.manoelfolgueira.gdai.model.entities.projectsfilter.ProjectsFilter;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ProjectsFilterService {

	public ProjectsFilter save(ProjectsFilter pf);
	public ProjectsFilter load(ProjectsFilter pf) throws InstanceNotFoundException;
	public ProjectsFilter load(User u) throws InstanceNotFoundException;

}
