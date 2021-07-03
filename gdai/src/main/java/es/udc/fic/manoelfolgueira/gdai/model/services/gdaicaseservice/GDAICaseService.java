package es.udc.fic.manoelfolgueira.gdai.model.services.gdaicaseservice;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GDAICaseDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface GDAICaseService {

	/**
	 * Registers a new gdaiCase
	 * 
	 * @param gDAICaseDetails
	 *            the DTO to be translated to a GDAICase entity and saved
	 * @return the actual gdaiCase being created
	 * @throws DuplicateInstanceException
	 */
	public GDAICaseDetails createGDAICase(GDAICaseDetails gDAICaseDetails) throws DuplicateInstanceException;

	/**
	 * Find a gdaiCase by its id
	 * 
	 * @param gdaiCaseId
	 *            the id
	 * @return Returns a gdaiCase referenced by the id provided
	 * @throws InstanceNotFoundException
	 */
	public GDAICaseDetails findGDAICase(Long gdaiCaseId) throws InstanceNotFoundException;

	/**
	 * Puts up-to-date a GDAICase using an instance of its DTO
	 * 
	 * @param gdaiCaseId
	 *            the id of the GDAICase being modified
	 * @param gDAICaseDetails
	 *            the DTO itself
	 * @throws InstanceNotFoundException
	 */
	public void updateGDAICaseDetails(Long gdaiCaseId, GDAICaseDetails gDAICaseDetails)
			throws InstanceNotFoundException;

	/**
	 * Deletes a gdaiCase by its id
	 * 
	 * @param gdaiCaseId
	 *            the id
	 * @throws InstanceNotFoundException
	 */
	public void removeGDAICase(Long gdaiCaseId) throws InstanceNotFoundException;

	/**
	 * Returns all gdaiCases
	 * 
	 * @return all GDAICase
	 */
	public List<GDAICaseDetails> findAllOrderedByGDAICaseId(SortingType sortingType);

	/**
	 * Find GDAICases by group
	 * 
	 * @param groupId
	 * @return the GDAICases found
	 */
	public List<GDAICaseDetails> findByGroup(Long groupId);

}
