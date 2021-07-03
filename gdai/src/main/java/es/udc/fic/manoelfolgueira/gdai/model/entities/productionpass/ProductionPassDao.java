package es.udc.fic.manoelfolgueira.gdai.model.entities.productionpass;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProjectDao.java
 */
public interface ProductionPassDao extends GenericDao<ProductionPass, Long> {

	/**
	 * Returns all productionPasss
	 * 
	 * @return all ProductionPass
	 */
	public List<ProductionPass> findAllOrderedByProductionPassId(SortingType sortingType);

	/**
	 * Find ProductionPasss by group
	 * 
	 * @param groupId
	 * @return the ProductionPasss found
	 */
	public List<ProductionPass> findByGroup(Long groupId);

	/**
	 * Gets a productionPass by its name
	 * 
	 * @param productionPassName
	 * @return a productionPass
	 */
	public ProductionPass findByName(String productionPassName) throws InstanceNotFoundException;
}
