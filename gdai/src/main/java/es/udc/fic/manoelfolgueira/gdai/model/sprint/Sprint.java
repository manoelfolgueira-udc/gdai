package es.udc.fic.manoelfolgueira.gdai.model.sprint;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.Utils;

@Entity
@Table(name = "gdai_sprint")
public class Sprint {

	@Column(name = "sprintId")
	@SequenceGenerator(name = "sprintIdGenerator", sequenceName = "sprintSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sprintIdGenerator")
	private Long sprintId;
	private String sprintName;
	private Calendar sprintStart = null;
	private Calendar sprintEnd = null;
	private Calendar creationDate = Calendar.getInstance();

	@ManyToMany(mappedBy = "sprints")
	private List<Project> projects;

	/**
	 * ^ Empty constructor
	 */
	public Sprint() {
	}

	/**
	 * Main constructor
	 * 
	 * @param sprintName
	 *            sprint name
	 * @param sprintStart
	 *            when the sprint starts
	 * @param sprintEnd
	 *            when the sprint finishes
	 * @param creationDate
	 *            when the sprint is registered in GDAI
	 * @param projects
	 *            a list of projects being taken into account in this sprint
	 */
	public Sprint(String sprintName, Calendar sprintStart, Calendar sprintEnd, Calendar creationDate,
			List<Project> projects) {
		super();
		this.sprintName = sprintName;
		this.sprintStart = sprintStart;
		this.sprintEnd = sprintEnd;
		this.creationDate = creationDate;
		this.projects = projects;
	}

	/**
	 * @param s
	 */
	public Sprint(SprintDetails sprintDetails) {
		this.sprintId = sprintDetails.getSprintId();
		this.sprintName = sprintDetails.getSprintName();
		this.sprintStart = sprintDetails.getStartDate();
		this.sprintEnd = sprintDetails.getEndDate();
		this.creationDate = sprintDetails.getCreationDate();
		this.projects = new LinkedList<>();
		sprintDetails.getProjectsDetails().forEach(p -> {
			projects.add(new Project(p));
		});
	}

	/**
	 * @return the sprintId
	 */
	public Long getSprintId() {
		return sprintId;
	}

	/**
	 * @param sprintId
	 *            the sprintId to set
	 */
	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	/**
	 * @return the sprintName
	 */
	public String getSprintName() {
		return sprintName;
	}

	/**
	 * @param sprintName
	 *            the sprintName to set
	 */
	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	/**
	 * @return the sprintStart
	 */
	public Calendar getSprintStart() {
		return sprintStart;
	}

	/**
	 * @param sprintStart
	 *            the sprintStart to set
	 */
	public void setSprintStart(Calendar sprintStart) {
		this.sprintStart = sprintStart;
	}

	/**
	 * @return the sprintEnd
	 */
	public Calendar getSprintEnd() {
		return sprintEnd;
	}

	/**
	 * @param sprintEnd
	 *            the sprintEnd to set
	 */
	public void setSprintEnd(Calendar sprintEnd) {
		this.sprintEnd = sprintEnd;
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
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getBSprintName() {
		return "[" + this.sprintName + "] " + Utils.getFormattedDate(this.sprintStart.getTime()) + " - "
				+ Utils.getFormattedDate(this.sprintEnd.getTime());
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
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		result = prime * result + ((sprintEnd == null) ? 0 : sprintEnd.hashCode());
		result = prime * result + ((sprintId == null) ? 0 : sprintId.hashCode());
		result = prime * result + ((sprintName == null) ? 0 : sprintName.hashCode());
		result = prime * result + ((sprintStart == null) ? 0 : sprintStart.hashCode());
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
		Sprint other = (Sprint) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		if (sprintEnd == null) {
			if (other.sprintEnd != null)
				return false;
		} else if (!sprintEnd.equals(other.sprintEnd))
			return false;
		if (sprintId == null) {
			if (other.sprintId != null)
				return false;
		} else if (!sprintId.equals(other.sprintId))
			return false;
		if (sprintName == null) {
			if (other.sprintName != null)
				return false;
		} else if (!sprintName.equals(other.sprintName))
			return false;
		if (sprintStart == null) {
			if (other.sprintStart != null)
				return false;
		} else if (!sprintStart.equals(other.sprintStart))
			return false;
		return true;
	}

	@PreRemove
	private void removeProjectsFromSprint() {
		for (Project p : projects) {
			p.getSprints().remove(this);
		}
	}

}
