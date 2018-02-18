package com.lift;

import java.util.HashSet;
import java.util.Set;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lift.service.admin.questions.QuestionService;
import com.lift.so.enums.CorrectAnswer;
import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.QuestionStatus;
import com.lift.so.enums.QuestionsType;
import com.lift.so.questions.AnswersSo;
import com.lift.so.questions.QuestionsSO;

public class QuestionsTest {
	ResteasyWebTarget target = null;
	ResteasyClient client = null;
	QuestionService service = null; 
			
	@Before
	public void init() {
		client = new ResteasyClientBuilder().build();
		client.abortIfClosed();
		target = client.target("http://localhost:8080/certification/rest/");
		service = target.proxy(QuestionService.class);
	}
	
	@After
	public void destroy() {
		client.close();
	}
	
	@Test
	public void testCreateBeginnerEasyQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.BEGINNER);
		questionsSO.setMaxMarks(Byte.valueOf("2").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.EASY);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateBeginnerMediumQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.BEGINNER);
		questionsSO.setMaxMarks(Byte.valueOf("2").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.MEDIUM);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateBeginnerHardQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.BEGINNER);
		questionsSO.setMaxMarks(Byte.valueOf("2").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.HARD);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateIntermediateEasyQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.INTERMEDIATE);
		questionsSO.setMaxMarks(Byte.valueOf("2").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.EASY);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateIntermediateMediumQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.INTERMEDIATE);
		questionsSO.setMaxMarks(Byte.valueOf("2").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.MEDIUM);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		AnswersSo answersSo2 = new AnswersSo();
		answersSo1.setAnswerDetails("it happens automatic");
		answersSo1.setAnswerStatus(CorrectAnswer.Y);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		possibleAns.add(answersSo2);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateIntermediateHardQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.INTERMEDIATE);
		questionsSO.setMaxMarks(Byte.valueOf("2").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.HARD);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateExpertEasyQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.EXPERT);
		questionsSO.setMaxMarks(Byte.valueOf("2").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.EASY);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateExpertMediumQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.EXPERT);
		questionsSO.setMaxMarks(Byte.valueOf("3").byteValue());
		questionsSO.setQuestion("When java 10 will release");
		questionsSO.setQuestionsType(QuestionsType.MEDIUM);
		questionsSO.setTitle("Java 10");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("2021");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("2018");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
	
	@Test
	public void testCreateExpertHardQuestions() {
		
		QuestionService service = target.proxy(QuestionService.class);
		
		QuestionsSO questionsSO = new QuestionsSO();
		questionsSO.setExamTypes(ExamTypes.EXPERT);
		questionsSO.setMaxMarks(Byte.valueOf("5").byteValue());
		questionsSO.setQuestion("How to start weblogic server");
		questionsSO.setQuestionsType(QuestionsType.HARD);
		questionsSO.setTitle("Weblogic server start");
		questionsSO.setQuestionStatus(QuestionStatus.ACTIVE);
		
		AnswersSo answersSo = new AnswersSo();
		answersSo.setAnswerDetails("Goto setting ");
		answersSo.setAnswerStatus(CorrectAnswer.Y);
		
		AnswersSo answersSo1 = new AnswersSo();
		answersSo1.setAnswerDetails("Goto setting then delete");
		answersSo1.setAnswerStatus(CorrectAnswer.N);
		
		Set<AnswersSo> possibleAns = new HashSet<>();
		possibleAns.add(answersSo);
		possibleAns.add(answersSo1);
		
		questionsSO.setPossibleAns(possibleAns);
		
		service.createQuestions("test", questionsSO);
	}
}
