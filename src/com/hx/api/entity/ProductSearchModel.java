package com.hx.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.hx.api.dao.NamedQueryer;

public class ProductSearchModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer dealerId;
	private String fieldSearch;
	private Integer status;
	private String cycle;
	
	public NamedQueryer getHql() {
		NamedQueryer queryer = new NamedQueryer();
		List<String> names = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from Product where 1=1");
		if(null != status) {
			hql.append(" and status = :status");
			names.add("status");
			values.add(status);
		}
		if(null != fieldSearch && !"".equals(fieldSearch)) {
			hql.append(" and (brand.brandName like :brandName or text2 like :spec)");
			names.add("brandName");
			names.add("spec");
			values.add("%"+fieldSearch+"%");
			values.add("%"+fieldSearch+"%");
		}
		if(null != dealerId) {
			hql.append(" and dealer.id = :dealerId");
			names.add("dealerId");
			values.add(dealerId);
		}
		hql.append(" order by id desc");
		queryer.setHql(hql.toString());
		queryer.setNames(names);
		queryer.setValues(values);
		return queryer;
	}
	
	/**
	 * 根据月统计
	 * @return
	 */
	public String getSqlForMonth() {
		StringBuffer sql = new StringBuffer("select DATE_FORMAT(DATE(so.createTime),'%Y-%m'),SUM(so.price),COUNT(ol.id),SUM(ol.productNumber) from StoreOrder so,OrderList ol where so.orderCode = ol.orderCode and so.status = 9");
		if(null != dealerId) {
			sql.append(" and so.dealer.id = "+dealerId+"");
		}
		sql.append(" GROUP BY DATE_FORMAT(DATE(so.createTime),'%Y-%m')");
		return sql.toString();
	}
	
	/**
	 * 根据天统计
	 * @return
	 */
	public String getSqlForDay() {
		StringBuffer sql = new StringBuffer("select DATE_FORMAT(DATE(so.createTime),'%Y-%m-%d'),SUM(so.price),COUNT(ol.id),SUM(ol.productNumber) from StoreOrder so,OrderList ol where so.orderCode = ol.orderCode and so.status = 9");
		if(null != dealerId) {
			sql.append(" and so.dealer.id = "+dealerId+"");
		}
		if(!StringUtils.isEmpty(cycle)) {
			sql.append(" and DATE_FORMAT(DATE(so.createTime),'%Y-%m') = '"+cycle+"'");
		}
		sql.append(" GROUP BY DATE_FORMAT(DATE(so.createTime),'%Y-%m-%d')");
		return sql.toString();
	}
	
	/**
	 * 统计总数据
	 * @return
	 */
	public String getSqlForTotal() {
		StringBuffer sql = new StringBuffer("select COUNT(so.id),SUM(ol.productNumber),SUM(so.price) from StoreOrder so,OrderList ol where so.orderCode = ol.orderCode and so.status = 9");
		if(null != dealerId) {
			sql.append(" and so.dealer.id = "+dealerId+"");
		}
		return sql.toString();
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

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	
	
}
