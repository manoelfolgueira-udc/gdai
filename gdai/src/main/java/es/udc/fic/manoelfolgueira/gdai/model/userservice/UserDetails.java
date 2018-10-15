package es.udc.fic.manoelfolgueira.gdai.model.userservice;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;

public class UserDetails {

	private String loginName;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phoneNumber;
	private String avatarUrl;
	private Calendar hiredate;	
	private Calendar dateOfBirth;
	private Calendar expirationDate;
	private Group group;
	
	/**
	 * @param loginName
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param avatarUrl
	 * @param hiredate
	 * @param dateOfBirth
	 * @param expirationDate
	 */
	public UserDetails(String loginName, String firstName, String lastName, String gender, String email, String phoneNumber,
			String avatarUrl, Calendar hiredate, Calendar dateOfBirth, Calendar expirationDate, Group group) {
		super();
		this.loginName = loginName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.avatarUrl = avatarUrl;
		this.hiredate = hiredate;
		this.dateOfBirth = dateOfBirth;
		this.expirationDate = expirationDate;
		this.group = group;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Calendar getHiredate() {
		return hiredate;
	}
	public void setHiredate(Calendar hiredate) {
		this.hiredate = hiredate;
	}
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Calendar getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
