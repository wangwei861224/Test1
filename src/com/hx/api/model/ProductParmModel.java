package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class ProductParmModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 列表参数
	 */
	@Expose
	private String phone;
	@Expose
	private String token;
	@Expose
	private Integer productId;
	@Expose
	private String fieldSearch;
	@Expose
	private Integer status;
	@Expose
	private Integer pageNo;
	@Expose
	private Integer pageSize;
	
	/**
	 * 商品上架或下架ID
	 */
	@Expose
	private String productIds;
	
	/**
	 * 抛货统计
	 * 周期
	 */
	@Expose
	private String cycle;
	
	/**
	 * 新增，编辑参数
	 */
	@Expose
	private Integer id;
	@Expose
	private String productName;
	@Expose
	private String price;
	@Expose
	private String salePrice;
	@Expose
	private String webPrice;
	@Expose
	private Integer inventory;
	@Expose
	private String specification;
	@Expose
	private String imgs;
	@Expose
	private Integer minNumber;
	@Expose
	private String describe;
	@Expose
	private String dotInfo;
	@Expose
	private Integer sendCity;
	@Expose
	private Integer sendSheng;
	@Expose
	private String notinCity;
	@Expose
	private Integer brandId;
	
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
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getWebPrice() {
		return webPrice;
	}
	public void setWebPrice(String webPrice) {
		this.webPrice = webPrice;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public Integer getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getDotInfo() {
		return dotInfo;
	}
	public void setDotInfo(String dotInfo) {
		this.dotInfo = dotInfo;
	}
	public Integer getSendCity() {
		return sendCity;
	}
	public void setSendCity(Integer sendCity) {
		this.sendCity = sendCity;
	}
	public Integer getSendSheng() {
		return sendSheng;
	}
	public void setSendSheng(Integer sendSheng) {
		this.sendSheng = sendSheng;
	}
	public String getNotinCity() {
		return notinCity;
	}
	public void setNotinCity(String notinCity) {
		this.notinCity = notinCity;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	
}
