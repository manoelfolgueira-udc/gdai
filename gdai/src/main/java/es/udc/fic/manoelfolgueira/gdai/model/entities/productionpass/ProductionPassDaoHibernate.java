package es.udc.fic.manoelfolgueira.gdai.model.entities.productionpass;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.entities.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("productionPassDao")
public class ProductionPassDaoHibernate extends GenericDaoHibernate<ProductionPass, Long> implements ProductionPassDao {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductionPass> findAllOrderedByProductionPassId(SortingType sortingType) {

		List<ProductionPass> productionPasses = getSession()
				.createQuery("SELECT pp FROM ProductionPass pp order by pp.productionPassId " + sortingType.toString())
				.list();
		return productionPasses;

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductionPass> findByGroup(Long groupId) {
		List<ProductionPass> productionPasses = getSession().createQuery(
				"SELECT pp FROM ProductionPass pp inner join pp.system s inner join s.group g where g.groupId = :groupId")
				.setParameter("groupId", groupId).list();
		return productionPasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPassDao#
	 * findByName(java.lang.String)
	 */
	@Override
	public ProductionPass findByName(String productionPassName) throws InstanceNotFoundException {
		ProductionPass productionPass = (ProductionPass) getSession()
				.createQuery("SELECT pp FROM ProductionPass pp where pp.productionPassName = :productionPassName")
				.setParameter("productionPassName", productionPassName).uniqueResult();
		
		if (productionPass == null) {
			throw new InstanceNotFoundException(productionPassName, ProductionPass.class.getName());
		} else {
			return productionPass;
		}
	}

}