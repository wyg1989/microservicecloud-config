package com.csii.springcloud.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TGoodInfoDao {
	 int updateAmount(Map map);
	 Integer queryAmout(Map map);
}
