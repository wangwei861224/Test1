package com.hx.api.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hx.api.common.web.CommonConfig;
import com.hx.api.dao.BaseDaoSupport;
import com.hx.api.dao.NamedQueryer;
import com.hx.api.dao.Pagination;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.DealerBankInfo;
import com.hx.api.entity.DealerFundChange;
import com.hx.api.entity.TiXianList;
import com.hx.api.model.CapitalModel;
import com.hx.api.model.CapitalParmModel;
import com.hx.api.model.CapitalSearchModel;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.service.ICapitalService;
import com.hx.api.service.IUserService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.utils.DateUtils;

@Service
@Transactional
public class CapitalServiceImpl implements ICapitalService {

	private Logger logger = LoggerFactory.getLogger(CapitalServiceImpl.class);

	@Autowired
	private BaseDaoSupport baseDaoSupport;
	@Autowired
	private IUserService userService;

	private static DecimalFormat df = new DecimalFormat("0.00");
	/**
	 * 获取提现首页数据汇总业务处理
	 */
	@Override
	public void doGetWithdrawalsDataSum(InterfaceResponse rsp, CapitalParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			CapitalModel capitalModel = new CapitalModel();
			this.getWithdrawalsDataSum(capitalModel, dealer);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(capitalModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}
	
	
	/**
	 * 添加提现操作业务处理
	 */
	@Override
	public void doAddWithdrawals(InterfaceResponse rsp, CapitalParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			this.addWithdrawals(model, dealer);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}


	/**
	 * 获取经销商绑定的银行卡信息业务处理
	 */
	@Override
	public void doGetBankInfo(InterfaceResponse rsp, CapitalParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			List<CapitalModel> capitalModels = new ArrayList<CapitalModel>();
			Set<DealerBankInfo> dealerBankInfos = dealer.getDealerBankInfo();
			if(dealerBankInfos != null && dealerBankInfos.size() > 0) {
				for(DealerBankInfo dbi : dealerBankInfos) {
					CapitalModel capitalModel = new CapitalModel();
					capitalModel.setBankName(dbi.getBankName());
					capitalModel.setBankPerson(dbi.getBankPerson());
					capitalModel.setCardNo(dbi.getBankNumber());
					capitalModels.add(capitalModel);
				}
			}
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(capitalModels);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}
	
	
	

	/**
	 * 获取经销商提现记录业务处理
	 */
	@Override
	public void doGetWithdrawalsList(InterfaceResponse rsp, CapitalParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			List<CapitalModel> capitalModels = new ArrayList<CapitalModel>();
			Pagination<TiXianList> result = this.getTiXianList(model, dealer);
			List<TiXianList> tiXianLists = result.getRecords();
			if(tiXianLists != null && tiXianLists.size() > 0) {
				for(TiXianList txl : tiXianLists) {
					CapitalModel entity = new CapitalModel();
					entity.setCreateTime(DateUtils.format(Long.valueOf(txl.getCreateTime()), CommonConfig.PATTERN));
					entity.setPrice(df.format(Double.parseDouble(txl.getPrice())));
					entity.setStatus(txl.getStatus());
					entity.setBankName(txl.getBankName());
					entity.setBankPerson(txl.getUserName());
					entity.setCardNo(txl.getCardNo());
					capitalModels.add(entity);
				}
			}
			Pagination<CapitalModel> resultData = new Pagination<CapitalModel>(result);
			resultData.setRecords(capitalModels);
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
	 * 获取经销商资金变动记录业务处理
	 */
	@Override
	public void doGetFundChangeList(InterfaceResponse rsp, CapitalParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			List<CapitalModel> capitalModels = new ArrayList<CapitalModel>();
			Pagination<DealerFundChange> result = this.getFundChangeList(model, dealer);
			List<DealerFundChange> dealerFundChanges = result.getRecords();
			if(dealerFundChanges != null && dealerFundChanges.size() > 0) {
				for(DealerFundChange dfc : dealerFundChanges) {
					CapitalModel entity = new CapitalModel();
					entity.setType(dfc.getType());
					entity.setCreateTime(DateUtils.format(Long.valueOf(dfc.getCreateTime()), CommonConfig.PATTERN));
					entity.setFcPrice(df.format(Double.parseDouble(dfc.getPrice())));
					entity.setBeforePrice(dfc.getBeforPrice());
					entity.setBalancePrice(dfc.getAfterPrice());
					capitalModels.add(entity);
				}
			}
			Pagination<CapitalModel> resultData = new Pagination<CapitalModel>(result);
			resultData.setRecords(capitalModels);
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
	 * 获取提现首页数据汇总
	 * @param capitalModel
	 */
	@Override
	public void getWithdrawalsDataSum(CapitalModel capitalModel,Dealer dealer) {
		//获取经销商的提交记录
		Set<TiXianList> tiXianLists = dealer.getTiXianLists();
		//获取经销商的资金变动记录
		Set<DealerFundChange> dealerFundChanges = dealer.getDealerFundChanges();
		
		String dealerBalance = StringUtils.isEmpty(dealer.getBalance()) ? "0.00" : dealer.getBalance();
		//经销商可用余额
		capitalModel.setBalance(df.format(Double.valueOf(dealerBalance)));
		
		Double withdrawPrice = 0.00;
		if(tiXianLists != null && tiXianLists.size() > 0) {
			for(TiXianList txl : tiXianLists) {
				if(txl.getStatus() == 2) {
					withdrawPrice = withdrawPrice + Double.parseDouble(txl.getPrice());
				}
			}
		}
		//经销商累计提款
		capitalModel.setWithdrawPrice(df.format(withdrawPrice));
		
		Double incomePrice = 0.00;
		if(dealerFundChanges != null && dealerFundChanges.size() > 0) {
			for(DealerFundChange dfc : dealerFundChanges) {
				if(dfc.getType() == 0) {
					incomePrice = incomePrice + Double.valueOf(dfc.getPrice());
				}
			}
		}
		//经销商累计收入
		capitalModel.setIncomePrice(df.format(incomePrice));
	}
	
	/**
	 * 添加提现记录
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addWithdrawals(CapitalParmModel model,Dealer dealer) {
		TiXianList entity = new TiXianList();
		entity.setDealer(dealer);
		entity.setPrice(df.format(Double.valueOf(model.getTiXianPrice())));
		entity.setStatus(0);
		entity.setCreateTime(String.valueOf(DateUtils.format(new Date())));
		entity.setBankName(model.getBankName());
		entity.setCardNo(model.getCardNo());
		entity.setUserName(model.getUserName());
		this.baseDaoSupport.insert(TiXianList.class, entity);
	}


	/**
	 * 获取经销商的提现记录
	 */
	@Override
	public Pagination<TiXianList> getTiXianList(CapitalParmModel model, Dealer dealer) {
		CapitalSearchModel searchModel = new CapitalSearchModel();
		searchModel.setTimeSection(model.getTimeSection());
		searchModel.setDealerId(dealer.getId());
		NamedQueryer queryer = searchModel.getTiXianHql();
		Pagination<TiXianList> tiXianLists = this.baseDaoSupport.find(queryer, model.getPageNo(), model.getPageSize(), TiXianList.class);
		return tiXianLists;
	}


	/**
	 * 获取经销商的资金变动记录
	 */
	@Override
	public Pagination<DealerFundChange> getFundChangeList(CapitalParmModel model, Dealer dealer) {
		CapitalSearchModel searchModel = new CapitalSearchModel();
		searchModel.setTimeSection(model.getTimeSection());
		searchModel.setDealerId(dealer.getId());
		NamedQueryer queryer = searchModel.getFundChangeHql();
		Pagination<DealerFundChange> dealerFundChanges = this.baseDaoSupport.find(queryer, model.getPageNo(), model.getPageSize(), DealerFundChange.class);
		return dealerFundChanges;
	}
	
	
	
}
