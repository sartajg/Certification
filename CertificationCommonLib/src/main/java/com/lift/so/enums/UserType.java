package com.lift.so.enums;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum UserType {
	
	ADMIN, CUSTOMER;
	
	public String value() {
		return name();
	}

	public static UserType fromValue(String v) {
		return valueOf(v);
	}
}
