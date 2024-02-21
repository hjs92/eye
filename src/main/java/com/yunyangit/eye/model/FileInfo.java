package com.yunyangit.eye.model;

import java.util.Date;

public class FileInfo extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2438940824337030932L;
	private Date dateTime;
	private Long state;
	private String downloadLink1;
	private String downloadLink2;
	private SysUser user;
	private Long type;
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public String getDownloadLink1() {
		return downloadLink1;
	}
	public void setDownloadLink1(String downloadLink1) {
		this.downloadLink1 = downloadLink1;
	}
	public String getDownloadLink2() {
		return downloadLink2;
	}
	public void setDownloadLink2(String downloadLink2) {
		this.downloadLink2 = downloadLink2;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	
}
