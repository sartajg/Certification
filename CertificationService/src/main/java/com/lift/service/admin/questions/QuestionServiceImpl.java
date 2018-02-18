package com.lift.service.admin.questions;

import java.util.LinkedHashSet;

import javax.ejb.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.lift.so.filter.FilterSO;
import com.lift.so.questions.AnswersSo;
import com.lift.so.questions.QuestionsSO;
import com.lift.svc.CertificationSvc;
import com.lift.svc.admin.QuestionsSvc;

@Singleton
public class QuestionServiceImpl extends SpringBeanAutowiringSupport implements QuestionService{

	@Autowired
	private QuestionsSvc questionSvc;
	@Autowired
	private CertificationSvc certificationSvc;
	
	@Override
	public void createQuestions(String userName, QuestionsSO questionsSO) {
		questionSvc.addQuestions(questionsSO);
		System.out.println("Question added successfully");
	}

	@Override
	public LinkedHashSet<Integer> prepareQuestions(Integer userId) {
		
		LinkedHashSet<Integer> createdQuestionIds = null;
		
		if(certificationSvc.userHasRegisteredForCertificate(userId)) {
			createdQuestionIds = questionSvc.prepareQuestion(userId);
			System.out.println("Question preparation success");
		}
		return createdQuestionIds;
	}

	@Override
	public QuestionsSO getQuestion(Integer userId, Integer questionId) {
		QuestionsSO questionsSO = questionSvc.getNextQuestion(userId, questionId);
		return questionsSO;
	}

	@Override
	public QuestionsSO[] getQuestionsInRangeForUser(Integer userId, FilterSO filterSO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionsSO[] getQuestions(FilterSO filterSO) {
		return null;
	}

	@Override
	public AnswersSo[] getAnswers(Integer questionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnswersSo getAnswers(Integer questionId, Integer answerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateQuestion(Integer questionId, QuestionsSO questionsSO) {
		questionSvc.updateQuestion(questionId, questionsSO);
	}

	@Override
	public void updateAnswer(Integer questionId, Integer answerId, AnswersSo answersSo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAnswer(Integer questionId, AnswersSo answersSo) {
		// TODO Auto-generated method stub
		
	}

}
