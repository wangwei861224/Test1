package com.hx.api.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hx.api.common.utils.Des3;
import com.hx.api.common.web.CommonConfig;
import com.hx.api.dao.BaseDaoSupport;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.Valicode;
import com.hx.api.model.DealerModel;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.model.VerificationCodeModel;
import com.hx.api.service.IAreaService;
import com.hx.api.service.IUserService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.utils.CommonUtil;
import com.hx.api.utils.DateUtils;
import com.hx.api.utils.SMSUtil;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private BaseDaoSupport baseDaoSupport;

	@Autowired
	private IAreaService areaService;

	
	
	/**
	 * 获取验证码业务处理
	 * @throws IOException 
	 */
	@Override
	public void doGetValiCode(HttpServletRequest request, InterfaceResponse rsp, VerificationCodeModel codeModel) {
		try {
			//对参数解密
//			codeModel.setPhone(Des3.decode(codeModel.getPhone()));
//			codeModel.setToken(Des3.decode(codeModel.getToken()));
			//判断是否存在该经销商
			Dealer dealer = this.getDealer(codeModel.getPhone());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_EXIST);
				return;
			}
			//获取6位随机验证码
			String valiCode = CommonUtil.getValiCode();
			codeModel.setVerificationCode(valiCode);
			
			//将验证码存放在数据库中
			this.addValicode(codeModel.getPhone(), valiCode);
			//发送验证码
			SMSUtil.sendShortMsg(codeModel.getPhone(), "@1@="+valiCode, CommonConfig.TEMP_ID);
			
			rsp.setStateCode(InterfaceResponse.SUCCESS);
//			rsp.setData(codeModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}
	

	/**
	 * 经销商登录业务处理
	 */
	@Override
	public void doLogin(HttpServletRequest request, InterfaceResponse rsp, VerificationCodeModel codeModel) {
		try {
			//判断是否存在该经销商
			Dealer dealer = this.getDealer(codeModel.getPhone());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_EXIST);
				return;
			}
			//匹配验证码
			Valicode valiCode = this.getValicode(codeModel.getPhone());
			if(valiCode.getValicode().equals(codeModel.getVerificationCode())) {
				//生成新的token
				dealer.setToken(CommonUtil.generateToken());
				this.updateDealer(dealer);
				DealerModel model = new DealerModel();
				
				//属性拷贝
				BeanUtils.copyProperties(dealer, model);
				if(null != model.getAreaId() && !"".equals(model.getAreaId())) {
					model.setAreaText(this.areaService.getAreaText(model.getAreaId()));
				}
				model.setRegTime(DateUtils.format(Long.valueOf(model.getRegTime()), CommonConfig.PATTERN));
				rsp.setStateCode(InterfaceResponse.SUCCESS);
				rsp.setData(model);
			} else {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_VALI_CODE_ERROE);
				return;
			}
			//销毁验证码
			this.updateValicode(valiCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}


	/**
	 * 经销商登出业务处理
	 */
	@Override
	public void doLogout(InterfaceResponse rsp, VerificationCodeModel codeModel) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.getDealer(codeModel.getPhone(), codeModel.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			//生成新的token
			dealer.setToken(CommonUtil.generateToken());
			this.updateDealer(dealer);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
            rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}


	/**
	 * 根据手机号获取经销商实例
	 */
	@Override
	public Dealer getDealer(String phone) {
		String hql = "from Dealer where frozen = 0 and mobile = '"+phone+"'";
		List<Dealer> dealers = this.baseDaoSupport.find(Dealer.class, hql);
		if(dealers != null && dealers.size() > 0) {
			return dealers.get(0);
		}
		return null;
	}

	/**
	 * 更新经销商
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateDealer(Dealer dealer) {
		this.baseDaoSupport.update(dealer);
	}


	/**
	 * 根据手机号和token对经销商进行验证
	 */
	@Override
	public Dealer getDealer(String phone, String token) {
		String hql = "from Dealer where frozen = 0 and mobile = '"+phone+"' and token = '"+token+"'";
		List<Dealer> dealers = this.baseDaoSupport.find(Dealer.class, hql);
		if(dealers != null && dealers.size() > 0) {
			return dealers.get(0);
		}
		return null;
	}
	


	/**
	 * 将生成的验证码存入数据库
	 * @param phone
	 * @param valiCode
	 */
	 @Transactional(propagation=Propagation.REQUIRED)
	 public void addValicode(String phone, String code) {
		Valicode valicode = new Valicode();
		valicode.setMobile(phone);
		valicode.setValicode(code);
		valicode.setCreateTime(String.valueOf(DateUtils.format(new Date())));
		valicode.setState(0);
		this.baseDaoSupport.insert(Valicode.class, valicode);
	 }


	 /**
	  * 从数据库中取得验证码
	  */
	@Override
	public Valicode getValicode(String phone) {
		String hql = "from Valicode where mobile = '"+phone+"' and state = 0 order by id desc";
		List<Valicode> valicodes = this.baseDaoSupport.find(Valicode.class, hql);
		if(valicodes != null && valicodes.size() > 0) {
			return valicodes.get(0);
		}
		return null;
	}


	/**
	 * 销毁验证码
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateValicode(Valicode valicode) {
		valicode.setState(1);
		this.baseDaoSupport.update(valicode);
	}
	 
	 
}
