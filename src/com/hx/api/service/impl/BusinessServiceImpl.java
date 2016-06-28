package com.hx.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hx.api.dao.BaseDaoSupport;
import com.hx.api.dao.NamedQueryer;
import com.hx.api.dao.Pagination;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.WuliuCompany;
import com.hx.api.model.BusinessModel;
import com.hx.api.model.BusinessParmModel;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.service.IBusinessService;
import com.hx.api.service.IUserService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.utils.UploadHandle;

@Service
@Transactional
public class BusinessServiceImpl implements IBusinessService {

	private Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

	@Autowired
	private BaseDaoSupport baseDaoSupport;
	@Autowired
	private IUserService userService;

	
	/**
	 *	获取物流列表业务处理
	 */
	@Override
	public void doGetWuLiuList(InterfaceResponse rsp, BusinessParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			List<BusinessModel> businessModels = new ArrayList<BusinessModel>();
			Pagination<WuliuCompany> results = this.getWuliuList(model);
			List<WuliuCompany> wuliuCompanies = results.getRecords();
			if(wuliuCompanies != null && wuliuCompanies.size() > 0) {
				for(WuliuCompany wlc : wuliuCompanies) {
					BusinessModel businessModel = new BusinessModel();
					businessModel.setCompanyId(wlc.getId());
					businessModel.setCompanyName(wlc.getCompanyName());
					businessModels.add(businessModel);
				}
			}
			Pagination<BusinessModel> resultData = new Pagination<BusinessModel>(results);
			resultData.setRecords(businessModels);;
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(resultData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}
	
	
	
	/**
	 * 上传文件业务处理
	 */
	@Override
	public void doUploadFile(HttpServletRequest request,InterfaceResponse rsp,BusinessParmModel model) {
		try {
//			String phone = request.getParameter("phone");
//			String token = request.getParameter("token");
//			String uploadFlag = request.getParameter("uploadFlag");
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			
			BusinessModel businessModel = new BusinessModel();
			String filePath = "";
			if(!StringUtils.isEmpty(model.getUploadFlag())) {
				//上传文件为图片，需要对图片进行压缩处理
				filePath = UploadHandle.uploadFileForImg(request);
			} else {
				//正常上传
				filePath = UploadHandle.uploadFile(request);
			}
			businessModel.setFilePath(filePath);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(businessModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}




	/**
	 * 获取物流列表
	 */
	@Override
	public Pagination<WuliuCompany> getWuliuList(BusinessParmModel model) {
		String hql = "from WuliuCompany";
		NamedQueryer queryer = new NamedQueryer();
		queryer.setHql(hql);
		return this.baseDaoSupport.find(queryer, model.getPageNo(), model.getPageSize(), WuliuCompany.class);
	}
	
	
}
