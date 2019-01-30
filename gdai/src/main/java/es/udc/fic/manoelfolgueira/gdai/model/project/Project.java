package es.udc.fic.manoelfolgueira.gdai.model.project;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
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
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "project_sprint_jt", 
        joinColumns = { @JoinColumn(name = "projectId") }, 
        inverseJoinColumns = { @JoinColumn(name = "sprintId") }
    )
    List<Sprint> sprints;
	

	public Project() {
	}
		
	public Project(String projectName, String projectDescription, Calendar creationDate,
			Calendar targetDate, User createdBy, System system, List<Sprint> sprints) {

		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.targetDate = targetDate;
		this.createdBy = createdBy;
		this.system = system;
		this.sprints = sprints;
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

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(LinkedList<Sprint> sprints) {
		this.sprints = sprints;
	}
	
	public void addSprint(Sprint sprint) {
		this.sprints.add(sprint);
	}
	
	public void removeSprint(Sprint sprint) {
		this.sprints.remove(sprint);
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
