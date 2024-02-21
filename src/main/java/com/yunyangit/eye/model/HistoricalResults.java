package com.yunyangit.eye.model;

import java.util.Date;

public class HistoricalResults extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1110492864770970552L;
	private String pid;
	private String files;
	private Date dateTime;
	private Long userId;
	private Long type;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	
	
	
	
	
}
