package com.csii.springcloud.service;

import java.util.Map;

import com.csii.springcloud.entities.TGoodInfo;

public interface TGoodInfoService {
	 public int updateByPrimaryKeySelective(TGoodInfo recode);
	 public int updateByPrimary(TGoodInfo recode);
	 int updateAmount(Map map);
}
