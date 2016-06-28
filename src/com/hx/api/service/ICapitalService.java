package com.hx.api.service;

import com.hx.api.dao.Pagination;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.DealerFundChange;
import com.hx.api.entity.TiXianList;
import com.hx.api.model.CapitalModel;
import com.hx.api.model.CapitalParmModel;
import com.hx.api.model.InterfaceResponse;

public interface ICapitalService {

	public void doGetWithdrawalsDataSum(InterfaceResponse rsp, CapitalParmModel model);
	
	public void getWithdrawalsDataSum(CapitalModel capitalModel,Dealer dealer);
	
	public void doAddWithdrawals(InterfaceResponse rsp, CapitalParmModel model);
	
	public void addWithdrawals(CapitalParmModel model, Dealer dealer);
	
	public void doGetBankInfo(InterfaceResponse rsp, CapitalParmModel model);
	
	public void doGetWithdrawalsList(InterfaceResponse rsp, CapitalParmModel model);
	
	public Pagination<TiXianList> getTiXianList(CapitalParmModel model, Dealer dealer);
	
	public void doGetFundChangeList(InterfaceResponse rsp, CapitalParmModel model);
	
	public Pagination<DealerFundChange> getFundChangeList(CapitalParmModel model, Dealer dealer);
}
