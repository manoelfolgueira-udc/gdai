package es.udc.fic.manoelfolgueira.gdai.model.services.sprintservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.entities.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.entities.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.entities.sprint.SprintDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SprintDetails;
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
	public SprintDetails registerSprint(String name, SprintDetails sprintDetails) throws DuplicateInstanceException {

		try {
			sprintDao.findByName(name);
			throw new DuplicateInstanceException(name, Sprint.class.getName());
		} catch (InstanceNotFoundException e) {

			LinkedList<Project> projects = new LinkedList<>();
			sprintDetails.getProjectsDetails().forEach(p -> {
				projects.add(new Project(p));
			});

			Sprint sprint = new Sprint(

					sprintDetails.getSprintName(), sprintDetails.getStartDate(), sprintDetails.getEndDate(),
					sprintDetails.getCreationDate(), projects

			);

			sprintDao.save(sprint);

			return new SprintDetails(sprint);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public SprintDetails findSprint(Long sprintId) throws InstanceNotFoundException {

		return new SprintDetails(sprintDao.find(sprintId));
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateSprintDetails(Long sprintId, SprintDetails sprintDetails) throws InstanceNotFoundException {

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
	public List<SprintDetails> findAllOrderedBySprintName(SortingType sortingType) {

		LinkedList<SprintDetails> sprintsDetails = new LinkedList<>();

		sprintDao.findAllOrderedBySprintName(sortingType).forEach(s -> {
			sprintsDetails.add(new SprintDetails(s));
		});

		return sprintsDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeSprint(Long sprintId) throws InstanceNotFoundException {
		sprintDao.remove(sprintId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SprintDetails> findAllOrderedBySprintStart(SortingType sortingType, int n) {

		LinkedList<SprintDetails> sprintsDetails = new LinkedList<>();

		sprintDao.findBySprintStart(sortingType, n).forEach(s -> {
			sprintsDetails.add(new SprintDetails(s));
		});

		return sprintsDetails;

	}

}
