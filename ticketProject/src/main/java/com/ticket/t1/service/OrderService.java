package com.ticket.t1.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.ICartDao;
import com.ticket.t1.dao.IOrderDao;

@Service
public class OrderService {

	@Autowired
	IOrderDao odao;

	public void insertOrder(HashMap<String, Object> paramMap) {
		odao.insertOrder(paramMap);
		
	}
	
	public void getOrderView(HashMap<String, Object> paramMap) {
		odao.getOrderView(paramMap);
		
	}

	public void listOrderByOseq(HashMap<String, Object> paramMap) {
		odao.listOrderByOseq(paramMap);
		
		ArrayList<HashMap<String, Object>> list 
		= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		int totalPrice = 0;
		for(int i = 0; i < list.size(); i++) {
			totalPrice += Integer.parseInt(list.get(i).get("QUANTITY").toString()) 
					* Integer.parseInt(list.get(i).get("PRICE2").toString()); 
		}
		paramMap.put("totalPrice", totalPrice);
		
	}

	public void getOrderViewByMyMseq(HashMap<String, Object> paramMap) {
		odao.getOrderViewByMyMseq(paramMap);
		
	}

	public void getOrderList(HashMap<String, Object> paramMap1) {
		odao.getOrderList(paramMap1);
		
	}

	public void getAllOrderViewByMyMseq(HashMap<String, Object> list3) {
		odao.getAllOrderViewByMyseq(list3);
		
	}

	public void getOrderDetail(HashMap<String, Object> paramMap) {
		odao.getOrderDetail(paramMap);
		
	}

	public void insertOrders(int mseq, int cseq, String date, String time, String area, int quantity, int mseq2,
			int locationNum) {
		odao.insertOrders(mseq, cseq);
		HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("mseq", mseq);
		paramMap1.put("ref_cursor", null);
		odao.selectOrders(paramMap1);
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
		System.out.println("orders===========" + list);
		int oseq = Integer.parseInt(list.get(0).get("OSEQ").toString());
		int mseq1 = Integer.parseInt(list.get(0).get("MSEQ").toString());
		int cseq1 = Integer.parseInt(list.get(0).get("CSEQ").toString());
		String indate = list.get(0).get("INDATE").toString().substring(0, 10);
		odao.insertOrdersDetail(oseq, mseq1, cseq1, indate, date, time, area, mseq2, quantity, locationNum);
		
		
	}

	
	
	
}
