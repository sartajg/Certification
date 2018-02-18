package com.lift.service.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lift.so.enums.ExamTypes;
import com.lift.so.filter.CertificateFilterSO;
import com.lift.so.response.CertificateResSO;


@Path(value = "/certificates")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface CertificationService {

	/**
	 * Get all the certificate Ids till now attempted
	 * @return
	 */
	@GET
	@Path("/")
	Integer[] getCertificate();
	
	/**
	 * Get all certificate details based 
	 * @param certificateFilter
	 * @return
	 */
	@GET
	@Path("/filter")
	CertificateResSO[] getCertificate(CertificateFilterSO certificateFilterSO);
	
	/**
	 * 
	 * @param userId
	 * @param certificateFilterSO
	 * @return
	 */
	@GET
	@Path("/{userId}")
	CertificateResSO[] getCertificate(@PathParam("userId") Integer userId, CertificateFilterSO certificateFilterSO);
	
	/**
	 * 
	 * @param userId
	 * @param certificateId
	 * @return
	 */
	@GET
	@Path("/user/{userId}/certificate/{certificateId}")
	CertificateResSO getCertificate(@PathParam("userId") Integer userId, @PathParam("certificateId") Integer certificateId);
	
	/**
	 * 
	 * @param certificateId
	 * @return
	 */
	@GET
	@Path("/certificate/{certificateId}")
	CertificateResSO getCertificate(@PathParam("certificateId") Integer certificateId);
	
	@GET
	@Path("/user/{userId}")
	CertificateResSO getUserCertificate(@PathParam("userId") Integer userId, ExamTypes examTypes);
	
	@GET
	@Path("/user/{userId}")
	CertificateResSO[] getUserCertificates(@PathParam("userId") Integer userId);
	
	@POST
	@Path("/user/{userId}")
	void registerForCertificate(@PathParam("userId") Integer userId);
}
