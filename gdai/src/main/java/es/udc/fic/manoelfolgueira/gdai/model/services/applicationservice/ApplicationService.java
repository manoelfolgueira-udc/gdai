package es.udc.fic.manoelfolgueira.gdai.model.services.applicationservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.ApplicationDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface ApplicationService {

	/**
	 * Creates a new application in GDAI
	 * 
	 * @param applicationName
	 *            application name
	 * @param applicationDetails
	 *            an application DTO
	 * @return the application being created
	 * @throws DuplicateInstanceException
	 */
	public ApplicationDetails registerApplication(ApplicationDetails applicationDetails)
			throws DuplicateInstanceException;

	/**
	 * Finds an application by an identifier
	 * 
	 * @param applicationId
	 *            the identifier
	 * @return the application entity found by its identifier
	 * @throws InstanceNotFoundException
	 */
	public ApplicationDetails findApplication(Long applicationId) throws InstanceNotFoundException;

	/**
	 * Modifies an existing application in GDAI with the DTO interface
	 * 
	 * @param applicationId
	 *            an application identifier
	 * @param applicationDetails
	 *            the DTO itself
	 * @throws InstanceNotFoundException
	 */
	public void updateApplicationDetails(Long applicationId, ApplicationDetails applicationDetails)
			throws InstanceNotFoundException;

	/**
	 * Returns an ordered list by name of all the applications
	 * 
	 * @return the ordered list
	 */
	public List<ApplicationDetails> findAllOrderedByApplicationName();

	/**
	 * Deletes an application by its identifier
	 * 
	 * @param applicationId
	 *            the identifier
	 * @throws InstanceNotFoundException
	 */
	public void remove(Long applicationId) throws InstanceNotFoundException;

}
