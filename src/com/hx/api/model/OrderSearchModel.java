package com.hx.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hx.api.dao.NamedQueryer;

public class OrderSearchModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer dealerId;
	private String fieldSearch;
	private Integer status;
	
	public NamedQueryer getHql() {
		NamedQueryer queryer = new NamedQueryer();
		List<String> names = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("select so.id,so.orderCode,so.createTime,so.price,so.wuliuPrice,so.status,ol.product.productName,ol.product.specification,ol.product.dotInfo,ol.product.minNumber,ol.productNumber,ol.productPrice,ol.product.imgs "
				+ "from StoreOrder so, OrderList ol "
				+ "where so.orderCode = ol.orderCode");
		if(null != status) {
			hql.append(" and so.status = :status");
			names.add("status");
			values.add(status);
		}
		if(null != fieldSearch && !"".equals(fieldSearch)) {
			hql.append(" and (so.orderCode like :orderCode or ol.product.brand.brandName like :brandName or ol.product.text2 like :spec)");
			names.add("orderCode");
			names.add("brandName");
			names.add("spec");
			values.add("%"+fieldSearch+"%");
			values.add("%"+fieldSearch+"%");
			values.add("%"+fieldSearch+"%");
		}
		if(null != dealerId) {
			hql.append(" and so.dealer.id = :dealerId");
			names.add("dealerId");
			values.add(dealerId);
		}
		hql.append(" order by so.id desc");
		queryer.setHql(hql.toString());
		queryer.setNames(names);
		queryer.setValues(values);
		queryer.setCountHql(this.getCountHql());
		return queryer;
	}
	
	public String getCountHql() {
		StringBuffer hql = new StringBuffer("select count(*) from StoreOrder so, OrderList ol where so.orderCode = ol.orderCode");
		if(null != status) {
			hql.append(" and so.status = :status");
		}
		if(null != fieldSearch && !"".equals(fieldSearch)) {
			hql.append(" and (so.orderCode like :orderCode or ol.product.brand.brandName like :brandName or ol.product.text2 like :spec)");
		}
		if(null != dealerId) {
			hql.append(" and so.dealer.id = :dealerId");
		}
		return hql.toString();
	}
	
	
	public Integer getDealerId() {
		return dealerId;
	}
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
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

}
