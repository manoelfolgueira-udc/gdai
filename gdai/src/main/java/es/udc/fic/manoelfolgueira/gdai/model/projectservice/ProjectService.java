package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ProjectService {

	/**
	 * Registers a new project
	 * @param projectDetails the DTO to be translated to a Project entity and saved
	 * @return the actual project being created
	 * @throws DuplicateInstanceException
	 */
    public Project createProject(ProjectDetails projectDetails) throws DuplicateInstanceException;

    /**
     * Find a project by its id
     * @param projectId the id
     * @return Returns a project referenced by the id provided
     * @throws InstanceNotFoundException
     */
    public Project findProject(Long projectId)
            throws InstanceNotFoundException;

    /**
     * Puts up-to-date a Project using an instance of its DTO
     * @param projectId the id of the Project being modified
     * @param projectDetails the DTO itself
     * @throws InstanceNotFoundException
     */
    public void updateProjectDetails(Long projectId,
            ProjectDetails projectDetails)
            throws InstanceNotFoundException;
    
    /**
     * Returns all project ordered by their names
     * @return the list of all projects in the database
     */
    public List<Project> findAllOrderedByProjectName();
    
    /**
     * Deletes a project by its id
     * @param projectId the id
     * @throws InstanceNotFoundException
     */
    public void removeProject(Long projectId) throws InstanceNotFoundException;

}
