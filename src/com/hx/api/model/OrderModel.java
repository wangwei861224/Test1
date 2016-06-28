package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class OrderModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * order
	 */
	@Expose
	private Integer id;
	@Expose
	private String orderCode;
	@Expose
	private String createTime;
	@Expose
	private Integer status;
	@Expose
	private String price;
	@Expose
	private String linkMan;
	@Expose
	private String mobile;
	@Expose
	private String address;
	@Expose
	private String wuliuCode;
	@Expose
	private String sendTime;
	@Expose
	private Integer wuliuCompanyId;
	@Expose
	private String wuliuCompanyName;
	@Expose
	private String wuliuPrice;
	@Expose
	private Integer payType;
	@Expose
	private String remark;
	
	/**
	 * product
	 */
	@Expose
	private String dotInfo;
	@Expose
	private Integer minNumber;
	@Expose
	private String specification;
	@Expose
	private String productName;
	@Expose
	private String imgs;
	
	/**
	 * orderList
	 */
	@Expose
	private Integer productNumber;
	@Expose
	private String productPrice;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
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
	public String getWuliuCode() {
		return wuliuCode;
	}
	public void setWuliuCode(String wuliuCode) {
		this.wuliuCode = wuliuCode;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getWuliuCompanyId() {
		return wuliuCompanyId;
	}
	public void setWuliuCompanyId(Integer wuliuCompanyId) {
		this.wuliuCompanyId = wuliuCompanyId;
	}
	public String getWuliuCompanyName() {
		return wuliuCompanyName;
	}
	public void setWuliuCompanyName(String wuliuCompanyName) {
		this.wuliuCompanyName = wuliuCompanyName;
	}
	public String getWuliuPrice() {
		return wuliuPrice;
	}
	public void setWuliuPrice(String wuliuPrice) {
		this.wuliuPrice = wuliuPrice;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDotInfo() {
		return dotInfo;
	}
	public void setDotInfo(String dotInfo) {
		this.dotInfo = dotInfo;
	}
	public Integer getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	
}
