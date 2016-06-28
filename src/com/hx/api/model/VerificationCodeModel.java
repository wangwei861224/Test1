package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class VerificationCodeModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//手机号
	@Expose
	private String phone;
	//token
	@Expose
	private String token;
	//验证码
	@Expose
	private String verificationCode;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
