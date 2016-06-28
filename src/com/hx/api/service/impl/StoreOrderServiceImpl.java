package com.hx.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.api.dao.BaseDaoSupport;
import com.hx.api.entity.StoreOrder;
import com.hx.api.service.IStoreOrderService;

@Service
@Transactional
public class StoreOrderServiceImpl implements IStoreOrderService {

	private Logger logger = LoggerFactory.getLogger(StoreOrderServiceImpl.class);
	
	@Autowired
	private BaseDaoSupport baseDaoSupport;

	
	/**
	 * 根据订单编号获取订单实例
	 */
	@Override
	public StoreOrder getStoreOrderByCode(String orderCode) {
		String hql = "from StoreOrder where orderCode = '"+orderCode+"'";
		List<StoreOrder> storeOrders = this.baseDaoSupport.find(StoreOrder.class, hql);
		if(storeOrders != null && storeOrders.size() > 0) {
			return storeOrders.get(0);
		}
		return null;
	}


	/**
	 * 根据订单编号取得订单状态
	 */
	@Override
	public Integer getStatusByCode(String orderCode) {
		Integer status = 0;
		String hql = "select status from StoreOrder where orderCode = '"+orderCode+"'";
		List<Integer> sts = this.baseDaoSupport.find(Integer.class, hql);
		if(sts != null && sts.size() > 0) {
			return sts.get(0);
		}
		return status;
	}
	
	
}
