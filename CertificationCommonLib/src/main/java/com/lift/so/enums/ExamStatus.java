package com.lift.so.enums;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum ExamStatus {

	P, N, C;
	
	public String value() {
		return name();
	}

	public static ExamStatus fromValue(String v) {
		return valueOf(v);
	}
}
