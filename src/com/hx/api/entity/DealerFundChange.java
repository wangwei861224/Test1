package com.hx.api.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dealerfundchange")
public class DealerFundChange implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Dealer dealer;
	private Integer type;
	private String price;
	private String createTime;
	private String beforPrice;
	private String afterPrice;
	private String orderCode;
	private Integer tiXianId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="dealerid")
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name = "createtime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "beforprice")
	public String getBeforPrice() {
		return beforPrice;
	}
	public void setBeforPrice(String beforPrice) {
		this.beforPrice = beforPrice;
	}
	@Column(name = "afterprice")
	public String getAfterPrice() {
		return afterPrice;
	}
	public void setAfterPrice(String afterPrice) {
		this.afterPrice = afterPrice;
	}
	@Column(name = "ordercode")
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	@Column(name = "tixianid")
	public Integer getTiXianId() {
		return tiXianId;
	}
	public void setTiXianId(Integer tiXianId) {
		this.tiXianId = tiXianId;
	}

}
