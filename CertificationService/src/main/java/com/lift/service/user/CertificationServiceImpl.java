package com.lift.service.user;

import javax.ejb.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.lift.so.enums.ExamTypes;
import com.lift.so.filter.CertificateFilterSO;
import com.lift.so.response.CertificateResSO;
import com.lift.svc.CertificationSvc;

@Singleton
public class CertificationServiceImpl extends SpringBeanAutowiringSupport implements CertificationService {

	@Autowired
	private CertificationSvc certificationSvc;

	@Override
	public CertificateResSO getUserCertificate(Integer userId, ExamTypes examTypes) {
		CertificateResSO certificateFilterSO = certificationSvc.getUserCertificate(userId, examTypes);
		return certificateFilterSO;
	}

	@Override
	public CertificateResSO[] getUserCertificates(Integer userId) {
		CertificateResSO certificateResSO[] = certificationSvc.getUserCertificates(userId);
		return certificateResSO;
	}
	
	@Override
	public Integer[] getCertificate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateResSO[] getCertificate(CertificateFilterSO certificateFilterSO) {
		CertificateResSO certificateResSO[] = certificationSvc.getUserCertificate(certificateFilterSO.getMedalType(), certificateFilterSO.getExamTypes());
		return certificateResSO;
	}

	@Override
	public CertificateResSO[] getCertificate(Integer userId, CertificateFilterSO certificateFilterSO) {
		return null;
	}

	@Override
	public CertificateResSO getCertificate(Integer userId, Integer certificateId) {
		return null;
	}

	@Override
	public CertificateResSO getCertificate(Integer certificateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerForCertificate(Integer userId) {
		
		if(!certificationSvc.userHasRegisteredForCertificate(userId)) {
			certificationSvc.registerForCertificate(userId);
		} else {
			//throw exception
		}
	}
}
