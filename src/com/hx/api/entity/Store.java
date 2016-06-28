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
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String storeName;
	private String companyName;
	private String linkMan;
	private String mobile;
	private String introducerMobile;
	private String regTime;
	private String updateTime;
	private String businesshours;
	private Integer status;
	private String logo;
	private String address;
	private String longitude;
	private String latitude;
	private Integer staffNumber;
	private Integer plantingDuration;
	private String covering;
	private String imgs;
	private String businesslicense;
	private String email;
	private Integer device;
	private Integer areaId;
	private String text1;
	private String token;
	private String city;
	private Integer shengcode;
	private Integer delflag;
	private Integer frozen;
	private String text2;
	
	private Set<StoreOrder> storeOrders;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "storename")
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Column(name = "companyname")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	@Column(name = "introducer_mobile")
	public String getIntroducerMobile() {
		return introducerMobile;
	}
	public void setIntroducerMobile(String introducerMobile) {
		this.introducerMobile = introducerMobile;
	}
	@Column(name = "regtime")
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	@Column(name = "updatetime")
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "businesshours")
	public String getBusinesshours() {
		return businesshours;
	}
	public void setBusinesshours(String businesshours) {
		this.businesshours = businesshours;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "logo")
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name = "staffnumber")
	public Integer getStaffNumber() {
		return staffNumber;
	}
	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}
	@Column(name = "plantingduration")
	public Integer getPlantingDuration() {
		return plantingDuration;
	}
	public void setPlantingDuration(Integer plantingDuration) {
		this.plantingDuration = plantingDuration;
	}
	@Column(name = "covering")
	public String getCovering() {
		return covering;
	}
	public void setCovering(String covering) {
		this.covering = covering;
	}
	@Column(name = "imgs")
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	@Column(name = "businesslicense")
	public String getBusinesslicense() {
		return businesslicense;
	}
	public void setBusinesslicense(String businesslicense) {
		this.businesslicense = businesslicense;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "device")
	public Integer getDevice() {
		return device;
	}
	public void setDevice(Integer device) {
		this.device = device;
	}
	@Column(name = "areaid")
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	@Column(name = "text1")
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	@Column(name = "token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "shengcode")
	public Integer getShengcode() {
		return shengcode;
	}
	public void setShengcode(Integer shengcode) {
		this.shengcode = shengcode;
	}
	@Column(name = "delflag")
	public Integer getDelflag() {
		return delflag;
	}
	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
	@Column(name = "frozen")
	public Integer getFrozen() {
		return frozen;
	}
	public void setFrozen(Integer frozen) {
		this.frozen = frozen;
	}
	@Column(name = "text2")
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	@OneToMany(mappedBy="store",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	public Set<StoreOrder> getStoreOrders() {
		return storeOrders;
	}
	public void setStoreOrders(Set<StoreOrder> storeOrders) {
		this.storeOrders = storeOrders;
	}
	
	
}
