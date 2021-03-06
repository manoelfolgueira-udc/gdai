package es.udc.fic.manoelfolgueira.gdai.model.productionpassservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPass;
import es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPassDao;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("productionPassService")
@Transactional
public class ProductionPassServiceImpl implements ProductionPassService {

	@Autowired
	private ProductionPassDao productionPassDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductionPassDetails createProductionPass(ProductionPassDetails productionPassDetails)
			throws DuplicateInstanceException {
		
		ProductionPass productionPass = new ProductionPass(productionPassDetails);
		
		productionPassDao.save(productionPass);
		return new ProductionPassDetails(productionPass);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public ProductionPassDetails findProductionPass(Long productionPassId) throws InstanceNotFoundException {
		return new ProductionPassDetails(productionPassDao.find(productionPassId));
	}

	@Override
	public void updateProductionPassDetails(Long productionPassId, ProductionPassDetails productionPassDetails)
			throws InstanceNotFoundException {
		ProductionPass productionPass = productionPassDao.find(productionPassId);

		productionPass.setProductionPassName(productionPassDetails.getProductionPassName());
		productionPass.setProductionPassResolution(productionPassDetails.getProductionPassResolution());
		if (productionPassDetails.getPassPath() != null)
			productionPass.setPassPath(productionPassDetails.getPassPath());

		productionPass.setCreatedBy(new User(productionPassDetails.getCreatedBy())); // should not be modified, consider getting
																			// rid of it

		productionPassDao.save(productionPass);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductionPassDetails> findAllOrderedByProductionPassId(SortingType sortingType) {
		
		LinkedList<ProductionPassDetails> productionPassesDetails = new LinkedList<>();
		List<ProductionPass> productionPasses = productionPassDao.findAllOrderedByProductionPassId(sortingType);

		for (ProductionPass productionPass : productionPasses) {
			productionPassesDetails.add(new ProductionPassDetails(productionPass));
		}

		return productionPassesDetails;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductionPassDetails> findByGroup(Long groupId) {
	
		LinkedList<ProductionPassDetails> productionPassesDetails = new LinkedList<>();
		List<ProductionPass> productionPasses = productionPassDao.findByGroup(groupId);

		for (ProductionPass productionPass : productionPasses) {
			productionPassesDetails.add(new ProductionPassDetails(productionPass));
		}

		return productionPassesDetails;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeProductionPass(Long productionPassId) throws InstanceNotFoundException {
		productionPassDao.remove(productionPassId);

	}

}
