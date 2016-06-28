package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class KuaidiParmModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Expose
	private String com;
	@Expose
	private String num;
	@Expose
	private String from;
	@Expose
	private String to;
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	
	
	
}
