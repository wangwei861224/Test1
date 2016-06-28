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
import com.hx.api.model.VerificationCodeModel;
import com.hx.api.service.IUserService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.service.core.IRequestResolver;
import com.hx.api.utils.GsonUtil;
import com.hx.api.utils.ResponseUtil;

@Controller
@RequestMapping("/api/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Resource(name = "jsonBodyResolver")
    private IRequestResolver resolver;
	
	private static Gson gson = GsonUtil.getGsonExpose();
	
	/**
	 * 获取手机验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getVerificationCode")
    public void getVerificationCode(HttpServletRequest request, HttpServletResponse response)
    {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api user:verificationCode start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			VerificationCodeModel codeModel = resolver.resolve(request, VerificationCodeModel.class);
			//业务处理
			this.userService.doGetValiCode(request, rsp, codeModel);
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
        	logger.debug("Invoke api user:verificationCode end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
    }
	
	/**
	 * 经销商登录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/dealerLogin")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api user:login start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			VerificationCodeModel codeModel = resolver.resolve(request, VerificationCodeModel.class);
			//业务处理
			this.userService.doLogin(request, rsp, codeModel);
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
        	logger.debug("Invoke api user:login end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 经销商登出
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/dealerLogout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api user:logout start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			VerificationCodeModel codeModel = resolver.resolve(request, VerificationCodeModel.class);
			//业务处理
			this.userService.doLogout(rsp, codeModel);
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
        	logger.debug("Invoke api user:logout end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
}
