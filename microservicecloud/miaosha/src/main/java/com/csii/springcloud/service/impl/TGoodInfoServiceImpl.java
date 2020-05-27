package com.csii.springcloud.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csii.springcloud.dao.TGoodInfoDao;
import com.csii.springcloud.service.TGoodInfoService;
@Service
public class TGoodInfoServiceImpl implements TGoodInfoService {
	@Autowired
	TGoodInfoDao dao;
	@Override
	public int updateAmount(Map map) {
		
		return dao.updateAmount(map);
	}
	@Override
	public Integer queryAmout(Map map) {
		Integer amout = dao.queryAmout(map);
		return amout;
	}

}
