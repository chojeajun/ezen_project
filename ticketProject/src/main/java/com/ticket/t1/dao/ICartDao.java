package com.ticket.t1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICartDao {

	void notBuyList(HashMap<String, Object> paramMap1);

	void buyList(HashMap<String, Object> paramMap2);

	void select_Content_Loc_Seat_View(HashMap<String, Object> paramMap3);

	void select_Content_Loc_Seat_View_Buy(HashMap<String, Object> paramMap4);

	void getCnickName(HashMap<String, Object> paramMap5);

	void deleteCart(int parseInt);

	void orderCart(int parseInt);

}
