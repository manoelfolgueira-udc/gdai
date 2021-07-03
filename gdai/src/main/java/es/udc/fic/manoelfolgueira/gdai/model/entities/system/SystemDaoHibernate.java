package es.udc.fic.manoelfolgueira.gdai.model.entities.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("systemDao")
public class SystemDaoHibernate extends GenericDaoHibernate<System, Long> implements SystemDao {

	/**
	 * {@inheritDoc}
	 */
	public System findByName(String systemName) throws InstanceNotFoundException {

		System system = (System) getSession().createQuery("SELECT s FROM System s WHERE s.systemName = :systemName")
				.setParameter("systemName", systemName).uniqueResult();
		if (system == null) {
			throw new InstanceNotFoundException(systemName, System.class.getName());
		} else {
			return system;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<System> findAllOrderedBySystemName() {
		List<System> systems = getSession().createQuery("SELECT s FROM System s ORDER BY lower(s.systemName)").list();
		return systems;
	}

}