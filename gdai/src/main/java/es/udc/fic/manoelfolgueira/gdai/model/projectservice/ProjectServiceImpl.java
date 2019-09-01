package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.project.ProjectDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Project createProject(ProjectDetails projectDetails) throws DuplicateInstanceException {
		try {
			projectDao.findByName(projectDetails.getProjectName());
			throw new DuplicateInstanceException(projectDetails.getProjectName(), Project.class.getName());
		} catch (InstanceNotFoundException e) {

			Project project = new Project(projectDetails.getProjectName(), projectDetails.getProjectDescription(),
					projectDetails.getCreationDate(), projectDetails.getRequirementsPath(), projectDetails.getCreatedBy(),
					projectDetails.getSystem(), projectDetails.getSprints(), projectDetails.getUserStory());

			projectDao.save(project);
			return project;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Project findProject(Long projectId) throws InstanceNotFoundException {
		return projectDao.find(projectId);
	}

	@Override
	public void updateProjectDetails(Long projectId, ProjectDetails projectDetails) throws InstanceNotFoundException {
		Project project = projectDao.find(projectId);

		project.setProjectName(projectDetails.getProjectName());
		project.setProjectDescription(projectDetails.getProjectDescription());
		if (projectDetails.getRequirementsPath() != null) project.setRequirementsPath(projectDetails.getRequirementsPath());

		project.setCreatedBy(projectDetails.getCreatedBy()); // should not be modified, consider getting rid of it
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Project> findAllOrderedByProjectName() {
		return projectDao.findAllOrderedByProjectName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeProject(Long projectId) throws InstanceNotFoundException {
		projectDao.remove(projectId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.udc.fic.manoelfolgueira.gdai.model.projectservice.ProjectService#
	 * findByCriteria(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.util.Calendar, java.util.Calendar,
	 * es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint,
	 * es.udc.fic.manoelfolgueira.gdai.model.group.Group,
	 * es.udc.fic.manoelfolgueira.gdai.model.system.System)
	 */
	@Override
	public List<Project> findByCriteria(String projectId, String projectDescription, String userStoryId,
			String userStoryDescription, Calendar creationDateStart, Calendar creationDateEnd, Long sprintId,
			Long groupId, Long systemId) {
		return projectDao.findByCriteria(projectId, projectDescription, userStoryId, userStoryDescription,
				creationDateStart, creationDateEnd, sprintId, groupId, systemId);
	}

}
