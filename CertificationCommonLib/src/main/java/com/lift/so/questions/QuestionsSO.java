package com.lift.so.questions;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.QuestionStatus;
import com.lift.so.enums.QuestionsType;

@XmlRootElement(name="questions")
public class QuestionsSO {

	private Integer questionId;
	private String title;
	private String question;
	private QuestionsType questionsType;
	private ExamTypes examTypes;
	private Byte maxMarks;
	private Set<AnswersSo> possibleAns;
	private Date createdDate;
	private Date modifiedDate;
	private QuestionStatus questionStatus;
	
	public QuestionsType getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(QuestionsType questionsType) {
		this.questionsType = questionsType;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public ExamTypes getExamTypes() {
		return examTypes;
	}
	public void setExamTypes(ExamTypes examTypes) {
		this.examTypes = examTypes;
	}
	public Byte getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(Byte maxMarks) {
		this.maxMarks = maxMarks;
	}
	public Set<AnswersSo> getPossibleAns() {
		return possibleAns;
	}
	public void setPossibleAns(Set<AnswersSo> possibleAns) {
		this.possibleAns = possibleAns;
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
	public QuestionStatus getQuestionStatus() {
		return questionStatus;
	}
	public void setQuestionStatus(QuestionStatus questionStatus) {
		this.questionStatus = questionStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
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
		QuestionsSO other = (QuestionsSO) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		return true;
	}

}
