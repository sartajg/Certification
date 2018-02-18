package com.lift.so.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="MedalType")
@XmlEnum
public enum MedalType {

	SILVER, BRONZE, GOLD;
	
	public String value() {
		return name();
	}

	public static MedalType fromValue(String v) {
		return valueOf(v);
	}
}
