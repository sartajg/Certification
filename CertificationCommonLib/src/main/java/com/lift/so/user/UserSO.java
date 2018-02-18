package com.lift.so.user;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.lift.so.enums.UserType;

@XmlRootElement(name = "userSO")
public class UserSO {

	private String firstName;
	private String lastName;
	private Integer userId;
	private String password;
	private String emailId;
	private String mobileNo;
	private Set<AddressSO> locations;
	private Date createdDate;
	private Date modifiedDate;
	private boolean userLocked;
	private UserType userType;
	
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Set<AddressSO> getLocations() {
		return locations;
	}

	public void setLocations(Set<AddressSO> locations) {
		this.locations = locations;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isUserLocked() {
		return userLocked;
	}

	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserSO other = (UserSO) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserSO [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + ", locations="
				+ locations + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", userLocked="
				+ userLocked + "]";
	}
}
