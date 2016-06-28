package com.hx.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hx.api.dao.NamedQueryer;
import com.hx.api.utils.DateUtils;

public class CapitalSearchModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String timeSection;
	private Integer dealerId;
	
	
	public NamedQueryer getTiXianHql() {
		NamedQueryer query = new NamedQueryer();
		List<String> names = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		
		StringBuffer hql = new StringBuffer("from TiXianList t where 1=1");
		if("today".equals(timeSection)) {
			Date today = new Date();
			String startTime = DateUtils.format(DateUtils.toStartDate(today)).toString();
			String endTime = DateUtils.format(DateUtils.toEndDate(today)).toString();
			hql.append(" and t.createTime >= :startTime");
			hql.append(" and t.createTime <= :endTime");
			names.add("startTime");
			names.add("endTime");
			values.add(startTime);
			values.add(endTime);
		} else if("week".equals(timeSection)) {
			Date firstDayOfWeek = DateUtils.getFirstDayOfWeek();
			Date lastDayOfWeek = DateUtils.getLastDayOfWeek();
			String startTime = DateUtils.format(DateUtils.toStartDate(firstDayOfWeek)).toString();
			String endTime = DateUtils.format(DateUtils.toEndDate(lastDayOfWeek)).toString();
			hql.append(" and t.createTime >= :startTime");
			hql.append(" and t.createTime <= :endTime");
			names.add("startTime");
			names.add("endTime");
			values.add(startTime);
			values.add(endTime);
		} else if("month".equals(timeSection)) {
			Map<String, Date> dateMap = DateUtils.findNowMonth();
			String startTime = DateUtils.format(DateUtils.toStartDate(dateMap.get("beginTime"))).toString();
			String endTime = DateUtils.format(DateUtils.toEndDate(dateMap.get("endTime"))).toString();
			hql.append(" and t.createTime >= :startTime");
			hql.append(" and t.createTime <= :endTime");
			names.add("startTime");
			names.add("endTime");
			values.add(startTime);
			values.add(endTime);
		}
		hql.append(" and t.dealer.id = :dealerId");
		names.add("dealerId");
		values.add(dealerId);
		
		query.setHql(hql.toString());
		query.setNames(names);
		query.setValues(values);
		return query;
	}
	
	public NamedQueryer getFundChangeHql() {
		NamedQueryer query = new NamedQueryer();
		List<String> names = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		
		StringBuffer hql = new StringBuffer("from DealerFundChange d where 1=1");
		if("today".equals(timeSection)) {
			Date today = new Date();
			String startTime = DateUtils.format(DateUtils.toStartDate(today)).toString();
			String endTime = DateUtils.format(DateUtils.toEndDate(today)).toString();
			hql.append(" and d.createTime >= :startTime");
			hql.append(" and d.createTime <= :endTime");
			names.add("startTime");
			names.add("endTime");
			values.add(startTime);
			values.add(endTime);
		} else if("week".equals(timeSection)) {
			Date firstDayOfWeek = DateUtils.getFirstDayOfWeek();
			Date lastDayOfWeek = DateUtils.getLastDayOfWeek();
			String startTime = DateUtils.format(DateUtils.toStartDate(firstDayOfWeek)).toString();
			String endTime = DateUtils.format(DateUtils.toEndDate(lastDayOfWeek)).toString();
			hql.append(" and d.createTime >= :startTime");
			hql.append(" and d.createTime <= :endTime");
			names.add("startTime");
			names.add("endTime");
			values.add(startTime);
			values.add(endTime);
		} else if("month".equals(timeSection)) {
			Map<String, Date> dateMap = DateUtils.findNowMonth();
			String startTime = DateUtils.format(DateUtils.toStartDate(dateMap.get("beginTime"))).toString();
			String endTime = DateUtils.format(DateUtils.toEndDate(dateMap.get("endTime"))).toString();
			hql.append(" and d.createTime >= :startTime");
			hql.append(" and d.createTime <= :endTime");
			names.add("startTime");
			names.add("endTime");
			values.add(startTime);
			values.add(endTime);
		}
		hql.append(" and d.dealer.id = :dealerId");
		names.add("dealerId");
		values.add(dealerId);
		
		query.setHql(hql.toString());
		query.setNames(names);
		query.setValues(values);
		return query;
	}

	
	public String getTimeSection() {
		return timeSection;
	}

	public void setTimeSection(String timeSection) {
		this.timeSection = timeSection;
	}


	public Integer getDealerId() {
		return dealerId;
	}


	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	
	
	

}
