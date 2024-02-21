package com.yunyangit.eye.model;

public class Mail  extends BaseEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9221408198746545445L;
	
	private String beforeRealname;
	private String afterRealname;
	private String beforeUsername;
	private String afterUsername;
	private String beforeOrganization;
	private String afterOrganization;
	private String beforeJob;
	private String afterJob;
	public String getBeforeRealname() {
		return beforeRealname;
	}
	public void setBeforeRealname(String beforeRealname) {
		this.beforeRealname = beforeRealname;
	}
	public String getAfterRealname() {
		return afterRealname;
	}
	public void setAfterRealname(String afterRealname) {
		this.afterRealname = afterRealname;
	}
	public String getBeforeUsername() {
		return beforeUsername;
	}
	public void setBeforeUsername(String beforeUsername) {
		this.beforeUsername = beforeUsername;
	}
	public String getAfterUsername() {
		return afterUsername;
	}
	public void setAfterUsername(String afterUsername) {
		this.afterUsername = afterUsername;
	}
	public String getBeforeOrganization() {
		return beforeOrganization;
	}
	public void setBeforeOrganization(String beforeOrganization) {
		this.beforeOrganization = beforeOrganization;
	}
	public String getAfterOrganization() {
		return afterOrganization;
	}
	public void setAfterOrganization(String afterOrganization) {
		this.afterOrganization = afterOrganization;
	}
	public String getBeforeJob() {
		return beforeJob;
	}
	public void setBeforeJob(String beforeJob) {
		this.beforeJob = beforeJob;
	}
	public String getAfterJob() {
		return afterJob;
	}
	public void setAfterJob(String afterJob) {
		this.afterJob = afterJob;
	}
	
}
