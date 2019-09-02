package es.udc.fic.manoelfolgueira.gdai.model.util.dao;

import java.io.Serializable;

import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * The generic DAO interface
 * @param <E> any Entity
 * @param <PK> its primary key
 */
public interface GenericDao <E, PK extends Serializable> {

	void save(E entity);

	E find(PK id) throws InstanceNotFoundException;

	void remove(PK id) throws InstanceNotFoundException;

}

