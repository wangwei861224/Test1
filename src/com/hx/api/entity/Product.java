package com.hx.api.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String productName;
	private String price;
	private String salePrice;
	private String webPrice;
	private Integer inventory;
	private Dealer dealer;
	private String createTime;
	private Integer status;
	private String code;
	private String specification;
	private Brand brand;
	private String imgs;
	private String desc;
	private Integer minNumber;
	private String text1;
	private String text2;
	private String text3;
	private String weight;
	private String dotInfo;
	private Integer sendCity;
	private Integer sendSheng;
	private String notinCity;
	private Integer specId;
	
	private Set<OrderList> orderLists;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "productname")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name = "price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name = "saleprice")
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	@Column(name = "webprice")
	public String getWebPrice() {
		return webPrice;
	}
	public void setWebPrice(String webPrice) {
		this.webPrice = webPrice;
	}
	@Column(name = "inventory")
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="dealerid")
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	@Column(name = "createtime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "specification")
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="brandid")
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	@Column(name = "imgs")
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	@Column(name = "_desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Column(name = "minnumber")
	public Integer getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}
	@Column(name = "text1")
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	@Column(name = "text2")
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	@Column(name = "text3")
	public String getText3() {
		return text3;
	}
	public void setText3(String text3) {
		this.text3 = text3;
	}
	@Column(name = "weight")
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Column(name = "dotinfo")
	public String getDotInfo() {
		return dotInfo;
	}
	public void setDotInfo(String dotInfo) {
		this.dotInfo = dotInfo;
	}
	@Column(name = "sendcity")
	public Integer getSendCity() {
		return sendCity;
	}
	public void setSendCity(Integer sendCity) {
		this.sendCity = sendCity;
	}
	@Column(name = "sendsheng")
	public Integer getSendSheng() {
		return sendSheng;
	}
	public void setSendSheng(Integer sendSheng) {
		this.sendSheng = sendSheng;
	}
	@Column(name = "notincity")
	public String getNotinCity() {
		return notinCity;
	}
	public void setNotinCity(String notinCity) {
		this.notinCity = notinCity;
	}
	@Column(name = "specid")
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	@OneToMany(mappedBy="product",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	public Set<OrderList> getOrderLists() {
		return orderLists;
	}
	public void setOrderLists(Set<OrderList> orderLists) {
		this.orderLists = orderLists;
	}
	
}
