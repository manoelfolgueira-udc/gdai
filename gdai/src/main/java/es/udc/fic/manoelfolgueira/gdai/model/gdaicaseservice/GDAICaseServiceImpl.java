package es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.gdaicase.GDAICase;
import es.udc.fic.manoelfolgueira.gdai.model.gdaicase.GDAICaseDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType;
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
	public GDAICase createGDAICase(GDAICaseDetails gDAICaseDetails) {
		GDAICase gdaiCase = new GDAICase(gDAICaseDetails.getGDAICaseDescription(), gDAICaseDetails.getGDAICaseResolution(),
				gDAICaseDetails.getCreationDate(), gDAICaseDetails.getCreatedBy(),
				gDAICaseDetails.getSystem());

		gdaiCaseDao.save(gdaiCase);
		return gdaiCase;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public GDAICase findGDAICase(Long gdaiCaseId) throws InstanceNotFoundException {
		return gdaiCaseDao.find(gdaiCaseId);
	}

	@Override
	public void updateGDAICaseDetails(Long gdaiCaseId, GDAICaseDetails gDAICaseDetails) throws InstanceNotFoundException {
		GDAICase gdaiCase = gdaiCaseDao.find(gdaiCaseId);

		gdaiCase.setGDAICaseResolution(gDAICaseDetails.getGDAICaseResolution());
		gdaiCase.setGDAICaseDescription(gDAICaseDetails.getGDAICaseDescription());
		gdaiCase.setCreatedBy(gDAICaseDetails.getCreatedBy()); // should not be modified, consider getting rid of it
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeGDAICase(Long gdaiCaseId) throws InstanceNotFoundException {
		gdaiCaseDao.remove(gdaiCaseId);
	}

	/* (non-Javadoc)
	 * @see es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseService#findAllOrderedByGDAICaseId(es.udc.fic.manoelfolgueira.gdai.model.util.ModelConstants.SortingType)
	 */
	@Override
	public List<GDAICase> findAllOrderedByGDAICaseId(SortingType sortingType) {
		return gdaiCaseDao.findAllOrderedByGDAICaseId(sortingType);
	}

	/* (non-Javadoc)
	 * @see es.udc.fic.manoelfolgueira.gdai.model.gdaicaseservice.GDAICaseService#findByGroup(java.lang.Long)
	 */
	@Override
	public List<GDAICase> findByGroup(Long groupId) {
		return gdaiCaseDao.findByGroup(groupId);
	}

}
