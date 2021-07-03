package es.udc.fic.manoelfolgueira.gdai.model.services.gdaicaseservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.entities.gdaicase.GDAICase;
import es.udc.fic.manoelfolgueira.gdai.model.entities.gdaicase.GDAICaseDao;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GDAICaseDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("gdaiCaseService")
@Transactional
public class GDAICaseServiceImpl implements GDAICaseService {

	@Autowired
	private GDAICaseDao gdaiCaseDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GDAICaseDetails createGDAICase(GDAICaseDetails gDAICaseDetails) {
		GDAICase gdaiCase = new GDAICase(gDAICaseDetails);
		gdaiCaseDao.save(gdaiCase);
		return new GDAICaseDetails(gdaiCase);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public GDAICaseDetails findGDAICase(Long gdaiCaseId) throws InstanceNotFoundException {
		return new GDAICaseDetails(gdaiCaseDao.find(gdaiCaseId));
	}

	@Override
	public void updateGDAICaseDetails(Long gdaiCaseId, GDAICaseDetails gDAICaseDetails)
			throws InstanceNotFoundException {
		GDAICase gdaiCase = gdaiCaseDao.find(gdaiCaseId);

		gdaiCase.setGDAICaseResolution(gDAICaseDetails.getGDAICaseResolution());
		gdaiCase.setGDAICaseDescription(gDAICaseDetails.getGDAICaseDescription());
		gdaiCase.setCreatedBy(new User(gDAICaseDetails.getCreatedBy())); // should not be modified, consider getting rid of it

		gdaiCaseDao.save(gdaiCase);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeGDAICase(Long gdaiCaseId) throws InstanceNotFoundException {
		gdaiCaseDao.remove(gdaiCaseId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GDAICaseDetails> findAllOrderedByGDAICaseId(SortingType sortingType) {		
		
		LinkedList<GDAICaseDetails> gdaiCaseesDetails = new LinkedList<>();
		List<GDAICase> gdaiCases = gdaiCaseDao.findAllOrderedByGDAICaseId(sortingType);

		for (GDAICase gc : gdaiCases) {
			gdaiCaseesDetails.add(new GDAICaseDetails(gc));
		}

		return gdaiCaseesDetails;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GDAICaseDetails> findByGroup(Long groupId) {
		
		LinkedList<GDAICaseDetails> gdaiCaseesDetails = new LinkedList<>();
		List<GDAICase> gdaiCases = gdaiCaseDao.findByGroup(groupId);

		for (GDAICase gc : gdaiCases) {
			gdaiCaseesDetails.add(new GDAICaseDetails(gc));
		}

		return gdaiCaseesDetails;
	}

}
