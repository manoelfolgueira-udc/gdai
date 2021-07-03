package es.udc.fic.manoelfolgueira.gdai.model.entities.user;

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

import es.udc.fic.manoelfolgueira.gdai.model.entities.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.entities.project.Project;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;

@Entity
@Table(name = "gdai_user")
public class User {

	@Column(name = "userId")
	@SequenceGenerator(name = "UserIdGenerator", sequenceName = "UserSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserIdGenerator")
	private Long userId;
	private String loginName;
	private String encryptedPassword;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String avatarUrl;
	private Calendar hireDate;
	private Calendar dateOfBirth;
	private Calendar expirationDate;
	private String gender;
	private Boolean isManager;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId")
	private Group group;

	@OneToMany(targetEntity = Project.class, mappedBy = "createdBy", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Project> projects;


	/**
	 * Empty constructor
	 */
	public User() {
	}

	/**
	 * Main constructor
	 * 
	 * @param loginName
	 *            a login name that does not exist already
	 * @param encryptedPassword
	 *            the password encrypted with
	 *            es.udc.fic.manoelfolgueira.gdai.model.userservice.util.PasswordEncrypter.java
	 * @param firstName
	 *            first real name
	 * @param lastName
	 *            last real name
	 * @param gender
	 *            sex
	 * @param email
	 *            electronic mail
	 * @param phoneNumber
	 *            a phone
	 * @param avatarUrl
	 *            the user current avatar
	 * @param hireDate
	 *            when the user was hired
	 * @param dateOfBirth
	 *            when the user was born
	 * @param expirationDate
	 *            when the user account will expire
	 * @param group
	 *            the group which the user belongs to
	 */
	public User(String loginName, String encryptedPassword, String firstName, String lastName, String gender,
			String email, String phoneNumber, String avatarUrl, Calendar hireDate, Calendar dateOfBirth,
			Calendar expirationDate, Boolean isManager, Group group) {

		this.loginName = loginName;
		this.encryptedPassword = encryptedPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.avatarUrl = avatarUrl;
		this.hireDate = hireDate;
		this.dateOfBirth = dateOfBirth;
		this.expirationDate = expirationDate;
		this.group = group;
		this.isManager = isManager;

	}

	/**
	 * @param userDetails
	 */
	public User(UserDetails userDetails) {

		this.userId = userDetails.getUserId();
		this.loginName = userDetails.getLoginName();
		//this.encryptedPassword = userDetails.getEncryptedPassword(); // this field is not exported in the dto
		this.firstName = userDetails.getFirstName();
		this.lastName = userDetails.getLastName();
		this.gender = userDetails.getGender();
		this.email = userDetails.getEmail();
		this.phoneNumber = userDetails.getPhoneNumber();
		this.avatarUrl = userDetails.getAvatarUrl();
		this.hireDate = userDetails.getHireDate();
		this.dateOfBirth = userDetails.getDateOfBirth();
		this.expirationDate = userDetails.getExpirationDate();
		this.group = new Group(userDetails.getGroup());
		this.isManager = userDetails.getIsManager();

	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the encryptedPassword
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * @param encryptedPassword
	 *            the encryptedPassword to set
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * @param avatarUrl
	 *            the avatarUrl to set
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	/**
	 * @return the hireDate
	 */
	public Calendar getHireDate() {
		return hireDate;
	}

	/**
	 * @param hireDate
	 *            the hireDate to set
	 */
	public void setHireDate(Calendar hireDate) {
		this.hireDate = hireDate;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the expirationDate
	 */
	public Calendar getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
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

	/**
	 * @return the isManager
	 */
	public Boolean getIsManager() {
		return isManager;
	}

	/**
	 * @param isManager
	 *            the isManager to set
	 */
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
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
		result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((encryptedPassword == null) ? 0 : encryptedPassword.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (avatarUrl == null) {
			if (other.avatarUrl != null)
				return false;
		} else if (!avatarUrl.equals(other.avatarUrl))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (encryptedPassword == null) {
			if (other.encryptedPassword != null)
				return false;
		} else if (!encryptedPassword.equals(other.encryptedPassword))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
