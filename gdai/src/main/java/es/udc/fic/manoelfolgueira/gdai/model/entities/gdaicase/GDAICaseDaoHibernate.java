package es.udc.fic.manoelfolgueira.gdai.model.entities.gdaicase;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;

@Repository("gdaiCaseDao")
public class GDAICaseDaoHibernate extends GenericDaoHibernate<GDAICase, Long> implements GDAICaseDao {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GDAICase> findAllOrderedByGDAICaseId(SortingType sortingType) {

		List<GDAICase> gDAICases = getSession()
				.createQuery("SELECT pc FROM GDAICase gc order by gc.gdaiCaseId " + sortingType.toString()).list();
		return gDAICases;

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GDAICase> findByGroup(Long groupId) {
		List<GDAICase> gDAICases = getSession().createQuery(
				"SELECT gc FROM GDAICase gc inner join gc.system s inner join s.group g where g.groupId = :groupId")
				.setParameter("groupId", groupId).list();
		return gDAICases;
	}

}