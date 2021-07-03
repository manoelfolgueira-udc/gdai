package es.udc.fic.manoelfolgueira.gdai.model.services.productionpassservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ProductionPassDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ProductionPassService {

	/**
	 * Registers a new ProductionPass
	 * 
	 * @param productionPassDetails
	 *            the DTO to be translated to a ProductionPass entity and saved
	 * @return the actual ProductionPass being created
	 * @throws DuplicateInstanceException
	 */
	public ProductionPassDetails createProductionPass(ProductionPassDetails productionPassDetails)
			throws DuplicateInstanceException;

	/**
	 * Find a ProductionPass by its id
	 * 
	 * @param ProductionPassId
	 *            the id
	 * @return Returns a ProductionPass referenced by the id provided
	 * @throws InstanceNotFoundException
	 */
	public ProductionPassDetails findProductionPass(Long ProductionPassId) throws InstanceNotFoundException;

	/**
	 * Puts up-to-date a ProductionPass using an instance of its DTO
	 * 
	 * @param ProductionPassId
	 *            the id of the ProductionPass being modified
	 * @param productionPassDetails
	 *            the DTO itself
	 * @throws InstanceNotFoundException
	 */
	public void updateProductionPassDetails(Long ProductionPassId, ProductionPassDetails productionPassDetails)
			throws InstanceNotFoundException;

	/**
	 * Deletes a ProductionPass by its id
	 * 
	 * @param productionPassId
	 *            the id
	 * @throws InstanceNotFoundException
	 */
	public void removeProductionPass(Long productionPassId) throws InstanceNotFoundException;

	/**
	 * Returns all productionPasss
	 * 
	 * @return all ProductionPass
	 */
	public List<ProductionPassDetails> findAllOrderedByProductionPassId(SortingType sortingType);

	/**
	 * Find ProductionPasss by group
	 * 
	 * @param groupId
	 * @return the ProductionPasss found
	 */
	public List<ProductionPassDetails> findByGroup(Long groupId);

}
