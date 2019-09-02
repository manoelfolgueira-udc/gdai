package es.udc.fic.manoelfolgueira.gdai.model.sprintservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprint.SprintDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("sprintService")
@Transactional
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintDao sprintDao;

    /**
     * {@inheritDoc}
     */
    public Sprint registerSprint(String name,
            SprintDetails sprintDetails)
            throws DuplicateInstanceException {

        try {
            sprintDao.findByName(name);
            throw new DuplicateInstanceException(name,
                    Sprint.class.getName());
        } catch (InstanceNotFoundException e) {
        	
            Sprint sprint = new Sprint(
            		
            		sprintDetails.getSprintName(),
            		sprintDetails.getStartDate(),
            		sprintDetails.getEndDate(),
            		sprintDetails.getCreationDate(),
            		sprintDetails.getProjects()
            		
            		);

            sprintDao.save(sprint);
            return sprint;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Sprint findSprint(Long sprintId)
            throws InstanceNotFoundException {

        return sprintDao.find(sprintId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateSprintDetails(Long sprintId,
            SprintDetails sprintDetails)
            throws InstanceNotFoundException {

        Sprint sprint = sprintDao.find(sprintId);
        
        sprint.setSprintName(sprintDetails.getSprintName());
        sprint.setSprintStart(sprintDetails.getStartDate());
        sprint.setSprintEnd(sprintDetails.getEndDate());
        
        sprintDao.save(sprint);
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	public List<Sprint> findAllOrderedBySprintName(SortingType sortingType) {
		return sprintDao.findAllOrderedBySprintName(sortingType);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void removeSprint(Long sprintId) throws InstanceNotFoundException {
		sprintDao.remove(sprintId);
	}

	/* (non-Javadoc)
	 * @see es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService#findAllOrderedBySprintStart(es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType)
	 */
	@Override
	public List<Sprint> findAllOrderedBySprintStart(SortingType sortingType, int n) {
		return sprintDao.findBySprintStart(sortingType, n);
	}

	
    
}
