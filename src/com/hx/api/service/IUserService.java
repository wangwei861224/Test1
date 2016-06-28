package com.hx.api.service;

import javax.servlet.http.HttpServletRequest;

import com.hx.api.entity.Dealer;
import com.hx.api.entity.Valicode;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.model.VerificationCodeModel;

public interface IUserService {
	
	public void doGetValiCode(HttpServletRequest request, InterfaceResponse rsp, VerificationCodeModel codeModel);
	
	public void doLogin(HttpServletRequest request, InterfaceResponse rsp, VerificationCodeModel codeModel);
	
	public void doLogout(InterfaceResponse rsp, VerificationCodeModel codeModel);
	
	public Dealer getDealer(String phone, String token);
	
	public Dealer getDealer(String phone);
	
	public void updateDealer(Dealer dealer);
	
	public void addValicode(String phone, String code);
	
	public Valicode getValicode(String phone);
	
	public void updateValicode(Valicode valicode);
}
