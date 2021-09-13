package es.udc.fic.manoelfolgueira.gdai.model.services.projectservice;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.entities.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.entities.project.ProjectDao;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ProjectDetails;
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
	public ProjectDetails createProject(ProjectDetails projectDetails) throws DuplicateInstanceException {
		try {
			projectDao.findByName(projectDetails.getProjectName());
			throw new DuplicateInstanceException(projectDetails.getProjectName(), Project.class.getName());
		} catch (InstanceNotFoundException e) {
			
			Project p = new Project(projectDetails);
			
			projectDao.save(p);
			
			projectDetails.setProjectId(p.getProjectId());
			
			return projectDetails;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public ProjectDetails findProject(Long projectId) throws InstanceNotFoundException {
		return new ProjectDetails(projectDao.find(projectId));
	}

	@Override
	public void updateProjectDetails(Long projectId, ProjectDetails projectDetails) throws InstanceNotFoundException {
		Project project = projectDao.find(projectId);

		project.setProjectName(projectDetails.getProjectName());
		project.setProjectDescription(projectDetails.getProjectDescription());
		if (projectDetails.getRequirementsPath() != null)
			project.setRequirementsPath(projectDetails.getRequirementsPath());

		project.setCreatedBy(new User(projectDetails.getCreatedBy())); // should not be modified, consider getting rid of it

		projectDao.save(project);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ProjectDetails> findAllOrderedByProjectName() {
		
		LinkedList<ProjectDetails> projectsDetails = new LinkedList<>();
		
		projectDao.findAllOrderedByProjectName().forEach(p -> {
			projectsDetails.add(new ProjectDetails(p));
		});
				
		return projectsDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeProject(Long projectId) throws InstanceNotFoundException {
		projectDao.remove(projectId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProjectDetails> findByCriteria(String projectName, String projectDescription, String userStoryName,
			String userStoryDescription, Calendar creationDateStart, Calendar creationDateEnd, Long sprintId,
			Long groupId, Long systemId) {
		
		LinkedList<ProjectDetails> projectsDetails = new LinkedList<>();
		
		projectDao.findByCriteria(projectName, projectDescription, userStoryName, userStoryDescription,
				creationDateStart, creationDateEnd, sprintId, groupId, systemId).forEach(p -> {
					projectsDetails.add(new ProjectDetails(p));
				});
		
		return projectsDetails;
	}

}
