package es.udc.fic.manoelfolgueira.gdai.model.projectservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ProjectService {

    public Project createProject(ProjectDetails projectDetails) throws DuplicateInstanceException;

    public Project findProject(Long projectId)
            throws InstanceNotFoundException;

    public void updateProjectDetails(Long projectId,
            ProjectDetails projectDetails)
            throws InstanceNotFoundException;
    
    public List<Project> findAllOrderedByProjectName();
    
    public void removeProject(Long projectId) throws InstanceNotFoundException;

}
