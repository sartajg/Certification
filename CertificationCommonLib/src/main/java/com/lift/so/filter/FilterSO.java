package com.lift.so.filter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.lift.so.enums.ExamTypes;

@XmlRootElement(name="filterSO")
@XmlSeeAlso({ExamTypes.class})
public class FilterSO {

	private DateSO dateSO;
	private ExamTypes examTypes;
	private Integer startIndex;
	private Integer endIndex;

	public DateSO getDateSO() {
		return dateSO;
	}

	public void setDateSO(DateSO dateSO) {
		this.dateSO = dateSO;
	}

	public ExamTypes getExamTypes() {
		return examTypes;
	}

	public void setExamTypes(ExamTypes examTypes) {
		this.examTypes = examTypes;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	
}
