package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class SoldStatisticsViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private String cycle;//周期
	@Expose
	private String dealPrice;//成交金额
	@Expose
	private Integer orderNum;//订单数
	@Expose
	private Integer stripNum;//抛货数
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(String dealPrice) {
		this.dealPrice = dealPrice;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getStripNum() {
		return stripNum;
	}
	public void setStripNum(Integer stripNum) {
		this.stripNum = stripNum;
	}
	
}
