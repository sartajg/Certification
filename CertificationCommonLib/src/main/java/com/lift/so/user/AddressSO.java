package com.lift.so.user;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.lift.so.enums.AddressType;

@XmlRootElement(name="locations")
@XmlSeeAlso({AddressType.class})
public class AddressSO {

	private Integer locationId;
	private String street;
	private String pinCode;
	private AddressType addressType;
	private Date createdDate;
	private Date lastmodifiedDate;
	
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
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastmodifiedDate() {
		return lastmodifiedDate;
	}
	public void setLastmodifiedDate(Date lastmodifiedDate) {
		this.lastmodifiedDate = lastmodifiedDate;
	}
	
	@Override
	public String toString() {
		return "AddressSO [locationId=" + locationId + ", street=" + street + ", pinCode=" + pinCode + ", addressType="
				+ addressType + "]";
	}
	
	
}
