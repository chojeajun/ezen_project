package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService os;
	
//	@RequestMapping(value="/orderInsert")
//	public String orderInsert( HttpServletRequest request ) {
//		
//		int oseq = 0;
//		HttpSession session = request.getSession();
//		HashMap<String, Object> loginUser 
//			= (HashMap<String, Object>)session.getAttribute("loginUser");
//		if( loginUser == null ) {
//			return "member/login";
//		}else {
//			//카트에서 장바구니 목록 조회
//			//상품목록을 orders 와 order_detail 에 저장
//			//카트에서 주문한 목록을 삭제
//			//oseq 주문번호를 리턴
//			
//			HashMap<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", loginUser.get("ID") );
//			paramMap.put("oseq", 0 );
//			
//			os.insertOrder( paramMap );
//			oseq = Integer.parseInt(paramMap.get("oseq").toString());
//			
//		}
//		return "redirect:/orderView?oseq="+oseq;
//	}
	
	@RequestMapping("/OrderView")
	public ModelAndView success_view( HttpServletRequest request,
			@RequestParam("oseq") int oseq) {
		
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("oseq", oseq);
		paramMap.put("ref_cursor", null);
		
		os.getOrderView( paramMap );
		
		ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		mav.addObject("orderVO", list.get(0));
		mav.setViewName("order/orderView");
		
		return mav;
		
	}
	
	@RequestMapping(value="/orderDetail")  
	public ModelAndView orderDetail( HttpServletRequest request,
			@RequestParam("oseq") int oseq ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String , Object> paramMap = new HashMap<String , Object>();
			paramMap.put("oseq", oseq);
			paramMap.put("ref_cursor", null);
			os.listOrderByOseq(paramMap);
				
			ArrayList<HashMap<String,Object>> orderListByOseq 
				= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
				
			mav.addObject("totalPrice", paramMap.get("totalPrice"));
			mav.addObject("orderList", orderListByOseq);
			mav.addObject("orderDetail", orderListByOseq.get(0) );  
			mav.setViewName("order/orderDetail");
		}
		return mav;
	}
	
	@RequestMapping("/myOrderView")
	public ModelAndView my_order_view( HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if( loginUser == null ) {
			mav.setViewName("member/login");
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mseq", loginUser.get("MSEQ"));
			paramMap.put("ref_cursor", null);
			
			os.getOrderViewByMyMseq(paramMap);
			
			ArrayList<HashMap<String, Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if(list.size() != 0) {
				
				int [] TotalPrice = new int[list.size()];
				String [] Title = new String[list.size()];
				int totalPrice = 0;
				String title = null;
				
				for(int i = 0; i<list.size(); i++) {
					HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
					System.out.println(Integer.parseInt(list.get(i).get("OSEQ").toString()));
					paramMap1.put("oseq", Integer.parseInt(list.get(i).get("OSEQ").toString()));
					paramMap1.put("ref_cursor", null);
					os.getOrderList(paramMap1);
					ArrayList<HashMap<String, Object>> list1
						= (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
					for(int j=0; j<list1.size(); j++) {
						totalPrice += Integer.parseInt(list1.get(j).get("CONTENT_PRICE").toString()) 
								* Integer.parseInt(list1.get(j).get("QUANTITY").toString()) 
								+ Integer.parseInt(list1.get(j).get("COM_PRICE").toString());
						System.out.println(totalPrice);
					}
					TotalPrice[i] = totalPrice;
					
					if( list1.size()>1) {
						title = list1.get(i).get("TITLE").toString() + " 외 " + (list1.size()-1) + "건";
					}else {
						title = list1.get(i).get("TITLE").toString();
					}
					
					Title[i] = title;
					mav.addObject("orderList", list1);
					
				}
				mav.addObject("totalPrice", TotalPrice);
	    		mav.addObject("title", Title);
	    		mav.setViewName("order/orderView");
	    		
			}
			
		}
		return mav;
		
	}
	
	
	
}
