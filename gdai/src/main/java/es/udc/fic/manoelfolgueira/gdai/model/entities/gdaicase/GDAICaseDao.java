package es.udc.fic.manoelfolgueira.gdai.model.entities.gdaicase;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;

/**
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GDAICaseDao.java
 */
public interface GDAICaseDao extends GenericDao<GDAICase, Long> {

	/**
	 * Returns all gdaiCases
	 * 
	 * @return all GDAICase
	 */
	public List<GDAICase> findAllOrderedByGDAICaseId(SortingType sortingType);

	/**
	 * Find GDAICases by group
	 * 
	 * @param groupId
	 * @return the GDAICases found
	 */
	public List<GDAICase> findByGroup(Long groupId);
}
