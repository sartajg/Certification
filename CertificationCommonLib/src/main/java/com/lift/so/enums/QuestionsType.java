package com.lift.so.enums;

public enum QuestionsType {

	EASY, MEDIUM, HARD;
	
	public String value() {
		return name();
	}

	public static QuestionsType fromValue(String v) {
		return valueOf(v);
	}
}
