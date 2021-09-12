package es.udc.fic.manoelfolgueira.gdai.model.entities.project;

import java.util.Calendar;
import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectDao.java
 */
public interface ProjectDao extends GenericDao<Project, Long> {

	/**
	 * Returns a Project by name
	 * 
	 * @param name
	 *            the project identifier
	 * @return the Project
	 */
	public Project findByName(String name) throws InstanceNotFoundException;

	/**
	 * Returns all projects
	 * 
	 * @return all Project
	 */
	public List<Project> findAllOrderedByProjectName();

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
	public List<Project> findByCriteria(String projectName, String projectDescription, String userStoryName,
			String userStoryDescription, Calendar creationDateStart, Calendar creationDateEnd, Long sprintId,
			Long groupId, Long systemId);
}
