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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dealer")
public class Dealer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String dealerName;
	private String linkName;
	private String mobile;
	private String address;
	private String regTime;
	private String businesslicense;//营业执照
	private Integer status;
	private String organization;//组织代码证
	private String brandproxy;//品牌代理资格证
	private Integer device;
	private String text1;
	private String text2;
	private String text3;
	private String areaId;
	private String balance;
	private String delflag;
	private String frozen;
	private String token;
	
	private Set<Product> products;
	private Set<StoreOrder> storeOrders;
	private Set<TiXianList> tiXianLists;
	private Set<DealerFundChange> dealerFundChanges;
	private Set<DealerBankInfo> dealerBankInfo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "dealername")
	public String getDealerName() {
		return dealerName;
	}
	
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	@Column(name = "linkman")
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "regtime")
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	@Column(name = "businesslicense")
	public String getBusinesslicense() {
		return businesslicense;
	}
	public void setBusinesslicense(String businesslicense) {
		this.businesslicense = businesslicense;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "organization")
	public String getOrganization() {
		return organization;
	}
	
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	@Column(name = "brandproxy")
	public String getBrandproxy() {
		return brandproxy;
	}
	public void setBrandproxy(String brandproxy) {
		this.brandproxy = brandproxy;
	}
	@Column(name = "device")
	public Integer getDevice() {
		return device;
	}
	public void setDevice(Integer device) {
		this.device = device;
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
	@Column(name = "areaid")
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name = "balance")
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Column(name = "delflag")
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	@Column(name = "frozen")
	public String getFrozen() {
		return frozen;
	}
	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}
	@Column(name = "token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@OneToMany(mappedBy="dealer",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	@OneToMany(mappedBy="dealer",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	public Set<StoreOrder> getStoreOrders() {
		return storeOrders;
	}
	public void setStoreOrders(Set<StoreOrder> storeOrders) {
		this.storeOrders = storeOrders;
	}
	@OneToMany(mappedBy="dealer",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	public Set<TiXianList> getTiXianLists() {
		return tiXianLists;
	}
	public void setTiXianLists(Set<TiXianList> tiXianLists) {
		this.tiXianLists = tiXianLists;
	}
	@OneToMany(mappedBy="dealer",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	public Set<DealerFundChange> getDealerFundChanges() {
		return dealerFundChanges;
	}
	public void setDealerFundChanges(Set<DealerFundChange> dealerFundChanges) {
		this.dealerFundChanges = dealerFundChanges;
	}
	@OneToMany(mappedBy="dealer",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	public Set<DealerBankInfo> getDealerBankInfo() {
		return dealerBankInfo;
	}
	public void setDealerBankInfo(Set<DealerBankInfo> dealerBankInfo) {
		this.dealerBankInfo = dealerBankInfo;
	}
	
	
	
	
}
