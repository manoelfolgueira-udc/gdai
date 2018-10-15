package es.udc.fic.manoelfolgueira.gdai.model.project;

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

@Entity
@Table(name="gdai_project")
public class Project {

	@Column(name = "projectId")
	@SequenceGenerator( // It only takes effect for
		name = "projectIdGenerator", // databases providing identifier
		sequenceName = "projectSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "projectIdGenerator")
	private Long projectId;
	private String projectName;
	private String projectDescription;
	private Calendar creationDate = Calendar.getInstance();
	private Calendar targetDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdById")
	private User createdBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemId")
	private System system;
	

	public Project() {
	}
	
	public Project(String projectName, String projectDescription, Calendar targetDate,
			User createdBy, System system) {
		super();
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.targetDate = targetDate;
		this.createdBy = createdBy;
		this.system = system;
	}

	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectDescription() {
		return projectDescription;
	}


	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}


	public Calendar getCreationDate() {
		return creationDate;
	}
	
	
	public Calendar getTargetDate() {
		return targetDate;
	}

	
	public void setTargetDate(Calendar targetDate) {
		this.targetDate = targetDate;
	}


	public User getCreatedBy() {
		return createdBy;
	}

	
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
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
		Project other = (Project) obj;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}

}
