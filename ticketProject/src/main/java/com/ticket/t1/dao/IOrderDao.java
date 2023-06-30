package com.ticket.t1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOrderDao {

	void insertOrder(HashMap<String, Object> paramMap);
	void getOrderView(HashMap<String, Object> paramMap);
	void listOrderByOseq(HashMap<String, Object> paramMap);


}
