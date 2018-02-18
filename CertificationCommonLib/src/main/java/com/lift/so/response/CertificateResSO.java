package com.lift.so.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.MedalType;
import com.lift.so.user.UserSO;

@XmlRootElement(name="CertificateResSO")
@XmlSeeAlso({MedalType.class})
public class CertificateResSO {

	private Float marks;
	private MedalType medalType;
	private ExamTypes examTypes;
	private Date examCompletionDate;
	private Integer totleRecords;

	public Float getMarks() {
		return marks;
	}

	public void setMarks(Float marks) {
		this.marks = marks;
	}

	public MedalType getMedalType() {
		return medalType;
	}

	public void setMedalType(MedalType medalType) {
		this.medalType = medalType;
	}

	public ExamTypes getExamTypes() {
		return examTypes;
	}

	public void setExamTypes(ExamTypes examTypes) {
		this.examTypes = examTypes;
	}

	public Date getExamCompletionDate() {
		return examCompletionDate;
	}

	public void setExamCompletionDate(Date examCompletionDate) {
		this.examCompletionDate = examCompletionDate;
	}

	public Integer getTotleRecords() {
		return totleRecords;
	}

	public void setTotleRecords(Integer totleRecords) {
		this.totleRecords = totleRecords;
	}

}
