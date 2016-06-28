package com.hx.api.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class KuaidiModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private String message;
	@Expose
	private String status;
	/**
	 * 快递单当前签收状态，包括:0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单
	 */
	@Expose
	private String state;
	@Expose
	private String com;
	@Expose
	private String nu;
	@Expose
	private List<KuaidiResultModel> data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<KuaidiResultModel> getData() {
		return data;
	}
	public void setData(List<KuaidiResultModel> data) {
		this.data = data;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getNu() {
		return nu;
	}
	public void setNu(String nu) {
		this.nu = nu;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
