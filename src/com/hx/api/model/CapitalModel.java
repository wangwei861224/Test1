package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class CapitalModel implements Serializable {

	private static final long serialVersionUID = 1L;

	//经销商余额
	@Expose
	private String balance;
	//经销商累计提款
	@Expose
	private String withdrawPrice;
	//经销商累计收益
	@Expose
	private String incomePrice;
	
	
	/**
	 * 提现记录
	 */
	//提现时间
	@Expose
	private String createTime;
	//提现金额
	@Expose
	private String price;
	//提现状态
	@Expose
	private Integer status;
	//银行名称
	@Expose
	private String bankName;
	//持卡人姓名
	@Expose
	private String bankPerson;
	//卡号
	@Expose
	private String cardNo;
	
	/**
	 * 资金变动参数
	 * 
	 */
	//变动类型 0:销售转入；1：提现转出；2：扣除手续费；3：扣除物流费
	@Expose
	private Integer type;
	//变动金额
	@Expose
	private String fcPrice;
	//变更前金额
	@Expose
	private String beforePrice;
	//余额
	@Expose
	private String balancePrice;
	
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFcPrice() {
		return fcPrice;
	}
	public void setFcPrice(String fcPrice) {
		this.fcPrice = fcPrice;
	}
	public String getBeforePrice() {
		return beforePrice;
	}
	public void setBeforePrice(String beforePrice) {
		this.beforePrice = beforePrice;
	}
	public String getBalancePrice() {
		return balancePrice;
	}
	public void setBalancePrice(String balancePrice) {
		this.balancePrice = balancePrice;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getWithdrawPrice() {
		return withdrawPrice;
	}
	public void setWithdrawPrice(String withdrawPrice) {
		this.withdrawPrice = withdrawPrice;
	}
	public String getIncomePrice() {
		return incomePrice;
	}
	public void setIncomePrice(String incomePrice) {
		this.incomePrice = incomePrice;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankPerson() {
		return bankPerson;
	}
	public void setBankPerson(String bankPerson) {
		this.bankPerson = bankPerson;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
		
		
}
