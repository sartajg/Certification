package com.lift.svc;

import java.util.List;

import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.MedalType;
import com.lift.so.response.CertificateResSO;

public interface CertificationSvc {

	ExamTypes getNextCertificate(Integer userId);
	
	ExamTypes getLastAttemptExam(Integer userId);
	
	List<String> getAllAttempteExam(Integer userId);
	
	CertificateResSO getUserCertificate(Integer userId, ExamTypes examTypes);
	
	CertificateResSO[] getUserCertificate(MedalType medalType, ExamTypes examTypes);
	
	CertificateResSO[] getUserCertificates(Integer userId);
	
	Boolean userHasRegisteredForCertificate(Integer userId);
	
	void registerForCertificate(Integer userId);
}
