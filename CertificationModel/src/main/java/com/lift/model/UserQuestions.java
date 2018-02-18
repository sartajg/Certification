package com.lift.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name="USERS_QUESTIONS")
@DynamicUpdate
public class UserQuestions implements Auditable {

	@Embeddable
	public static class UserQuestionsPK implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name="USER_ID")
		private Integer userId;
		
		@Column(name="Q_ID")
		private Integer questionId;
		
		public UserQuestionsPK() {
		}
		
		public UserQuestionsPK(Integer userId, Integer questionId) {
			this.userId = userId;
			this.questionId = questionId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getQuestionId() {
			return questionId;
		}

		public void setQuestionId(Integer questionId) {
			this.questionId = questionId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
			UserQuestionsPK other = (UserQuestionsPK) obj;
			if (questionId == null) {
				if (other.questionId != null)
					return false;
			} else if (!questionId.equals(other.questionId))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "UserQuestionsPK [userId=" + userId + ", questionId=" + questionId + "]";
		}
	}
	
	@Transient
	@Column(name="USER_ID")
	private Integer userId;
	
	@Transient
	@Column(name="Q_ID")
	private Integer questionId;
	
	@Column(name="ATTEMPTED")
	@Type(type="yes_no")
	private Boolean attempted;
	
	@Column(name="A_IDS", updatable=true, insertable=true)
	private String selectedAnsIds;
	
	@Column(name="IS_CORRECT", insertable=true, updatable=true)
	@Type(type="yes_no")
	private Boolean correct;
	
	@Embedded
	private Audit audit = new Audit();
	
	@EmbeddedId
	private UserQuestionsPK id = new UserQuestionsPK();
	
	public UserQuestions() {
	}
	
	@Override
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	@Override
	public Audit getAudit() {
		return audit;
	}

	public Integer getUserId() {
		return this.getId().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
		this.getId().setUserId(userId);
	}

	public Integer getQuestionId() {
		return this.getId().getQuestionId();
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
		this.getId().setQuestionId(questionId);
	}

	public Boolean getAttempted() {
		return attempted;
	}

	public void setAttempted(Boolean attempted) {
		this.attempted = attempted;
	}

	public String getSelectedAnsIds() {
		return selectedAnsIds;
	}

	public void setSelectedAnsIds(String selectedAnsIds) {
		this.selectedAnsIds = selectedAnsIds;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	public UserQuestionsPK getId() {
		return id;
	}

	public void setId(UserQuestionsPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserQuestions other = (UserQuestions) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserQuestions [userId=" + userId + ", questionId=" + questionId + ", attempted=" + attempted
				+ ", selectedAnsIds=" + selectedAnsIds + ", correct=" + correct + ", audit=" + audit + "]";
	}
}
