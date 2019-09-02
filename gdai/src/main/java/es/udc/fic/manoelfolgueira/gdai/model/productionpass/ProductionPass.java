package es.udc.fic.manoelfolgueira.gdai.model.productionpass;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAICodificable;

@Entity
@Table(name="gdai_production_pass")
public class ProductionPass extends GDAICodificable {
	
	@Column(name = "productionPassId")
	@SequenceGenerator(name = "productionPassIdGenerator", sequenceName = "productionPassSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productionPassIdGenerator")
	private Long productionPassId;
	private String productionPassName;
	private String productionPassResolution;
	private Calendar creationDate = Calendar.getInstance();
	private String passPath;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdById")
	private User createdBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemId")
	private System system;
	/**
	 * Empty constructor
	 */
	public ProductionPass() {}
	
	/**
	 * Main constructor
	 * @param productionPassName a name for this productionPass
	 * @param productionPassResolution a description for this productionPass
	 * @param creationDate when it's created
	 * @param targetDate when it should be finished
	 * @param createdBy user that has registered this productionPass
	 * @param system system related to this productionPass
	 * @param sprints a list of sprints when this productionPass will take place
	 * @param userStory the us which this productionPass belongs to
	 */
	public ProductionPass(String productionPassName, String productionPassResolution, Calendar creationDate,
			String passPath, User createdBy, System system) {

		this.productionPassName = productionPassName;
		this.productionPassResolution = productionPassResolution;
		this.creationDate = creationDate;
		this.passPath = passPath;
		this.createdBy = createdBy;
		this.system = system;
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
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
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
	 * @param system the system to set
	 */
	public void setSystem(System system) {
		this.system = system;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productionPassId == null) ? 0 : productionPassId.hashCode());
		result = prime * result + ((productionPassName == null) ? 0 : productionPassName.hashCode());
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
		ProductionPass other = (ProductionPass) obj;
		if (productionPassId == null) {
			if (other.productionPassId != null)
				return false;
		} else if (!productionPassId.equals(other.productionPassId))
			return false;
		if (productionPassName == null) {
			if (other.productionPassName != null)
				return false;
		} else if (!productionPassName.equals(other.productionPassName))
			return false;
		return true;
	}

	
	
}
