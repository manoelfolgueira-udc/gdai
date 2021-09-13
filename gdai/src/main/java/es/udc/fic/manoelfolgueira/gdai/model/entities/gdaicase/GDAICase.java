package es.udc.fic.manoelfolgueira.gdai.model.entities.gdaicase;

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

import es.udc.fic.manoelfolgueira.gdai.model.entities.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GDAICaseDetails;

@Entity
@Table(name = "gdai_gdai_case")
public class GDAICase {

	@Column(name = "gdaiCaseId")
	@SequenceGenerator(name = "gdaiCaseIdGenerator", sequenceName = "gdaiCaseSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gdaiCaseIdGenerator")
	private Long gdaiCaseId;
	private String gdaiCaseDescription;
	private String gdaiCaseResolution;
	private Calendar creationDate = Calendar.getInstance();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdById")
	private User createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systemId")
	private System system;

	/**
	 * Empty constructor
	 */
	public GDAICase() {
	}

	/**
	 * Main constructor
	 * 
	 * @param gdaiCaseDescription
	 *            a description for this gdaiCase
	 * @param gdaiCaseResolution
	 *            a resolution for this gdaiCase
	 * @param creationDate
	 *            when it's created
	 * @param createdBy
	 *            user that has registered this gdaiCase
	 * @param system
	 *            system related to this gdaiCase
	 */
	public GDAICase(String gdaiCaseDescription, String gdaiCaseResolution, Calendar creationDate, User createdBy,
			System system) {

		this.gdaiCaseDescription = gdaiCaseDescription;
		this.gdaiCaseResolution = gdaiCaseResolution;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.system = system;
	}
	
	

	/**
	 * @param gDAICaseDetails
	 */
	public GDAICase(GDAICaseDetails gDAICaseDetails) {
		if (gDAICaseDetails != null) {
			this.gdaiCaseId = gDAICaseDetails.getGDAICaseId();
			this.gdaiCaseDescription = gDAICaseDetails.getGDAICaseDescription();
			this.gdaiCaseResolution = gDAICaseDetails.getGDAICaseResolution();
			this.creationDate = gDAICaseDetails.getCreationDate();
			this.createdBy = new User(gDAICaseDetails.getCreatedBy());
			this.system = new System(gDAICaseDetails.getSystemDetails());
		}
	}

	/**
	 * @return the gdaiCaseId
	 */
	public Long getGDAICaseId() {
		return gdaiCaseId;
	}

	/**
	 * @param gdaiCaseId
	 *            the gdaiCaseId to set
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
	 * @param gdaiCaseDescription
	 *            the gdaiCaseDescription to set
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
	 * @param gdaiCaseResolution
	 *            the gdaiCaseResolution to set
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
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
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
		result = prime * result + ((gdaiCaseId == null) ? 0 : gdaiCaseId.hashCode());
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
		GDAICase other = (GDAICase) obj;
		if (gdaiCaseId == null) {
			if (other.gdaiCaseId != null)
				return false;
		} else if (!gdaiCaseId.equals(other.gdaiCaseId))
			return false;
		return true;
	}

}
