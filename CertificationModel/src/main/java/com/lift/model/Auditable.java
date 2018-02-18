package com.lift.model;

public interface Auditable {

	void setAudit(Audit audit);
	
	Audit getAudit();
}
