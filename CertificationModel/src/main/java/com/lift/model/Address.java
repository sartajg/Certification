package com.lift.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.lift.so.enums.AddressType;
import com.lift.so.user.AddressSO;
import com.lift.util.ModelToSo;
import com.lift.util.SoToModel;

import static com.lift.util.CertificateConstrants.ADDRESS_SEQ;

@Entity
@Table(name="ADDRESS")
@DynamicUpdate
public class Address implements Auditable, ModelToSo<AddressSO>, SoToModel<AddressSO, Address> {

	@Id
	@SequenceGenerator(name=ADDRESS_SEQ, sequenceName=ADDRESS_SEQ)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=ADDRESS_SEQ)
	@Column(name="LOCATION_ID", insertable=true, updatable=false, nullable=false)
	private Integer locationId;
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="PIN_CODE")
	private String pinCode;
	
	@Column(name="ADDRESS_TYPE")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
	@Embedded
	private Audit audit = new Audit();
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID", insertable=true, updatable=false)
	private User user;
	
	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Override
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	@Override
	public Audit getAudit() {
		return this.audit;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Address getModelFromSo(AddressSO addressSO) {
		
		if(addressSO.getLocationId() != null)
			this.setLocationId(addressSO.getLocationId());
		if(addressSO.getPinCode() != null)
			this.setPinCode(addressSO.getPinCode());
		if(addressSO.getStreet() != null)
			this.setStreet(addressSO.getStreet());
		if(addressSO.getAddressType() != null)
			this.setAddressType(addressSO.getAddressType());
		
		this.getAudit().setCreatedDate(null);
		this.getAudit().setModifiedDate(null);
		
		return this;
	}

	@Override
	public AddressSO getSoFromModel() {
		AddressSO addressSO = new AddressSO();
		
		addressSO.setLocationId(getLocationId());
		addressSO.setAddressType(getAddressType());
		addressSO.setPinCode(getPinCode());
		addressSO.setStreet(getStreet());
		addressSO.setCreatedDate(getAudit().getCreatedDate());
		addressSO.setLastmodifiedDate(getAudit().getModifiedDate());
		
		return addressSO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((pinCode == null) ? 0 : pinCode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (pinCode == null) {
			if (other.pinCode != null)
				return false;
		} else if (!pinCode.equals(other.pinCode))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [locationId=" + locationId + ", street=" + street + ", pinCode=" + pinCode + ", addressType="
				+ addressType + ", audit=" + audit + ", user=" + user + "]";
	}
}
