package com.lift.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.lift.so.enums.UserType;
import com.lift.so.user.AddressSO;
import com.lift.so.user.UserSO;
import com.lift.util.ModelToSo;
import com.lift.util.SoToModel;

import static com.lift.util.CertificateConstrants.USERS_SEQ;

@Entity
@Table(name = "USERS")
@DynamicUpdate
public class User implements Auditable, ModelToSo<UserSO>, SoToModel<UserSO, User> {

	@Id
	@SequenceGenerator(name = USERS_SEQ, sequenceName = USERS_SEQ)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USERS_SEQ)
	@Column(name = "USER_ID", insertable = true, updatable = false, nullable = false)
	private Integer userId;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = true)
	private String lastName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "USER_LOCKED", length = 1, columnDefinition = "NUMBER(1)")
	private boolean userLocked;

	@Column(name="USER_TYPE", insertable=true, updatable=false, nullable=false)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@OneToMany(mappedBy="user", orphanRemoval=true, cascade=CascadeType.ALL)
	/*@JoinTable(name = "USERS_ADDRESS", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "LOCATION_ID", referencedColumnName = "LOCATION_ID") })*/
	private Set<Address> locations = new HashSet<>();

	@OneToMany(mappedBy="user1", orphanRemoval=true, cascade=CascadeType.ALL)
	private Set<CertificateReg> certificates = new HashSet<>();
	
	@Embedded
	private Audit audit = new Audit();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public boolean isUserLocked() {
		return userLocked;
	}

	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
	}

	public Set<Address> getLocations() {
		return locations;
	}

	public void setLocations(Set<Address> locations) {
		this.locations = locations;
	}

	@Override
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Set<CertificateReg> getCertificates() {
		return certificates;
	}

	public void setCertificates(Set<CertificateReg> certificates) {
		this.certificates = certificates;
	}

	@Override
	public UserSO getSoFromModel() {

		UserSO userSO = new UserSO();

		userSO.setFirstName(firstName);
		userSO.setLastName(lastName);
		userSO.setUserId(userId);
		userSO.setCreatedDate(getAudit().getCreatedDate());
		userSO.setModifiedDate(getAudit().getModifiedDate());

		if(locations != null) {
			Set<AddressSO> addressSOs = new HashSet<>();
			for (Address address : locations) {
				addressSOs.add(address.getSoFromModel());
			}
			userSO.setLocations(addressSOs);
		}
		return userSO;
	}

	@Override
	public User getModelFromSo(UserSO SO) {

		if(SO.getLocations() != null) {
	
			for (AddressSO addressSO : SO.getLocations()) {
				Address adrs = new Address();
				adrs.getModelFromSo(addressSO);
				
				if(adrs.getLocationId() == null) {
					locations.add(adrs);
					adrs.setUser(this);
				}
			}
		}
		
		if(SO.getFirstName() != null)
			setFirstName(SO.getFirstName());
		if(SO.getLastName() != null)
			setLastName(SO.getLastName());
		if(SO.getPassword() != null)
			setPassword(SO.getPassword());
		if(SO.getUserType() != null)
			setUserType(SO.getUserType());
		if(SO.getEmailId() != null)
			setEmailId(SO.getEmailId());
		if(SO.getMobileNo() != null)
			setMobileNo(SO.getMobileNo());
		getAudit().setCreatedDate(null);
		getAudit().setModifiedDate(null);
		
		return this;
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", userLocked=" + userLocked + ", locations=" + locations + ", audit=" + audit + "]";
	}

}
