package com.yunyangit.eye.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SysUser extends BaseEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2571172027332433860L;
	private String username;
	private String password;
	private Integer status;
	private Date pwdexpireTime;
	private Integer type;
	private Integer isenabled;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date allowAt;
	private Integer errorNum;
	private String realName;
	private String organization;
	private String job;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getPwdexpireTime() {
		return pwdexpireTime;
	}
	public void setPwdexpireTime(Date pwdexpireTime) {
		this.pwdexpireTime = pwdexpireTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(Integer isenabled) {
		this.isenabled = isenabled;
	}
	public Date getAllowAt() {
		return allowAt;
	}
	public void setAllowAt(Date allowAt) {
		this.allowAt = allowAt;
	}
	public Integer getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}



	public interface Status {
		int DISABLED = 0;
		int VALID = 1;
		int LOCKED = 2;
	}
}
