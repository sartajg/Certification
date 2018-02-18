package com.lift.service.admin.questions;

import java.util.LinkedHashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lift.so.filter.FilterSO;
import com.lift.so.questions.AnswersSo;
import com.lift.so.questions.QuestionsSO;

@Path(value="/admin/questions")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface QuestionService {

	@POST
	@Path(value="/{username}")
	void createQuestions(@PathParam("username") String userName, QuestionsSO questionsSO);
	
	@POST
	@Path(value="/prepare/{userId}")
	LinkedHashSet<Integer> prepareQuestions(@PathParam("userId") Integer userId);
	
	@GET
	@Path(value="/{userId}/{questionId}")
	QuestionsSO getQuestion(@PathParam("userId") Integer userId, @PathParam("questionId") Integer questionId);
	
	@GET
	@Path(value="/{userId}")
	QuestionsSO[] getQuestionsInRangeForUser(@PathParam("userId") Integer userId, FilterSO filterSO);
	
	@GET
	@Path(value="/{startIndex}/{endIndex}")
	QuestionsSO[] getQuestions(FilterSO filterSO);
	
	@GET
	@Path(value="/{questionId}/answer")
	AnswersSo[] getAnswers(@PathParam("questionId") Integer questionId);
	
	@GET
	@Path(value="/{questionId}/answer/{answerId}")
	AnswersSo getAnswers(@PathParam("questionId") Integer questionId, @PathParam("answerId") Integer answerId);
	
	@GET
	@Path("/{questionId}")
	void updateQuestion(@PathParam("questionId") Integer questionId, QuestionsSO questionsSO);
	
	@PUT
	@Path("/{questionId}/answer/{answerId}")
	void updateAnswer(@PathParam("questionId") Integer questionId, Integer answerId,AnswersSo answersSo);
	
	@PUT
	@Path("/{questionId}/answer")
	void addAnswer(@PathParam("questionId") Integer questionId, AnswersSo answersSo);
}
