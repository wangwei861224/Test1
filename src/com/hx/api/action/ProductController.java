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
import com.hx.api.model.ProductParmModel;
import com.hx.api.service.IProductService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.service.core.IRequestResolver;
import com.hx.api.utils.GsonUtil;
import com.hx.api.utils.ResponseUtil;

@Controller
@RequestMapping("/api/product")
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IProductService productService;
	
	@Resource(name = "jsonBodyResolver")
    private IRequestResolver resolver;
	
	private static Gson gson = GsonUtil.getGsonExpose();
	
	/**
	 * 获取经销商商品列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getProductList")
	public void getProductList(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:getProductList start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
			this.productService.doGetProductList(rsp, model);
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
        	logger.debug("Invoke api product:getProductList end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 查询商品详情
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getProductInfo")
	public void getProductInfo(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:getProductInfo start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
			this.productService.doGetProductInfo(rsp, model);
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
        	logger.debug("Invoke api product:getProductInfo end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 新增商品
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addProduct")
	public void addProductInfo(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:addProduct start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
			this.productService.doAddProductInfo(rsp, model);
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
        	logger.debug("Invoke api product:addProduct end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 编辑商品
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateProduct")
	public void updateProductInfo(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:updateProduct start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
			this.productService.doUpdateProductInfo(rsp, model);
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
        	logger.debug("Invoke api product:updateProduct end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 更改商品状态，可做删除处理
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateProductStatus")
	public void updateProductStatus(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:updateProductStatus start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
			this.productService.doUpdateProductStatus(rsp, model);
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
        	logger.debug("Invoke api product:updateProductStatus end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 更改商品状态，可做批量上架或下架处理
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateProductStatusBatch")
	public void updateProductStatusBatch(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:updateProductStatusBatch start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
			this.productService.doUpdateProductStatusBatch(rsp, model);
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
        	logger.debug("Invoke api product:updateProductStatusBatch end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 抛货统计
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getSoldStatistics")
	public void getSoldStatistics(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:getSoldStatistics start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
			this.productService.doSoldStatistics(rsp, model);
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
        	logger.debug("Invoke api product:getSoldStatistics end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
	
	/**
	 * 抛货详情
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getSoldInfo")
	public void getSoldInfo(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
        {
			logger.debug("Invoke api product:getSoldInfo start.");
        }
		
		InterfaceResponse rsp = new InterfaceResponse();
		try {
			//参数模型
			ProductParmModel model = resolver.resolve(request, ProductParmModel.class);
			//业务处理
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
        	logger.debug("Invoke api product:getSoldInfo end.");
        }
		ResponseUtil.renderJson(response, gson.toJson(rsp).toString());
	}
}
