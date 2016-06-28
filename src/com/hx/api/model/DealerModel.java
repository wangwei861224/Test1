package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class DealerModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private Integer id;
	@Expose
	private String dealerName;
	@Expose
	private String linkName;
	@Expose
	private String mobile;
	@Expose
	private String address;
	@Expose
	private String regTime;
	@Expose
	private String businesslicense;//营业执照
	@Expose
	private String organization;//组织代码证
	@Expose
	private String brandproxy;//品牌代理资格证
	@Expose
	private Integer device;
	@Expose
	private String areaId;
	@Expose
	private String balance;
	@Expose
	private String token;
	@Expose
	private String areaText;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getBusinesslicense() {
		return businesslicense;
	}
	public void setBusinesslicense(String businesslicense) {
		this.businesslicense = businesslicense;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getBrandproxy() {
		return brandproxy;
	}
	public void setBrandproxy(String brandproxy) {
		this.brandproxy = brandproxy;
	}
	public Integer getDevice() {
		return device;
	}
	public void setDevice(Integer device) {
		this.device = device;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAreaText() {
		return areaText;
	}
	public void setAreaText(String areaText) {
		this.areaText = areaText;
	}
	
	
}
