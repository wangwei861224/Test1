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
import com.hx.api.model.CapitalParmModel;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.service.ICapitalService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.service.core.IRequestResolver;
import com.hx.api.utils.GsonUtil;
import com.hx.api.utils.ResponseUtil;

@Controller
@RequestMapping("/api/capital")
public class CapitalController {

	private Logger logger = LoggerFactory.getLogger(CapitalController.class);
	
	@Resource(name = "jsonBodyResolver")
    private IRequestResolver resolver;
	@Autowired
	private ICapitalService capitalService;
	
	private static Gson gson = GsonUtil.getGsonExpose();
	
	
	/**
	 * 获取提现首页数据汇总
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getWithdrawalsDataSum")
	public void getWithdrawalsDataSum(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api capital:getWithdrawalsDataSum start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			CapitalParmModel model = resolver.resolve(request, CapitalParmModel.class);
			//业务处理
			this.capitalService.doGetWithdrawalsDataSum(rsp, model);
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
        	logger.debug("Invoke api capital:getWithdrawalsDataSum end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 添加提现操作
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addWithdrawals")
	public void addWithdrawals(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api capital:addWithdrawals start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			CapitalParmModel model = resolver.resolve(request, CapitalParmModel.class);
			//业务处理
			this.capitalService.doAddWithdrawals(rsp, model);
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
        	logger.debug("Invoke api capital:addWithdrawals end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 获取经销商绑定的银行卡信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getBankInfo")
	public void getBankInfo(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api capital:getBankInfo start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			CapitalParmModel model = resolver.resolve(request, CapitalParmModel.class);
			//业务处理
			this.capitalService.doGetBankInfo(rsp, model);
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
        	logger.debug("Invoke api capital:getBankInfo end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 获取经销商提现记录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getWithdrawalsList")
	public void getWithdrawalsList(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api capital:getWithdrawalsList start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			CapitalParmModel model = resolver.resolve(request, CapitalParmModel.class);
			//业务处理
			this.capitalService.doGetWithdrawalsList(rsp, model);
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
        	logger.debug("Invoke api capital:getWithdrawalsList end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 获取经销商资金变动记录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getFundChangeList")
	public void getFundChangeList(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api capital:getFundChangeList start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			CapitalParmModel model = resolver.resolve(request, CapitalParmModel.class);
			//业务处理
			this.capitalService.doGetFundChangeList(rsp, model);
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
        	logger.debug("Invoke api capital:getFundChangeList end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
}
