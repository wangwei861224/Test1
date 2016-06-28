package com.hx.api.service;

import javax.servlet.http.HttpServletRequest;

import com.hx.api.dao.Pagination;
import com.hx.api.entity.WuliuCompany;
import com.hx.api.model.BusinessParmModel;
import com.hx.api.model.InterfaceResponse;

public interface IBusinessService {

	public void doGetWuLiuList(InterfaceResponse rsp, BusinessParmModel model);
	
	public Pagination<WuliuCompany> getWuliuList(BusinessParmModel model);
	
	public void doUploadFile(HttpServletRequest request,InterfaceResponse rsp, BusinessParmModel model);
}
