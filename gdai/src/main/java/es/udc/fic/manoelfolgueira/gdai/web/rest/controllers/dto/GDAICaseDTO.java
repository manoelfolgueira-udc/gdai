package es.udc.fic.manoelfolgueira.gdai.web.rest.controllers.dto;

public class GDAICaseDTO {
	private String gdaiCaseDescription;
	private String createdBy;
	private String systemId;
	
	public GDAICaseDTO() {}
	
	public GDAICaseDTO(String gdaiCaseDescription, String createdBy, String systemId) {
		super();
		this.gdaiCaseDescription = gdaiCaseDescription;
		this.createdBy = createdBy;
		this.systemId = systemId;
	}

	public String getGdaiCaseDescription() {
		return gdaiCaseDescription;
	}

	public void setGdaiCaseDescription(String gdaiCaseDescription) {
		this.gdaiCaseDescription = gdaiCaseDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((gdaiCaseDescription == null) ? 0 : gdaiCaseDescription.hashCode());
		result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GDAICaseDTO other = (GDAICaseDTO) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (gdaiCaseDescription == null) {
			if (other.gdaiCaseDescription != null)
				return false;
		} else if (!gdaiCaseDescription.equals(other.gdaiCaseDescription))
			return false;
		if (systemId == null) {
			if (other.systemId != null)
				return false;
		} else if (!systemId.equals(other.systemId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GDAICaseDTO [gdaiCaseDescription=" + gdaiCaseDescription + ", createdBy=" + createdBy + ", systemId="
				+ systemId + "]";
	}
	
	
	
	
}
