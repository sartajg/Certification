package com.lift.so.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="examType")
@XmlEnum
public enum ExamTypes {

	BEGINNER, INTERMEDIATE, EXPERT;
	
	public String value() {
		return name();
	}

	public static ExamTypes fromValue(String v) {
		return valueOf(v);
	}
}
