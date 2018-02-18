package com.lift.so.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="AddressType")
@XmlEnum
public enum AddressType {

	CURRENT, PERMANENT, OTHER;
	
	public String value() {
		return name();
	}

	public static AddressType fromValue(String v) {
		return valueOf(v);
	}
}
