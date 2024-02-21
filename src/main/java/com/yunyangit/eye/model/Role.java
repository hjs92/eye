package com.yunyangit.eye.model;

public class Role extends BaseEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7272584584184154136L;
	private String name;
	private String description;
	private Integer status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
