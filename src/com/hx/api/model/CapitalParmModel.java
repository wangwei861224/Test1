package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class CapitalParmModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private String phone;
	@Expose
	private String token;
	
	/**
	 * 提现参数
	 * @return
	 */
	//提现金额
	@Expose
	private String tiXianPrice;
	//银行名称
	@Expose
	private String bankName;
	//卡号
	@Expose
	private String cardNo;
	//开户名
	@Expose
	private String userName;
	
	/**
	 * 提现记录筛选条件
	 * 
	 * today;week;month;all
	 */
	@Expose
	private Integer pageNo;
	@Expose
	private Integer pageSize;
	@Expose
	private String timeSection;
	
	
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
	public String getTiXianPrice() {
		return tiXianPrice;
	}
	public void setTiXianPrice(String tiXianPrice) {
		this.tiXianPrice = tiXianPrice;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTimeSection() {
		return timeSection;
	}
	public void setTimeSection(String timeSection) {
		this.timeSection = timeSection;
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
