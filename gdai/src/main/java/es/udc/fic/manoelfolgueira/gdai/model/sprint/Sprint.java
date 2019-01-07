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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.project.Project;

@Entity
@Table(name="gdai_sprint")
public class Sprint {

	@Column(name = "sprintId")
	@SequenceGenerator( // It only takes effect for
		name = "sprintIdGenerator", // databases providing identifier
		sequenceName = "sprintSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sprintIdGenerator")
	private Long sprintId;
	private String sprintName;
	private Calendar startDate = null;
	private Calendar endDate = null;
	private Calendar creationDate = Calendar.getInstance();
	
	@ManyToMany(mappedBy = "sprints")
    private List<Project> projects;

	public Sprint() {
	}

	public Sprint(String sprintName, Calendar startDate, Calendar endDate, Calendar creationDate,
			List<Project> projects) {
		super();
		this.sprintName = sprintName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.projects = projects;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(LinkedList<Project> projects) {
		this.projects = projects;
	}
	
	public void addProject(Project project) {
		this.projects.add(project);
	}
	
	public void removeProject(Project project) {
		this.projects.remove(project);
	}

	public Calendar getCreationDate() {
		return creationDate;
	}
	

}
