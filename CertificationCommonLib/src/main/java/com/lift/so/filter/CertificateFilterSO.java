package com.lift.so.filter;

import javax.xml.bind.annotation.XmlRootElement;

import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.MedalType;

@XmlRootElement(name="certificateFilter")
public class CertificateFilterSO {
	
	private ExamTypes examTypes;
	private MedalType medalType;
	
	public ExamTypes getExamTypes() {
		return examTypes;
	}
	public void setExamTypes(ExamTypes examTypes) {
		this.examTypes = examTypes;
	}
	public MedalType getMedalType() {
		return medalType;
	}
	public void setMedalType(MedalType medalType) {
		this.medalType = medalType;
	}
}
