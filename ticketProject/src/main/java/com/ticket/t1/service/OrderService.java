package com.ticket.t1.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		for( HashMap<String, Object> ovo : list ) {
			totalPrice += Integer.parseInt( ovo.get("QUANTITY").toString() ) 
						* Integer.parseInt( ovo.get("PRICE2").toString() );
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

	
	
	
}
