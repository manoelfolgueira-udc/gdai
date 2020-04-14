package es.udc.fic.manoelfolgueira.gdai.model.productionpassservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPass;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class ProductionPassDetails extends GDAIDetailsCodificable {

	private Long productionPassId;
	private String productionPassName;
	private String productionPassResolution;
	private Calendar creationDate;
	private String passPath;
	private UserDetails createdBy;
	private SystemDetails system;

	/**
	 * Main constructor
	 * 
	 * @param productionPassName
	 *            a name for this productionPass
	 * @param productionPassResolution
	 *            a resolution for this productionPass
	 * @param creationDate
	 *            when it's created
	 * @param passPath
	 *            where the pass doc will be stored
	 * @param createdBy
	 *            user that has registered this productionPass
	 * @param system
	 *            system related to this productionPass
	 */
	public ProductionPassDetails(Long productionPassId, String productionPassName, String productionPassResolution, Calendar creationDate,
			String passPath, UserDetails createdBy, SystemDetails system) {
		super();
		this.productionPassId = productionPassId;
		this.productionPassName = productionPassName;
		this.productionPassResolution = productionPassResolution;
		this.creationDate = creationDate;
		this.passPath = passPath;
		this.createdBy = createdBy;
		this.system = system;
	}
	
	public ProductionPassDetails(ProductionPass productionPass) {
		super();
		this.productionPassId = productionPass.getProductionPassId();
		this.productionPassName = productionPass.getProductionPassName();
		this.productionPassResolution = productionPass.getProductionPassResolution();
		this.creationDate = productionPass.getCreationDate();
		this.passPath = productionPass.getPassPath();
		this.createdBy = new UserDetails(productionPass.getCreatedBy());
		this.system = new SystemDetails(productionPass.getSystem());
	}

	/**
	 * @return the productionPassId
	 */
	public Long getProductionPassId() {
		return productionPassId;
	}

	/**
	 * @param productionPassId the productionPassId to set
	 */
	public void setProductionPassId(Long productionPassId) {
		this.productionPassId = productionPassId;
	}

	/**
	 * @return the productionPassName
	 */
	public String getProductionPassName() {
		return productionPassName;
	}

	/**
	 * @param productionPassName the productionPassName to set
	 */
	public void setProductionPassName(String productionPassName) {
		this.productionPassName = productionPassName;
	}

	/**
	 * @return the productionPassResolution
	 */
	public String getProductionPassResolution() {
		return productionPassResolution;
	}

	/**
	 * @param productionPassResolution the productionPassResolution to set
	 */
	public void setProductionPassResolution(String productionPassResolution) {
		this.productionPassResolution = productionPassResolution;
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
	 * @return the passPath
	 */
	public String getPassPath() {
		return passPath;
	}

	/**
	 * @param passPath the passPath to set
	 */
	public void setPassPath(String passPath) {
		this.passPath = passPath;
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
	 * @return the system
	 */
	public SystemDetails getSystem() {
		return system;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(SystemDetails system) {
		this.system = system;
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
		result = prime * result + ((passPath == null) ? 0 : passPath.hashCode());
		result = prime * result + ((productionPassName == null) ? 0 : productionPassName.hashCode());
		result = prime * result + ((productionPassResolution == null) ? 0 : productionPassResolution.hashCode());
		result = prime * result + ((system == null) ? 0 : system.hashCode());
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
		ProductionPassDetails other = (ProductionPassDetails) obj;
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
		if (passPath == null) {
			if (other.passPath != null)
				return false;
		} else if (!passPath.equals(other.passPath))
			return false;
		if (productionPassName == null) {
			if (other.productionPassName != null)
				return false;
		} else if (!productionPassName.equals(other.productionPassName))
			return false;
		if (productionPassResolution == null) {
			if (other.productionPassResolution != null)
				return false;
		} else if (!productionPassResolution.equals(other.productionPassResolution))
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		return true;
	}

	

}
