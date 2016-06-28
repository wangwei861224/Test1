package com.hx.api.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.model.OrderParmModel;
import com.hx.api.service.IOrderService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.service.core.IRequestResolver;
import com.hx.api.utils.GsonUtil;
import com.hx.api.utils.ResponseUtil;

@Controller
@RequestMapping("/api/order")
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Resource(name = "jsonBodyResolver")
    private IRequestResolver resolver;
	
	@Autowired
	private IOrderService orderService;
	
	private static Gson gson = GsonUtil.getGsonExpose();
	
	/**
	 * 获取经销商订单列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getOrderList")
	public void getOrderList(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api ordert:getOrderList start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			OrderParmModel model = resolver.resolve(request, OrderParmModel.class);
			//业务处理
			this.orderService.doGetOrderList(rsp, model);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		} catch (Exception e)
        {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
        }
        
        if (logger.isDebugEnabled())
        {
        	logger.debug("Response body {}", gson.toJson(rsp).toString());
        	logger.debug("Invoke api order:getOrderList end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 获取经销商订单详情
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getOrderInfo")
	public void getOrderInfo(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api order:getOrderInfo start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			OrderParmModel model = resolver.resolve(request, OrderParmModel.class);
			//业务处理
			this.orderService.doGetOrderInfo(rsp, model);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		} catch (Exception e)
        {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
        }
        
        if (logger.isDebugEnabled())
        {
        	logger.debug("Response body {}", gson.toJson(rsp).toString());
        	logger.debug("Invoke api order:getOrderInfo end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 经销商发货
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateOrderForWuLiu")
	public void updateOrderForWuLiu(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api order:updateOrderForWuLiu start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			OrderParmModel model = resolver.resolve(request, OrderParmModel.class);
			//业务处理
			this.orderService.doUpdateOrderForWuLiu(rsp, model);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		} catch (Exception e)
        {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
        }
        
        if (logger.isDebugEnabled())
        {
        	logger.debug("Response body {}", gson.toJson(rsp).toString());
        	logger.debug("Invoke api order:updateOrderForWuLiu end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 更改订单状态
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateOrderForStatus")
	public void updateOrderForStatus(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api order:updateOrderForStatus start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			OrderParmModel model = resolver.resolve(request, OrderParmModel.class);
			//业务处理
			this.orderService.doUpdateOrderForStatus(rsp, model);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		} catch (Exception e)
        {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
        }
        
        if (logger.isDebugEnabled())
        {
        	logger.debug("Response body {}", gson.toJson(rsp).toString());
        	logger.debug("Invoke api order:updateOrderForStatus end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 经销商查询快递信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getWuLiuInfo")
	public void getWuLiuInfo(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api order:getWuLiuInfo start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			OrderParmModel model = resolver.resolve(request, OrderParmModel.class);
			//业务处理
			this.orderService.doGetWuLiuInfo(rsp, model);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		} catch (Exception e)
        {
			logger.error(e.getMessage(), e);
            rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
        }
        
        if (logger.isDebugEnabled())
        {
        	logger.debug("Response body {}", gson.toJson(rsp).toString());
        	logger.debug("Invoke api order:getWuLiuInfo end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
}
