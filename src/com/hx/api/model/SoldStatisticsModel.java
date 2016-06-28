package com.hx.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class SoldStatisticsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 抛货统计参数
	 * @return
	 */
	@Expose
	private Integer totalOrderNum;//总订单数
	@Expose
	private Integer totalStripNum;//总成交条数
	@Expose
	private String totalDealPrice;//总成交额
	@Expose
	List<SoldStatisticsViewModel> resultSet = new ArrayList<SoldStatisticsViewModel>();

	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(Integer totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public Integer getTotalStripNum() {
		return totalStripNum;
	}

	public void setTotalStripNum(Integer totalStripNum) {
		this.totalStripNum = totalStripNum;
	}

	public String getTotalDealPrice() {
		return totalDealPrice;
	}

	public void setTotalDealPrice(String totalDealPrice) {
		this.totalDealPrice = totalDealPrice;
	}

	public List<SoldStatisticsViewModel> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<SoldStatisticsViewModel> resultSet) {
		this.resultSet = resultSet;
	}

	
}
