package com.yunyangit.eye.model;

public class Dict extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7703257105066099608L;
	private String type;
	private String k;
	private String val;
	private String lang;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
}
