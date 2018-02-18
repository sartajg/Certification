package com.lift.svc.admin;

import java.util.LinkedHashSet;
import java.util.Set;

import com.lift.so.questions.QuestionsSO;

public interface QuestionsSvc {

	/**
	 * 
	 * @param questionsSO
	 */
	void addQuestions(QuestionsSO questionsSO);
	
	/**
	 * 
	 * @param userId
	 */
	public LinkedHashSet<Integer> prepareQuestion(Integer userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	QuestionsSO getNextQuestion(Integer userId, Integer questionId);
	
	/**
	 * 
	 * @param userId
	 * @param questionId
	 * @param selectedAnsSet
	 */
	void saveUsersSelectedAnswers(Integer userId, Integer questionId, Set<Integer> selectedAnsSet);
	
	/**
	 * 
	 * @param questionId
	 * @param questionsSO
	 */
	void updateQuestion(Integer questionId, QuestionsSO questionsSO);
	
	/**
	 * 
	 * @param userId
	 */
	void submitTest(Integer userId);
}
