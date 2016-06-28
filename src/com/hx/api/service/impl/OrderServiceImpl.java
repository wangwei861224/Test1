package com.hx.api.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.hx.api.common.web.CommonConfig;
import com.hx.api.dao.BaseDaoSupport;
import com.hx.api.dao.NamedQueryer;
import com.hx.api.dao.Pagination;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.OrderList;
import com.hx.api.entity.StoreOrder;
import com.hx.api.entity.WuliuCompany;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.model.KuaidiModel;
import com.hx.api.model.OrderModel;
import com.hx.api.model.OrderParmModel;
import com.hx.api.model.OrderSearchModel;
import com.hx.api.service.IOrderService;
import com.hx.api.service.IUserService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.utils.DateUtils;
import com.hx.api.utils.GsonUtil;
import com.hx.api.utils.KuaidiUtil;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private BaseDaoSupport baseDaoSupport;
	@Autowired
	private IUserService userService;

	private static Gson gson = GsonUtil.getGsonExpose();
	/**
	 * 获取订单列表业务处理
	 */
	@Override
	public void doGetOrderList(InterfaceResponse rsp, OrderParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			
			List<OrderModel> orderModels = new ArrayList<OrderModel>();
			DecimalFormat df = new DecimalFormat("0.00");
			//获取订单集合
			Pagination<Object[]> orderResult = this.getOrders(model, dealer);
			List<Object[]> ordertList = orderResult.getRecords();
			if(ordertList != null && ordertList.size() > 0) {
				for(Object[] obj : ordertList) {
					OrderModel orderModel = new OrderModel();
					//信息拷贝
					orderModel.setId(Integer.parseInt(obj[0].toString()));
					orderModel.setOrderCode(obj[1].toString());
					orderModel.setCreateTime(DateUtils.format(Long.valueOf(obj[2].toString()), CommonConfig.PATTERN));
					orderModel.setPrice(df.format(Double.valueOf(obj[3].toString())));
					orderModel.setWuliuPrice(df.format(Double.valueOf(obj[4].toString())));
					orderModel.setStatus(Integer.parseInt(obj[5].toString()));
					orderModel.setProductName(obj[6].toString());
					orderModel.setSpecification(obj[7].toString());
					orderModel.setDotInfo(obj[8].toString());
					orderModel.setMinNumber(Integer.parseInt(obj[9].toString()));
					orderModel.setProductNumber(Integer.parseInt(obj[10].toString()));
					orderModel.setProductPrice(df.format(Double.valueOf(obj[11].toString())));
					orderModel.setImgs(obj[12].toString());
					orderModels.add(orderModel);
				}
			}
			Pagination<OrderModel> results = new Pagination<OrderModel>(orderResult);
			results.setRecords(orderModels);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(results);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}

	
	
	/**
	 * 获取经销商订单详情业务处理
	 */
	@Override
	public void doGetOrderInfo(InterfaceResponse rsp, OrderParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			DecimalFormat df = new DecimalFormat("0.00");
			OrderModel orderModel = new OrderModel();
			StoreOrder storeOrder = this.getOrderInfo(model.getOrderId());
			//信息拷贝
			BeanUtils.copyProperties(storeOrder, orderModel);
			orderModel.setCreateTime(DateUtils.format(Long.valueOf(storeOrder.getCreateTime()), CommonConfig.PATTERN));
			orderModel.setWuliuCompanyId(storeOrder.getWuliuCompany().getId());
			orderModel.setWuliuCompanyName(storeOrder.getWuliuCompany().getCompanyName());
			orderModel.setPrice(df.format(Double.valueOf(storeOrder.getPrice())));
			orderModel.setWuliuPrice(df.format(Double.valueOf(storeOrder.getWuliuPrice())));
			if(!StringUtils.isEmpty(storeOrder.getSendTime())) {
				orderModel.setSendTime(DateUtils.format(Long.valueOf(storeOrder.getSendTime()), CommonConfig.PATTERN));
			}
			OrderList orderList = this.getOrderListInfo(storeOrder.getOrderCode());
			orderModel.setProductName(orderList.getProduct().getProductName());
			orderModel.setSpecification(orderList.getProduct().getSpecification());
			orderModel.setImgs(orderList.getProduct().getImgs());
			orderModel.setDotInfo(orderList.getProduct().getDotInfo());
			orderModel.setMinNumber(orderList.getProduct().getMinNumber());
			orderModel.setProductNumber(orderList.getProductNumber());
			orderModel.setProductPrice(df.format(Double.valueOf(orderList.getProductPrice())));
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(orderModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}

	


	/**
	 * 经销商发货业务处理
	 */
	@Override
	public void doUpdateOrderForWuLiu(InterfaceResponse rsp, OrderParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			StoreOrder storeOrder = this.getOrderInfo(model.getOrderId());
			storeOrder.setStatus(2);
			storeOrder.setSendTime(DateUtils.format(new Date(), CommonConfig.PATTERN_ONE));
			storeOrder.setWuliuCode(model.getWuliuCode());
			WuliuCompany wuliuCompany = new WuliuCompany();
			wuliuCompany.setId(model.getWuliuCompanyId());
			storeOrder.setWuliuCompany(wuliuCompany);
			this.updateOrder(storeOrder);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}
	
	
	

	/**
	 * 更新订单状态业务处理
	 */
	@Override
	public void doUpdateOrderForStatus(InterfaceResponse rsp, OrderParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			StoreOrder storeOrder = this.getOrderInfo(model.getOrderId());
			storeOrder.setStatus(model.getStatus());
			this.updateOrder(storeOrder);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}

	
	

	/**
	 * 查询快递信息业务处理
	 */
	@Override
	public void doGetWuLiuInfo(InterfaceResponse rsp, OrderParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			StoreOrder storeOrder = this.getOrderInfo(model.getOrderId());
			if(StringUtils.isEmpty(storeOrder.getWuliuCode())) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_KUAIDI);
				return;
			}
			String resultJson = KuaidiUtil.kdQuery(storeOrder.getWuliuCompany().getCompanyCode(), storeOrder.getWuliuCode());
			KuaidiModel kdModel = gson.fromJson(resultJson, KuaidiModel.class);
			if(!"ok".equals(kdModel.getMessage())) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_QUERY_ERROR);
				return;
			}
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(kdModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}



	/**
	 * 获取经销商订单详情
	 */
	@Override
	public StoreOrder getOrderInfo(Integer orderId) {
		return this.baseDaoSupport.get(StoreOrder.class, orderId);
	}




	/**
	 * 获取订单列表
	 */
	@Override
	public Pagination<Object[]> getOrders(OrderParmModel model, Dealer dealer) {
		OrderSearchModel searchModel = new OrderSearchModel();
		searchModel.setDealerId(dealer.getId());
		searchModel.setFieldSearch(model.getFieldSearch());
		searchModel.setStatus(model.getStatus());
		
		NamedQueryer queryer = searchModel.getHql();
		Pagination<Object[]> result = new Pagination<Object[]>();
		result.setPageNo(model.getPageNo());
		result.setPageSize(model.getPageSize());
		result = this.baseDaoSupport.find(queryer, result.getPageNo(), result.getPageSize(), Object[].class);
		return result;
	}



	/**
	 * 
	 */
	@Override
	public OrderList getOrderListInfo(String orderCode) {
		String hql = "from OrderList where orderCode = '"+orderCode+"'";
		List<OrderList> orderLists = this.baseDaoSupport.find(OrderList.class, hql);
		if(orderLists != null && orderLists.size() > 0) {
			return orderLists.get(0);
		}
		return null;
	}



	/**
	 * 更新经销商订单信息
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOrder(StoreOrder order) {
		this.baseDaoSupport.update(order);
		
	}
	
	
}
