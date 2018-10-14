package es.udc.fic.manoelfolgueira.gdai.model.application;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("applicationDao")
public class ApplicationDaoHibernate extends GenericDaoHibernate<Application, Long> implements ApplicationDao {

	public Application findByName(String applicationName) throws InstanceNotFoundException {

    	Application application = (Application) getSession().createQuery(
    			"SELECT p FROM Application p WHERE g.applicationName = :applicationName")
    			.setParameter("applicationName", applicationName)
    			.uniqueResult();
    	if (application == null) {
   			throw new InstanceNotFoundException(applicationName, Application.class.getName());
    	} else {
    		return application;
    	}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findAllOrderedByApplicationName() {
		List<Application> applications = getSession().createQuery("SELECT p FROM Application p ORDER BY p.applicationName").list();
		return applications;
	}

}