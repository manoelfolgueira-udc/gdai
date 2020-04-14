package es.udc.fic.manoelfolgueira.gdai.model.project;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.Utils;
import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("projectDao")
public class ProjectDaoHibernate extends GenericDaoHibernate<Project, Long> implements ProjectDao {

	/**
	 * {@inheritDoc}
	 */
	public Project findByName(String projectName) throws InstanceNotFoundException {

		Project project = (Project) getSession()
				.createQuery("SELECT p FROM Project p WHERE p.projectName = :projectName")
				.setParameter("projectName", projectName).uniqueResult();
		if (project == null) {
			throw new InstanceNotFoundException(projectName, Project.class.getName());
		} else {
			return project;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findAllOrderedByProjectName() {
		List<Project> projects = getSession().createQuery("SELECT p FROM Project p ORDER BY lower(p.projectName)")
				.list();
		return projects;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findByCriteria(String projectId, String projectDescription, String userStoryId,
			String userStoryDescription, Calendar creationDateStart, Calendar creationDateEnd, Long sprintId,
			Long groupId, Long systemId) {

		boolean alreadyCond = false;
		String query = "SELECT p FROM Project p inner join p.sprints sp inner join p.system sy inner join p.userStory u inner join sy.group g ";

		if (projectId != null || projectDescription != null || userStoryId != null || userStoryDescription != null
				|| creationDateStart != null || creationDateEnd != null || sprintId != null || groupId != null
				|| systemId != null) {
			query += "WHERE ";
		}

		String projectIdParam = "";
		if (projectId != null) {
			alreadyCond = true;
			projectIdParam = Utils.getSQLWildcardedParam(projectId);
			query += "cast(p.projectId as string) like :projectIdParam ";
		}

		String projectDescriptionParam = "";
		if (projectDescription != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			projectDescriptionParam = Utils.getSQLWildcardedParam(projectDescription);
			query += "lower(p.projectDescription) like lower(:projectDescriptionParam) ";
		}

		String userStoryIdParam = "";
		if (userStoryId != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			userStoryIdParam = Utils.getSQLWildcardedParam(userStoryId);
			query += "cast(u.userStoryId as string) like :userStoryIdParam ";
		}

		String userStoryDescriptionParam = "";
		if (userStoryDescription != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			userStoryDescriptionParam = Utils.getSQLWildcardedParam(userStoryDescription);
			query += "lower(u.userStoryDescription) like lower(:userStoryDescriptionParam) ";
		}
		if (creationDateStart != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			query += "p.creationDate >= :creationDateStartParam ";
		}
		if (creationDateEnd != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			query += "p.creationDate <= :creationDateEndParam ";
		}
		if (sprintId != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			query += "sp.sprintId = :sprintIdParam ";
		}
		if (systemId != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			query += "sy.systemId = :systemIdParam ";
		}
		if (groupId != null) {
			if (alreadyCond)
				query += "AND ";
			alreadyCond = true;
			query += "g.groupId = :groupIdParam ";
		}

		query += "order by p.projectId DESC";

		Query q = getSession().createQuery(query);

		if (projectId != null) {
			q.setParameter("projectIdParam", projectIdParam);
		}
		if (projectDescription != null) {
			q.setParameter("projectDescriptionParam", projectDescriptionParam);
		}
		if (userStoryId != null) {
			q.setParameter("userStoryIdParam", userStoryIdParam);
		}
		if (userStoryDescription != null) {
			q.setParameter("userStoryDescriptionParam", userStoryDescriptionParam);
		}
		if (creationDateStart != null) {
			q.setDate("creationDateStartParam", creationDateStart.getTime());
		}
		if (creationDateEnd != null) {
			q.setDate("creationDateEndParam", creationDateEnd.getTime());
		}
		if (sprintId != null) {
			q.setParameter("sprintIdParam", sprintId);
		}
		if (systemId != null) {
			q.setParameter("systemIdParam", systemId);
		}
		if (groupId != null) {
			q.setParameter("groupIdParam", groupId);
		}

		List<Project> projects = q.list();

		return projects;
	}

}