package com.csii.springcloud.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csii.springcloud.dao.DeptDao;
import com.csii.springcloud.dao.TGoodInfoDao;
import com.csii.springcloud.entities.Dept;
import com.csii.springcloud.entities.TGoodInfo;
import com.csii.springcloud.service.DeptService;
import com.csii.springcloud.service.TGoodInfoService;
@Service
public class TGoodInfoServiceImpl implements TGoodInfoService {
	@Autowired
	TGoodInfoDao dao;
	

	@Override
	public int updateByPrimaryKeySelective(TGoodInfo recode) {
		dao.updateByPrimary(recode);
		return 0;
	}

	@Override
	public int updateByPrimary(TGoodInfo recode) {
		dao.updateByPrimaryKeySelective(recode);
		return 0;
	}

	@Override
	public int updateAmount(Map map) {
		dao.updateAmount(map);
		return 0;
	}

}
