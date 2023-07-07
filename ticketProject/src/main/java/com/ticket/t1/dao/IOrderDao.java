package com.ticket.t1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOrderDao {

	void insertOrder(HashMap<String, Object> paramMap);
	void getOrderView(HashMap<String, Object> paramMap);
	void listOrderByOseq(HashMap<String, Object> paramMap);
	void getOrderViewByMyMseq(HashMap<String, Object> paramMap);
	void getOrderList(HashMap<String, Object> paramMap1);
	void getAllOrderViewByMyseq(HashMap<String, Object> list3);
	void getOrderDetail(HashMap<String, Object> paramMap);
	void insertOrders(int mseq, int cseq);
	void insertOrdersDetail(int oseq, int mseq1, int cseq1, String indate, String date, String time, String area,
			int mseq2, int quantity, int locationNum);
	void selectOrders(HashMap<String, Object> paramMap1);


}
