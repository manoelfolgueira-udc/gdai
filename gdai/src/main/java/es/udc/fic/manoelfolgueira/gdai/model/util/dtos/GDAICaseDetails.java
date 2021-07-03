package es.udc.fic.manoelfolgueira.gdai.model.util.dtos;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.entities.gdaicase.GDAICase;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class GDAICaseDetails extends GDAIDetailsCodificable {

	private Long gdaiCaseId;
	private String gdaiCaseDescription;
	private String gdaiCaseResolution;
	private Calendar creationDate = Calendar.getInstance();
	private UserDetails createdBy;
	private SystemDetails systemDetails;

	/**
	 * Empty constructor
	 */
	public GDAICaseDetails() {
	}

	/**
	 * Main constructor
	 * 
	 * @param gdaiCaseDescription
	 *            a description for this gdaiCase
	 * @param creationDate
	 *            when it's created
	 * @param createdBy
	 *            user that has registered this gdaiCase
	 * @param system
	 *            system related to this gdaiCase
	 */
	public GDAICaseDetails(Long gdaiCaseId, String gdaiCaseDescription, String gdaiCaseResolution, Calendar creationDate, UserDetails createdBy,
			SystemDetails systemDetails) {
		super();
		this.gdaiCaseId = gdaiCaseId;
		this.gdaiCaseDescription = gdaiCaseDescription;
		this.gdaiCaseResolution = gdaiCaseResolution;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.systemDetails = systemDetails;
	}

	/**
	 * @param gdaiCase
	 */
	public GDAICaseDetails(GDAICase gdaiCase) {
		super();
		this.gdaiCaseId = gdaiCase.getGDAICaseId();
		this.gdaiCaseDescription = gdaiCase.getGDAICaseDescription();
		this.gdaiCaseResolution = gdaiCase.getGDAICaseResolution();
		this.creationDate = gdaiCase.getCreationDate();
		this.createdBy = new UserDetails(gdaiCase.getCreatedBy());
		this.systemDetails = new SystemDetails(gdaiCase.getSystem());
	}

	/**
	 * @return the gdaiCaseId
	 */
	public Long getGDAICaseId() {
		return gdaiCaseId;
	}

	/**
	 * @param gdaiCaseId the gdaiCaseId to set
	 */
	public void setGDAICaseId(Long gdaiCaseId) {
		this.gdaiCaseId = gdaiCaseId;
	}

	/**
	 * @return the gdaiCaseDescription
	 */
	public String getGDAICaseDescription() {
		return gdaiCaseDescription;
	}

	/**
	 * @param gdaiCaseDescription the gdaiCaseDescription to set
	 */
	public void setGDAICaseDescription(String gdaiCaseDescription) {
		this.gdaiCaseDescription = gdaiCaseDescription;
	}

	/**
	 * @return the gdaiCaseResolution
	 */
	public String getGDAICaseResolution() {
		return gdaiCaseResolution;
	}

	/**
	 * @param gdaiCaseResolution the gdaiCaseResolution to set
	 */
	public void setGDAICaseResolution(String gdaiCaseResolution) {
		this.gdaiCaseResolution = gdaiCaseResolution;
	}

	/**
	 * @return the creationDate
	 */
	public Calendar getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	public UserDetails getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(UserDetails createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the systemDetails
	 */
	public SystemDetails getSystemDetails() {
		return systemDetails;
	}

	/**
	 * @param systemDetails the systemDetails to set
	 */
	public void setSystemDetails(SystemDetails systemDetails) {
		this.systemDetails = systemDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((gdaiCaseDescription == null) ? 0 : gdaiCaseDescription.hashCode());
		result = prime * result + ((gdaiCaseId == null) ? 0 : gdaiCaseId.hashCode());
		result = prime * result + ((gdaiCaseResolution == null) ? 0 : gdaiCaseResolution.hashCode());
		result = prime * result + ((systemDetails == null) ? 0 : systemDetails.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GDAICaseDetails other = (GDAICaseDetails) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (gdaiCaseDescription == null) {
			if (other.gdaiCaseDescription != null)
				return false;
		} else if (!gdaiCaseDescription.equals(other.gdaiCaseDescription))
			return false;
		if (gdaiCaseId == null) {
			if (other.gdaiCaseId != null)
				return false;
		} else if (!gdaiCaseId.equals(other.gdaiCaseId))
			return false;
		if (gdaiCaseResolution == null) {
			if (other.gdaiCaseResolution != null)
				return false;
		} else if (!gdaiCaseResolution.equals(other.gdaiCaseResolution))
			return false;
		if (systemDetails == null) {
			if (other.systemDetails != null)
				return false;
		} else if (!systemDetails.equals(other.systemDetails))
			return false;
		return true;
	}

	
}
