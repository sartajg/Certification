package com.lift.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.lift.so.enums.AddressType;
import com.lift.so.user.AddressSO;
import com.lift.util.ModelToSo;
import com.lift.util.SoToModel;

//@Entity
//@DiscriminatorValue(value="PERMANENT")
public class PermanentAddress  {

	/*@Override
	public Address getModelFromSo(AddressSO addressSO) {

		this.setLocationId(addressSO.getLocationId());
		this.setPinCode(addressSO.getPinCode());
		this.setStreet(addressSO.getStreet());
		
		return this;
	}

	@Override
	public AddressSO getSoFromModel() {
		AddressSO addressSO = new AddressSO();
		
		addressSO.setAddressType(AddressType.PERMANENT);
		addressSO.setLocationId(getLocationId());
		addressSO.setPinCode(getPinCode());
		addressSO.setStreet(getStreet());
		addressSO.setCreatedDate(getAudit().getCreatedDate());
		addressSO.setLastmodifiedDate(getAudit().getModifiedDate());
		
		return addressSO;
	}*/
}
