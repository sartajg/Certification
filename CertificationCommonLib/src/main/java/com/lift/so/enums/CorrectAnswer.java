package com.lift.so.enums;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum CorrectAnswer {

	Y, N;
	
	public String value() {
		return name();
	}

	public static CorrectAnswer fromValue(String v) {
		return valueOf(v);
	}	
}
