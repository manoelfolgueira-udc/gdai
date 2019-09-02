package es.udc.fic.manoelfolgueira.gdai.model.productionpassservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPass;
import es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPassDao;
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
	public ProductionPass createProductionPass(ProductionPassDetails productionPassDetails) throws DuplicateInstanceException {
		ProductionPass productionPass = new ProductionPass(productionPassDetails.getProductionPassName(), productionPassDetails.getProductionPassResolution(),
				productionPassDetails.getCreationDate(), productionPassDetails.getPassPath(), productionPassDetails.getCreatedBy(),
				productionPassDetails.getSystem());

		productionPassDao.save(productionPass);
		return productionPass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public ProductionPass findProductionPass(Long productionPassId) throws InstanceNotFoundException {
		return productionPassDao.find(productionPassId);
	}

	@Override
	public void updateProductionPassDetails(Long productionPassId, ProductionPassDetails productionPassDetails) throws InstanceNotFoundException {
		ProductionPass productionPass = productionPassDao.find(productionPassId);

		productionPass.setProductionPassName(productionPassDetails.getProductionPassName());
		productionPass.setProductionPassResolution(productionPassDetails.getProductionPassResolution());
		if (productionPassDetails.getPassPath() != null) productionPass.setPassPath(productionPassDetails.getPassPath());

		productionPass.setCreatedBy(productionPassDetails.getCreatedBy()); // should not be modified, consider getting rid of it
	}

	/* (non-Javadoc)
	 * @see es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassService#findAllOrderedByProductionPassId(es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType)
	 */
	@Override
	public List<ProductionPass> findAllOrderedByProductionPassId(SortingType sortingType) {
		return productionPassDao.findAllOrderedByProductionPassId(sortingType);
	}

	/* (non-Javadoc)
	 * @see es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassService#findByGroup(java.lang.Long)
	 */
	@Override
	public List<ProductionPass> findByGroup(Long groupId) {
		return productionPassDao.findByGroup(groupId);
	}

	/* (non-Javadoc)
	 * @see es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassService#removeProductionPass(java.lang.Long)
	 */
	@Override
	public void removeProductionPass(Long productionPassId) throws InstanceNotFoundException {
		productionPassDao.remove(productionPassId);
		
	}

}
