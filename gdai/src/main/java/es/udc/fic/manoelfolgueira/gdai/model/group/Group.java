package es.udc.fic.manoelfolgueira.gdai.model.group;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="gdai_group")
public class Group {

	private Long groupId;
	private String name;
	private Calendar creationTime = Calendar.getInstance();
	private Calendar expirationTime = null;

	public Group() {
	}

	public Group(String name) {

		/**
		 * NOTE: "groupId" *must* be left as "null" since its value is
		 * automatically generated.
		 */

		this.name = name;
		
	}

	@Column(name = "id")
	@SequenceGenerator( // It only takes effect for
		name = "groupIdGenerator", // databases providing identifier
		sequenceName = "groupSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "groupIdGenerator")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}

	public Calendar getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Calendar expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	

}
