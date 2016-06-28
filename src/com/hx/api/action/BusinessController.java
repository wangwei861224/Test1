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
import com.hx.api.model.BusinessParmModel;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.service.IBusinessService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.service.core.IRequestResolver;
import com.hx.api.utils.GsonUtil;
import com.hx.api.utils.ResponseUtil;

@Controller
@RequestMapping("/api/business")
public class BusinessController {

private Logger logger = LoggerFactory.getLogger(BusinessController.class);
	
	@Resource(name = "jsonBodyResolver")
    private IRequestResolver resolver;
	
	@Autowired
	private IBusinessService businessService;
	
	private static Gson gson = GsonUtil.getGsonExpose();
	
	
	/**
	 * 获取物流列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getWuLiuList")
	public void getWuLiuList(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api business:getWuLiuList start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			BusinessParmModel model = resolver.resolve(request, BusinessParmModel.class);
			//业务处理
			this.businessService.doGetWuLiuList(rsp, model);
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
        	logger.debug("Invoke api business:getWuLiuList end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/uploadFile")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api business:uploadFile start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			BusinessParmModel model = resolver.resolve(request, BusinessParmModel.class);
			//业务处理
			this.businessService.doUploadFile(request, rsp, model);
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
        	logger.debug("Invoke api business:uploadFile end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
}
