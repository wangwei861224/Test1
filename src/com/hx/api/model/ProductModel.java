package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	private Integer status;
	@Expose
	private String createTime;
	@Expose
	private String specification;
	@Expose
	private String imgs;
	@Expose
	private Integer minNumber;
	@Expose
	@SerializedName("describe")
	private String text1;
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
	@Expose
	private String brandName;
	@Expose
	private String sendCityText;
	@Expose
	private String sendShengText;
	@Expose
	private String notinCityText;
	@Expose
	private Integer dealNumber;//已成交条目
	
	/**
	 * 抛货统计参数
	 * @return
	 */
	@Expose
	private SoldStatisticsModel soldStatisticsModel;
	
	
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
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
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getSendCityText() {
		return sendCityText;
	}
	public void setSendCityText(String sendCityText) {
		this.sendCityText = sendCityText;
	}
	public String getSendShengText() {
		return sendShengText;
	}
	public void setSendShengText(String sendShengText) {
		this.sendShengText = sendShengText;
	}
	public String getNotinCityText() {
		return notinCityText;
	}
	public void setNotinCityText(String notinCityText) {
		this.notinCityText = notinCityText;
	}
	public Integer getDealNumber() {
		return dealNumber;
	}
	public void setDealNumber(Integer dealNumber) {
		this.dealNumber = dealNumber;
	}
	public SoldStatisticsModel getSoldStatisticsModel() {
		return soldStatisticsModel;
	}
	public void setSoldStatisticsModel(SoldStatisticsModel soldStatisticsModel) {
		this.soldStatisticsModel = soldStatisticsModel;
	}
	
}
