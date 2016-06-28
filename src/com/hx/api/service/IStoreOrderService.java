package com.hx.api.service;

import com.hx.api.entity.StoreOrder;

public interface IStoreOrderService {

	public StoreOrder getStoreOrderByCode(String orderCode);
	
	public Integer getStatusByCode(String orderCode);
}
