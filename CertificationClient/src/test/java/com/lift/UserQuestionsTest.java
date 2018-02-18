package com.lift;

import java.util.LinkedHashSet;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lift.service.admin.questions.QuestionService;

public class UserQuestionsTest {

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
	public void testPrepareQuestion() {
		Integer userId = 1951;
		LinkedHashSet<Integer> preparedQuestions = service.prepareQuestions(userId);
		Assert.assertNotNull(preparedQuestions);
	}
	
	@Test
	public void testGetQuestions() {
		Integer userId = 1951;
		LinkedHashSet<Integer> questionIds = service.prepareQuestions(userId);
		System.out.println(questionIds);
		Assert.assertNotNull(questionIds);
	}
}
