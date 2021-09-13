package es.udc.fic.manoelfolgueira.gdai.model.services.systemservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface SystemService {

	/**
	 * Adds a new system to GDAI
	 * 
	 * @param systemName
	 *            the system name
	 * @param systemDetails
	 *            the dto details
	 * @return the real System being created
	 * @throws DuplicateInstanceException
	 */
	public SystemDetails registerSystem(SystemDetails systemDetails) throws DuplicateInstanceException;

	/**
	 * Find a system by its id
	 * 
	 * @param systemId
	 *            the id
	 * @return the System
	 * @throws InstanceNotFoundException
	 */
	public SystemDetails findSystem(Long systemId) throws InstanceNotFoundException;

	/**
	 * Modifies a system by its dto
	 * 
	 * @param systemId
	 *            the id to look for the system being modified
	 * @param systemDetails
	 *            the dto to modify the system
	 * @throws InstanceNotFoundException
	 */
	public void updateSystemDetails(Long systemId, SystemDetails systemDetails) throws InstanceNotFoundException;

	/**
	 * Returns all systems in GDAI ordered by their name
	 * 
	 * @return all the systems
	 */
	public List<SystemDetails> findAllOrderedBySystemName();

	/**
	 * Deletes a system by its id
	 * 
	 * @param systemId
	 *            the id
	 * @throws InstanceNotFoundException
	 */
	public void remove(Long systemId) throws InstanceNotFoundException;

}
