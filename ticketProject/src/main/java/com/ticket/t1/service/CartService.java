package com.ticket.t1.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.ICartDao;

@Service
public class CartService {

	@Autowired
	ICartDao cdao;

	public void notBuyList(HashMap<String, Object> paramMap1) {
		cdao.notBuyList(paramMap1);
		
	}

	public void buyList(HashMap<String, Object> paramMap2) {
		cdao.buyList(paramMap2);
		
	}

	public void select_Content_Loc_Seat_View(HashMap<String, Object> paramMap3) {
		cdao.select_Content_Loc_Seat_View(paramMap3);
		
	}

	public void sclsvb(HashMap<String, Object> paramMap4) {
		cdao.sclsvb(paramMap4);
		
	}

	public void getCnickName(HashMap<String, Object> paramMap5) {
		cdao.getCnickName(paramMap5);
		
	}

	public void deleteCart(int parseInt) {
		cdao.deleteCart(parseInt);
		
	}

	public void orderCart(int parseInt) {
		cdao.orderCart(parseInt);
		
	}
	
}
