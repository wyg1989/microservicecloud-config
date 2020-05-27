package com.csii.springcloud.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.csii.springcloud.entities.TGoodInfo;

@Mapper
public interface TGoodInfoDao {

	 public int updateByPrimaryKeySelective(TGoodInfo recode);
	 public int updateByPrimary(TGoodInfo recode);
	 int updateAmount(Map map);
	 
	 
}
