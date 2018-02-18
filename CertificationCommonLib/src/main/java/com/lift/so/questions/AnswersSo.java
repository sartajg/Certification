package com.lift.so.questions;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.lift.so.enums.CorrectAnswer;

@XmlRootElement(name="answers")
public class AnswersSo {

	private String answerDetails;
	private CorrectAnswer answerStatus;
	private Date createdDate;
	private Date modifiedDate;
	
	public String getAnswerDetails() {
		return answerDetails;
	}

	public void setAnswerDetails(String answerDetails) {
		this.answerDetails = answerDetails;
	}

	public CorrectAnswer getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(CorrectAnswer answerStatus) {
		this.answerStatus = answerStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerDetails == null) ? 0 : answerDetails.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswersSo other = (AnswersSo) obj;
		if (answerDetails == null) {
			if (other.answerDetails != null)
				return false;
		} else if (!answerDetails.equals(other.answerDetails))
			return false;
		return true;
	}
	
	
}
