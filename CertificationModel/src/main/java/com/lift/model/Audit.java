package com.lift.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Embeddable
public class Audit {

	//@Generated(GenerationTime.INSERT)
	@Column(name="CREATED_DATE", nullable=false, updatable=false, columnDefinition="TIMESTAMP(9) WITH TIME ZONE")
	private Timestamp createdDate;
	
	//@Generated(GenerationTime.ALWAYS)
	@Column(name="MODIFIED_DATE", nullable=false, updatable=true, columnDefinition="TIMESTAMP(9) WITH TIME ZONE")
	private Timestamp modifiedDate;
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = (createdDate == null ? new Timestamp(new java.util.Date().getTime()) : createdDate);
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = (modifiedDate == null ? new Timestamp(new java.util.Date().getTime()) : modifiedDate);
	}
	@Override
	public String toString() {
		return "Audit [createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
}
