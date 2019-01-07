package es.udc.fic.manoelfolgueira.gdai.model.userstory;

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
@Table(name="gdai_userStory")
public class UserStory {

	@Column(name = "userStoryId")
	@SequenceGenerator( // It only takes effect for
		name = "userStoryIdGenerator", // databases providing identifier
		sequenceName = "userStorySeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "userStoryIdGenerator")
	private Long userStoryId;
	private String userStoryName;
	private Calendar startDate = null;
	private Calendar endDate = null;
	private Calendar creationDate = Calendar.getInstance();
	
	@ManyToMany(mappedBy = "userStorys")
    private List<Project> projects;

	public UserStory() {
	}

	public UserStory(String userStoryName, Calendar startDate, Calendar endDate, Calendar creationDate,
			List<Project> projects) {
		super();
		this.userStoryName = userStoryName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.projects = projects;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public String getUserStoryName() {
		return userStoryName;
	}

	public void setUserStoryName(String userStoryName) {
		this.userStoryName = userStoryName;
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
