package es.udc.fic.manoelfolgueira.gdai.model.productionpassservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;

public class ProductionPassDetails {

	private String productionPassName;
	private String productionPassResolution;
	private Calendar creationDate;
	private String passPath;
	private User createdBy;
	private System system;

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
	public ProductionPassDetails(String productionPassName, String productionPassResolution, Calendar creationDate,
			String passPath, User createdBy, System system) {
		super();
		this.productionPassName = productionPassName;
		this.productionPassResolution = productionPassResolution;
		this.creationDate = creationDate;
		this.passPath = passPath;
		this.createdBy = createdBy;
		this.system = system;
	}

	/**
	 * @return the productionPassName
	 */
	public String getProductionPassName() {
		return productionPassName;
	}

	/**
	 * @param productionPassName
	 *            the productionPassName to set
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
	 * @param productionPassResolution
	 *            the productionPassResolution to set
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
	 * @param creationDate
	 *            the creationDate to set
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
	 * @param passPath
	 *            the passPath to set
	 */
	public void setPassPath(String passPath) {
		this.passPath = passPath;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the system
	 */
	public System getSystem() {
		return system;
	}

	/**
	 * @param system
	 *            the system to set
	 */
	public void setSystem(System system) {
		this.system = system;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productionPassName == null) ? 0 : productionPassName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (productionPassName == null) {
			if (other.productionPassName != null)
				return false;
		} else if (!productionPassName.equals(other.productionPassName))
			return false;
		return true;
	}

}
