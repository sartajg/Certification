package com.lift.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.lift.so.enums.ExamStatus;
import com.lift.so.enums.ExamTypes;
import com.lift.so.enums.MedalType;
import com.lift.so.response.CertificateResSO;
import com.lift.util.ModelToSo;

import static com.lift.util.CertificateConstrants.CERT_REG_SEQ;

@Entity
@Table(name="CERTIFICATE_REG")
@DynamicUpdate
public class CertificateReg implements Auditable, ModelToSo<CertificateResSO> {

    @Id
    @SequenceGenerator(name=CERT_REG_SEQ, sequenceName=CERT_REG_SEQ)
	@GeneratedValue(generator=CERT_REG_SEQ, strategy=GenerationType.SEQUENCE)
	@Column(name="CERT_ID")
    private Integer certId;
	
	@Column(name="CERT_LEVEL")
	@Enumerated(EnumType.STRING)
	private ExamTypes examTypes;
	
	@Column(name="CERT_STATUS")
	@Enumerated(EnumType.STRING)
	private ExamStatus certStatus;
	
	@Column(name="MEDAL_WON")
	@Enumerated(EnumType.STRING)
	private MedalType medalType;
	
	@Column(name="MAX_DURATION_IN_MILLIS", nullable=false)
	private Integer maxTimeLimit;
	
	@Column(name="TIME_TAKEN")
	private Integer timeConsumed;
	
	@Column(name="MARKS_OBTAINED")
	private Float marksObtained;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private User user1;
	
	@Embedded
	private Audit audit = new Audit();
	
	public Integer getCertId() {
		return certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public ExamTypes getExamTypes() {
		return examTypes;
	}

	public void setExamTypes(ExamTypes examTypes) {
		this.examTypes = examTypes;
	}

	public ExamStatus getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(ExamStatus certStatus) {
		this.certStatus = certStatus;
	}

	public MedalType getMedalType() {
		return medalType;
	}

	public void setMedalType(MedalType medalType) {
		this.medalType = medalType;
	}

	public Integer getMaxTimeLimit() {
		return maxTimeLimit;
	}

	public void setMaxTimeLimit(Integer maxTimeLimit) {
		this.maxTimeLimit = maxTimeLimit;
	}

	public Integer getTimeConsumed() {
		return timeConsumed;
	}

	public void setTimeConsumed(Integer timeConsumed) {
		this.timeConsumed = timeConsumed;
	}

	public Float getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(Float marksObtained) {
		this.marksObtained = marksObtained;
	}

	@Override
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((certId == null) ? 0 : certId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CertificateReg other = (CertificateReg) obj;
		if (certId == null) {
			if (other.certId != null)
				return false;
		} else if (!certId.equals(other.certId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CertificateReg [certId=" + certId + ", examTypes=" + examTypes + ", certStatus="
				+ certStatus + ", medalType=" + medalType + ", maxTimeLimit=" + maxTimeLimit + ", timeConsumed="
				+ timeConsumed + ", audit=" + audit + "]";
	}

	@Override
	public CertificateResSO getSoFromModel() {
		
		CertificateResSO certificateResSO = new CertificateResSO();
		certificateResSO.setExamTypes(examTypes);
		certificateResSO.setMedalType(medalType);
		certificateResSO.setExamCompletionDate(getAudit().getModifiedDate());
		certificateResSO.setMarks(marksObtained);
		
		return certificateResSO;
	}
}
