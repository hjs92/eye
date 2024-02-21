package com.yunyangit.eye.model;

public class UserEvaluate extends BaseEntity<Long> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9154065960943764119L;
	private String struuid;
	private String content;
	private Long level;
	public String getStruuid() {
		return struuid;
	}
	public void setStruuid(String struuid) {
		this.struuid = struuid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	
	
}
