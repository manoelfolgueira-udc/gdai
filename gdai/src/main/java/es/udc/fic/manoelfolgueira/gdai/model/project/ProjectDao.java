package es.udc.fic.manoelfolgueira.gdai.model.project;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ProjectDao extends GenericDao<Project, Long> {

    /**
     * Returns a Project by name
     * @param name the project identifier
     * @return the Project
     */
    public Project findByName(String name) throws InstanceNotFoundException;
    
    /**
     * Returns all projects
     * @return all Project
     */
    public List<Project> findAllOrderedByProjectName();
}
