package com.hx.api.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class BusinessModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 物流
	 */
	@Expose
	private Integer companyId;
	@Expose
	private String companyName;
	
	/**
	 * 
	 * 上传文件的路径
	 */
	@Expose
	private String filePath;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
