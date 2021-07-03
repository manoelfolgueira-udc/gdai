package es.udc.fic.manoelfolgueira.gdai.model.services.projectservice;

import java.util.Calendar;
import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ProjectDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ProjectService {

	/**
	 * Registers a new project
	 * 
	 * @param projectDetails
	 *            the DTO to be translated to a Project entity and saved
	 * @return the actual project being created
	 * @throws DuplicateInstanceException
	 */
	public ProjectDetails createProject(ProjectDetails projectDetails) throws DuplicateInstanceException;

	/**
	 * Find a project by its id
	 * 
	 * @param projectId
	 *            the id
	 * @return Returns a project referenced by the id provided
	 * @throws InstanceNotFoundException
	 */
	public ProjectDetails findProject(Long projectId) throws InstanceNotFoundException;

	/**
	 * Puts up-to-date a Project using an instance of its DTO
	 * 
	 * @param projectId
	 *            the id of the Project being modified
	 * @param projectDetails
	 *            the DTO itself
	 * @throws InstanceNotFoundException
	 */
	public void updateProjectDetails(Long projectId, ProjectDetails projectDetails) throws InstanceNotFoundException;

	/**
	 * Returns all project ordered by their names
	 * 
	 * @return the list of all projects in the database
	 */
	public List<ProjectDetails> findAllOrderedByProjectName();

	/**
	 * Deletes a project by its id
	 * 
	 * @param projectId
	 *            the id
	 * @throws InstanceNotFoundException
	 */
	public void removeProject(Long projectId) throws InstanceNotFoundException;

	/**
	 * Finds a list of projects according to the given criteria
	 * 
	 * @param projectId
	 * @param projectDescription
	 * @param userStoryId
	 * @param userStoryDescription
	 * @param creationDateStart
	 * @param creationDateEnd
	 * @param sprintId
	 * @param groupId
	 * @param systemId
	 * @return a List of projects
	 */
	public List<ProjectDetails> findByCriteria(String projectId, String projectDescription, String userStoryId,
			String userStoryDescription, Calendar creationDateStart, Calendar creationDateEnd, Long sprintId,
			Long groupId, Long systemId);

}
