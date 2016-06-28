package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class OrderParmModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 列表参数
	 */
	@Expose
	private String phone;
	@Expose
	private String token;
	@Expose
	private Integer orderId;
	@Expose
	private String fieldSearch;
	@Expose
	private Integer status;
	@Expose
	private Integer pageNo;
	@Expose
	private Integer pageSize;
	
	/**
	 * 发货
	 */
	@Expose
	private Integer wuliuCompanyId;
	@Expose
	private String wuliuCode;
	
	
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getFieldSearch() {
		return fieldSearch;
	}
	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getWuliuCompanyId() {
		return wuliuCompanyId;
	}
	public void setWuliuCompanyId(Integer wuliuCompanyId) {
		this.wuliuCompanyId = wuliuCompanyId;
	}
	public String getWuliuCode() {
		return wuliuCode;
	}
	public void setWuliuCode(String wuliuCode) {
		this.wuliuCode = wuliuCode;
	}
	
	
	
}
