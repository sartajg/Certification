package com.lift.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.lift.so.enums.CorrectAnswer;
import com.lift.so.questions.AnswersSo;
import com.lift.util.ModelToSo;
import com.lift.util.SoToModel;

import static com.lift.util.CertificateConstrants.ANSWERS_SEQ;

@Entity
@Table(name="ANSWERS")
@DynamicUpdate
public class Answers implements Auditable, ModelToSo<AnswersSo>, SoToModel<AnswersSo, Answers> {

	@Id
	@SequenceGenerator(name=ANSWERS_SEQ, sequenceName=ANSWERS_SEQ)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=ANSWERS_SEQ)
	@Column(name="A_ID")
	private Integer aId;
	
	@Column(name="A_DESCRIPTIONS")
	private String descriptions;
	
	@Column(name="CORRECT_ANS")
	@Enumerated(EnumType.STRING)
	private CorrectAnswer correctAnswer;
	
	@Embedded
	private Audit audit = new Audit();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Q_ID")
	private Questions question;
	
	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(CorrectAnswer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	@Override
	public Answers getModelFromSo(AnswersSo SO) {
		
		setDescriptions(SO.getAnswerDetails());
		setCorrectAnswer(SO.getAnswerStatus());
		getAudit().setCreatedDate(null);
		getAudit().setModifiedDate(null);
		
		return this;
	}

	@Override
	public AnswersSo getSoFromModel() {
		AnswersSo answersSo = new AnswersSo();
		
		answersSo.setAnswerDetails(descriptions);
		answersSo.setAnswerStatus(correctAnswer);
		answersSo.setCreatedDate(getAudit().getCreatedDate());
		answersSo.setModifiedDate(getAudit().getModifiedDate());
		
		return answersSo;
	}

	@Override
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	@Override
	public Audit getAudit() {
		return this.audit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aId == null) ? 0 : aId.hashCode());
		result = prime * result + ((descriptions == null) ? 0 : descriptions.hashCode());
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
		Answers other = (Answers) obj;
		if (aId == null) {
			if (other.aId != null)
				return false;
		} else if (!aId.equals(other.aId))
			return false;
		if (descriptions == null) {
			if (other.descriptions != null)
				return false;
		} else if (!descriptions.equals(other.descriptions))
			return false;
		return true;
	}

	
}
