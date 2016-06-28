package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class KuaidiResultModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private String time;
	@Expose
	private String context;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
}
