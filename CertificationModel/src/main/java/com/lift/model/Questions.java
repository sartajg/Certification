package com.lift.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.QuestionStatus;
import com.lift.so.enums.QuestionsType;
import com.lift.so.questions.AnswersSo;
import com.lift.so.questions.QuestionsSO;
import com.lift.util.ModelToSo;
import com.lift.util.SoToModel;

import static com.lift.util.CertificateConstrants.QUESTIONS_SEQ;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="QUESTIONS")
@DynamicUpdate
public class Questions implements Auditable, ModelToSo<QuestionsSO>, SoToModel<QuestionsSO, Questions> {
	
	@Id
	@SequenceGenerator(name=QUESTIONS_SEQ, sequenceName=QUESTIONS_SEQ)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=QUESTIONS_SEQ)
	@Column(name="Q_ID", insertable=true, updatable=false, unique=true)
	private Integer qId;
	
	@Lob
	@Column(name="Q_DETAILS")
	private String qDetails;
	
	@Column(name="Q_TITLE")
	private String qTitle;

	@Column(name="Q_LEVEL")
	@Enumerated(EnumType.STRING)
	private QuestionsType level;
	
	@Column(name="EXAM_TYPE")
	@Enumerated(EnumType.STRING)
	private ExamTypes certificationLevel;
	
	@Column(name="Q_MARKS")
	private Byte maxMarks;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Embedded
	private Audit audit = new Audit();
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="question", orphanRemoval=true)
	private Set<Answers> answers = new HashSet<>();

	public void addAnswer(Answers answer) {
		answers.add(answer);
		answer.setQuestion(this);
	}
	
	public Integer getqId() {
		return qId;
	}

	public void setqId(Integer qId) {
		this.qId = (qId == null ? this.qId : qId);
	}

	public String getqDetails() {
		return qDetails;
	}

	public void setqDetails(String qDetails) {
		this.qDetails = (qDetails == null ? this.qDetails : qDetails);
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = (qTitle == null ? this.qTitle : qTitle);
	}

	public QuestionsType getLevel() {
		return level;
	}

	public void setLevel(QuestionsType level) {
		this.level = (level == null ? this.level : level);
	}

	public ExamTypes getCertificationLevel() {
		return certificationLevel;
	}

	public void setCertificationLevel(ExamTypes certificationLevel) {
		this.certificationLevel = (certificationLevel == null ? this.certificationLevel : certificationLevel);
	}

	public Byte getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Byte maxMarks) {
		this.maxMarks = (maxMarks == null? this.maxMarks : maxMarks);
	}

	public Set<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answers> answers) {
		this.answers = (answers == null ? this.answers : answers);
	}

	public QuestionStatus getStatus() {
		return status;
	}

	public void setStatus(QuestionStatus status) {
		this.status = (status == null ? this.status : status);
	}

	@Override
	public Questions getModelFromSo(QuestionsSO questionsSO) {
		
		if(questionsSO.getPossibleAns() != null)
		for (AnswersSo answersSo : questionsSO.getPossibleAns()) {
			Answers answers = new Answers();
			if(answersSo.getAnswerDetails() != null)
				answers.setDescriptions(answersSo.getAnswerDetails());
			if(answersSo.getAnswerStatus() != null)
				answers.setCorrectAnswer(answersSo.getAnswerStatus());
			
			answers.getAudit().setCreatedDate(null);
			answers.getAudit().setModifiedDate(null);
			
			if(questionsSO.getQuestionId() == null)
				addAnswer(answers);
		}
		
		if(questionsSO.getQuestion() != null)
			setqDetails(questionsSO.getQuestion());
		if(questionsSO.getTitle() != null)
			setqTitle(questionsSO.getTitle());
		if(questionsSO.getQuestionsType() != null)
			setLevel(questionsSO.getQuestionsType());
		if(questionsSO.getMaxMarks() != null)
			setMaxMarks(questionsSO.getMaxMarks());
		if(questionsSO.getExamTypes() != null)
			setCertificationLevel(questionsSO.getExamTypes());
		if(questionsSO.getQuestionStatus() != null)
			setStatus(questionsSO.getQuestionStatus());
		getAudit().setCreatedDate(null);
		getAudit().setModifiedDate(null);
		
		return this;
	}

	@Override
	public QuestionsSO getSoFromModel() {
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setQuestionId(qId);
		questionsSO.setQuestion(qDetails);
		questionsSO.setTitle(qTitle);
		questionsSO.setMaxMarks(maxMarks);
		questionsSO.setQuestionsType(level);
		questionsSO.setQuestionStatus(status);
		questionsSO.setCreatedDate(getAudit().getCreatedDate());
		questionsSO.setModifiedDate(getAudit().getModifiedDate());
		
		if(answers != null) {
			Set<AnswersSo> answersSos = new HashSet<>();
			for (Answers answer : answers) {
				answersSos.add(answer.getSoFromModel());
			}
			questionsSO.setPossibleAns(answersSos);
		}
		
		return questionsSO;
	}

	@Override
	public void setAudit(Audit audit) {
		this.audit = (audit == null ? this.audit : audit);
	}

	@Override
	public Audit getAudit() {
		return audit;
	}
}
