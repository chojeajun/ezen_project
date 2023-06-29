package com.ticket.t1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IContentDao {

	void getBestNewSuccessBannerList(HashMap<String, Object> paramMap);

}
