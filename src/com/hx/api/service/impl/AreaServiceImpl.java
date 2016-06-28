package com.hx.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.api.dao.BaseDaoSupport;
import com.hx.api.entity.Area;
import com.hx.api.service.IAreaService;

@Service
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private BaseDaoSupport baseDaoSupport;

	/**
	 * 根据地区ID获取地区文本信息
	 */
	@Override
	public String getAreaNameById(Integer areaId) {
		StringBuffer sb = new StringBuffer("");
		Area area = this.getAreaById(areaId);
		
		Integer parentid = area.getParentId();
		if(parentid != 0) {
			Area parentArea = this.getAreaById(parentid);
			
			sb.append(parentArea.getAreaName());
			sb.append("-");
		}
		sb.append(area.getAreaName());
		return sb.toString();
	}

	/**
	 * 获取地区文本信息
	 * @param areaId ***,***,***
	 */
	@Override
	public String getAreaText(String areaId) {
		StringBuffer text = new StringBuffer("");
		String[] areaIds = areaId.split(",");
		for(int i = 0; i < areaIds.length; i++) {
			if(null != areaIds[i] && !"".equals(areaIds[i])) {
				text.append(this.getAreaNameById(Integer.parseInt(areaIds[i])));
				
				if(i != areaIds.length-1) {
					text.append(",");
				}
			}
		}
		return text.toString();
	}

	/**
	 * 获取地区实例
	 */
	@Override
	public Area getAreaById(Integer id) {
		String hql = "from Area where id = "+id+"";
		List<Area> areaList = this.baseDaoSupport.find(Area.class, hql);
		if(areaList != null && areaList.size() > 0) {
			return areaList.get(0);
		}
		return null;
	}
	
	
}
