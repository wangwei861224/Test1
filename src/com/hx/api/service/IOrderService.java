package com.hx.api.service;

import com.hx.api.dao.Pagination;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.OrderList;
import com.hx.api.entity.StoreOrder;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.model.OrderParmModel;

public interface IOrderService {

	public void doGetOrderList(InterfaceResponse rsp, OrderParmModel model);
	
	public Pagination<Object[]> getOrders(OrderParmModel model, Dealer dealer);
	
	public void doGetOrderInfo(InterfaceResponse rsp, OrderParmModel model);
	
	public StoreOrder getOrderInfo(Integer orderId);
	
	public OrderList getOrderListInfo(String orderCode);
	
	public void doUpdateOrderForWuLiu(InterfaceResponse rsp, OrderParmModel model);
	
	public void updateOrder(StoreOrder order);
	
	public void doUpdateOrderForStatus(InterfaceResponse rsp, OrderParmModel model);
	
	public void doGetWuLiuInfo(InterfaceResponse rsp, OrderParmModel model);
}
