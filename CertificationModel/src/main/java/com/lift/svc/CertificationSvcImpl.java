package com.lift.svc;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lift.model.CertificateReg;
import com.lift.model.User;
import com.lift.so.enums.ExamStatus;
import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.MedalType;
import com.lift.so.response.CertificateResSO;
import com.lift.util.HibernateUtil;
import com.lift.util.ResHelper;

public class CertificationSvcImpl implements CertificationSvc, HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public ExamTypes getNextCertificate(Integer userId) {

		ExamTypes examTypes = getLastAttemptExam(userId);

		if (examTypes == null) {
			examTypes = ExamTypes.BEGINNER;
		}
		else if (ExamTypes.values().length > examTypes.ordinal() + 1
				&& ExamTypes.values()[examTypes.ordinal() + 1] != null) {
			examTypes = ExamTypes.values()[examTypes.ordinal() + 1];
		}

		return examTypes;
	}

	@Override
	@Transactional
	public ExamTypes getLastAttemptExam(Integer userId) {

		Session session = getSession();

		ExamTypes examTypes = (ExamTypes) session.createCriteria(CertificateReg.class)
				.setProjection(Projections.property("examTypes")).add(Restrictions.eq("user1.userId", userId))
				.add(Restrictions.isNotNull("medalType")).add(Restrictions.isNotNull("timeConsumed"))
				.addOrder(Order.desc("audit.modifiedDate")).setFetchSize(1).uniqueResult();

		if (examTypes != null)
			return examTypes;

		return null;
	}

	@Override
	@Transactional
	public List<String> getAllAttempteExam(Integer userId) {

		Session session = getSession();

		@SuppressWarnings("unchecked")
		List<CertificateReg> certificateRegs = session.createCriteria(CertificateReg.class)
				.setProjection(Projections.property("examTypes")).add(Restrictions.eq("user1.userId", userId)).list();

		List<String> attemptedExams = null;

		if (certificateRegs != null) {
			attemptedExams = new ArrayList<>();

			for (CertificateReg certificateReg : certificateRegs) {
				attemptedExams.add(certificateReg.getExamTypes().value());
			}
		}

		return attemptedExams;
	}

	@Override
	public Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	@Transactional
	public CertificateResSO getUserCertificate(Integer userId, ExamTypes examTypes) {
		Session session = getSession();

		CertificateReg certificateReg = (CertificateReg) session.createCriteria(CertificateReg.class)
				.add(Restrictions.eq("user1.userId", userId)).add(Restrictions.eq("examTypes", examTypes));
		CertificateResSO certificateResSO = certificateReg.getSoFromModel();
		return certificateResSO;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public CertificateResSO[] getUserCertificates(Integer userId) {

		Session session = getSession();

		List<CertificateReg> certificateRegs = session.createCriteria(CertificateReg.class)
				.add(Restrictions.eq("user1.userId", userId)).list();

		if (certificateRegs != null)
			return certificateRegs.toArray(new CertificateResSO[certificateRegs.size()]);

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public CertificateResSO[] getUserCertificate(MedalType medalType, ExamTypes examTypes) {

		Session session = getSession();

		List<CertificateReg> certificateRegs = session.createCriteria(CertificateReg.class)
				.add(Restrictions.eq("examTypes", examTypes)).add(Restrictions.eq("medalType", medalType)).list();

		if (certificateRegs != null)
			return certificateRegs.toArray(new CertificateResSO[certificateRegs.size()]);

		return null;
	}

	@Override
	@Transactional
	public Boolean userHasRegisteredForCertificate(Integer userId) {
		Session session = getSession();
		CertificateReg certificateReg = (CertificateReg) session.createCriteria(CertificateReg.class)
				.add(Restrictions.eq("user1.userId", userId)).add(Restrictions.eq("certStatus", ExamStatus.P)).uniqueResult();

		if(certificateReg != null) {
			return true;
		}
		
		return false;
	}

	@Override
	@Transactional
	public void registerForCertificate(Integer userId) {
		ExamTypes examTypes = getNextCertificate(userId);
		Session session = getSession();
		
		CertificateReg certificateReg = new CertificateReg();
		certificateReg.setExamTypes(examTypes);
		certificateReg.setMaxTimeLimit(Integer.parseInt(ResHelper.get("MAX_TIME_DURATION", "45")));
		certificateReg.setCertStatus(ExamStatus.P);
		certificateReg.getAudit().setCreatedDate(null);
		certificateReg.getAudit().setModifiedDate(null);
		
		
		User user = new User();
		user.setUserId(userId);
		
		certificateReg.setUser1(user);
		
		session.save(certificateReg);
	}
}
