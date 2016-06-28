package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class BusinessParmModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private String phone;
	@Expose
	private String token;
	@Expose
	private String uploadFlag;
	@Expose
	private Integer pageNo;
	@Expose
	private Integer pageSize;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
