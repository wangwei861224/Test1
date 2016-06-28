package com.hx.api.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "storeorder")
public class StoreOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String orderCode;
	private String createTime;
	private Integer status;
	private String price;
	private String linkMan;
	private String mobile;
	private String address;
	private String logisticsCompany;
	private String logisticsCode;
	private Store store;
	private Integer paymentid;
	private String text1;
	private Dealer dealer;
	private String ticketStatus;
	private String wuliuCode;
	private String sendTime;
	private WuliuCompany wuliuCompany;
	private String wuliuPrice;
	private Integer payType;
	private Integer contactDealer;
	private Integer contactWuliu;
	private Integer contactPerson;
	private String contactPersonName;
	private String remark;
	private Integer statusMainTain;
	private String timeOut;
	private Integer tyrebase;
	private String priceBase;
	private String completeTime;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "ordercode")
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	@Column(name = "price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name = "linkman")
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
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
	@Column(name = "logisticscompany")
	public String getLogisticsCompany() {
		return logisticsCompany;
	}
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	@Column(name = "logisticscode")
	public String getLogisticsCode() {
		return logisticsCode;
	}
	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="storeid")
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	@Column(name = "paymentid")
	public Integer getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(Integer paymentid) {
		this.paymentid = paymentid;
	}
	@Column(name = "text1")
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="dealerid")
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	@Column(name = "ticketstatus")
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	@Column(name = "wuliucode")
	public String getWuliuCode() {
		return wuliuCode;
	}
	public void setWuliuCode(String wuliuCode) {
		this.wuliuCode = wuliuCode;
	}
	@Column(name = "sendtime")
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="wuliucompanyid")
	public WuliuCompany getWuliuCompany() {
		return wuliuCompany;
	}
	public void setWuliuCompany(WuliuCompany wuliuCompany) {
		this.wuliuCompany = wuliuCompany;
	}
	@Column(name = "wuliuprice")
	public String getWuliuPrice() {
		return wuliuPrice;
	}
	public void setWuliuPrice(String wuliuPrice) {
		this.wuliuPrice = wuliuPrice;
	}
	@Column(name = "paytype")
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	@Column(name = "contactdealer")
	public Integer getContactDealer() {
		return contactDealer;
	}
	public void setContactDealer(Integer contactDealer) {
		this.contactDealer = contactDealer;
	}
	@Column(name = "contactwuliu")
	public Integer getContactWuliu() {
		return contactWuliu;
	}
	public void setContactWuliu(Integer contactWuliu) {
		this.contactWuliu = contactWuliu;
	}
	@Column(name = "contactperson")
	public Integer getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(Integer contactPerson) {
		this.contactPerson = contactPerson;
	}
	@Column(name = "contactpersonname")
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "statusmaintain")
	public Integer getStatusMainTain() {
		return statusMainTain;
	}
	public void setStatusMainTain(Integer statusMainTain) {
		this.statusMainTain = statusMainTain;
	}
	@Column(name = "timeout")
	public String getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	@Column(name = "tyrebase")
	public Integer getTyrebase() {
		return tyrebase;
	}
	public void setTyrebase(Integer tyrebase) {
		this.tyrebase = tyrebase;
	}
	@Column(name = "pricebase")
	public String getPriceBase() {
		return priceBase;
	}
	public void setPriceBase(String priceBase) {
		this.priceBase = priceBase;
	}
	@Column(name = "completetime")
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	
	
}
