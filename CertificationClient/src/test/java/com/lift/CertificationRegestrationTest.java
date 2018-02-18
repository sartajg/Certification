package com.lift;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lift.service.user.CertificationService;

public class CertificationRegestrationTest {
	ResteasyWebTarget target = null;
	ResteasyClient client = null;
	CertificationService service = null; 
			
	@Before
	public void init() {
		client = new ResteasyClientBuilder().build();
		client.abortIfClosed();
		target = client.target("http://localhost:8080/certification/rest/");
		service = target.proxy(CertificationService.class);
	}
	
	@After
	public void destroy() {
		client.close();
	}
	
	@Test
	public void testRegisterForCertificate() {
		Integer userId = 1951;
		service.registerForCertificate(userId);
	}
}
