package es.udc.fic.manoelfolgueira.gdai.model.util.dtos;

import java.util.Calendar;

import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAIDetailsCodificable;

public class UserDetails extends GDAIDetailsCodificable {

	private Long userId;
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
	private Boolean isManager;
	private GroupDetails groupDetails;
	/**
	 * @param userId
	 * @param loginName
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param email
	 * @param phoneNumber
	 * @param avatarUrl
	 * @param hiredate
	 * @param dateOfBirth
	 * @param expirationDate
	 * @param isManager
	 * @param groupDetails
	 */
	public UserDetails(Long userId, String loginName, String firstName, String lastName, String gender, String email,
			String phoneNumber, String avatarUrl, Calendar hiredate, Calendar dateOfBirth, Calendar expirationDate,
			Boolean isManager, GroupDetails groupDetails) {
		super();
		this.userId = userId;
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
		this.isManager = isManager;
		this.groupDetails = groupDetails;
	}
	
	public UserDetails(User user) {
		this.userId = user.getUserId();
		this.loginName = user.getLoginName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.gender = user.getGender();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		this.avatarUrl = user.getAvatarUrl();
		this.hiredate = user.getHireDate();
		this.dateOfBirth = user.getDateOfBirth();
		this.expirationDate = user.getExpirationDate();
		this.isManager = user.getIsManager();
		this.groupDetails = new GroupDetails(user.getGroup());
		
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
	 * @return the groupDetails
	 */
	public GroupDetails getGroup() {
		return groupDetails;
	}

	/**
	 * @param groupDetails the groupDetails to set
	 */
	public void setGroup(GroupDetails groupDetails) {
		this.groupDetails = groupDetails;
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
	 * @return the hiredate
	 */
	public Calendar getHireDate() {
		return hiredate;
	}

	/**
	 * @param hiredate
	 *            the hiredate to set
	 */
	public void setHireDate(Calendar hiredate) {
		this.hiredate = hiredate;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((groupDetails == null) ? 0 : groupDetails.hashCode());
		result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
		result = prime * result + ((isManager == null) ? 0 : isManager.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		UserDetails other = (UserDetails) obj;
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
		if (groupDetails == null) {
			if (other.groupDetails != null)
				return false;
		} else if (!groupDetails.equals(other.groupDetails))
			return false;
		if (hiredate == null) {
			if (other.hiredate != null)
				return false;
		} else if (!hiredate.equals(other.hiredate))
			return false;
		if (isManager == null) {
			if (other.isManager != null)
				return false;
		} else if (!isManager.equals(other.isManager))
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
