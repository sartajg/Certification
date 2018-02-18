package com.lift.so.enums;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum QuestionStatus {
	
	ACTIVE, INACTIVE;
	
	public String value() {
		return name();
	}

	public static QuestionStatus fromValue(String v) {
		return valueOf(v);
	}
}
