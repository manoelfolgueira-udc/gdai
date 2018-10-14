package es.udc.fic.manoelfolgueira.gdai.model.group;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;

@Entity
@Table(name="gdai_group")
public class Group {

	@Column(name = "groupId")
	@SequenceGenerator( // It only takes effect for
		name = "groupIdGenerator", // databases providing identifier
		sequenceName = "groupSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "groupIdGenerator")
	private Long groupId;
	private String groupName;
	private Calendar creationTime = Calendar.getInstance();
	private Calendar expirationTime = null;
	
	@OneToMany(targetEntity=User.class, mappedBy="group", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<User> users;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemId")
	private System system;

	public Group() {
	}

	public Group(String groupName, List<User> users, Calendar expirationTime, System system) {

		/**
		 * NOTE: "groupId" *must* be left as "null" since its value is
		 * automatically generated.
		 */

		this.groupName = groupName;
		this.users = users;
		this.expirationTime = expirationTime;
		this.system = system;
	}
	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
