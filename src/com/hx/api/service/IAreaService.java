package com.hx.api.service;

import com.hx.api.entity.Area;

public interface IAreaService {

	public String getAreaNameById(Integer areaId);
	
	public String getAreaText(String areaIds);
	
	public Area getAreaById(Integer id);
}
