package com.ticket.t1.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.service.CartService;


@Controller
public class CartController {

	@Autowired
	CartService cs;


	@RequestMapping("/cartList")
	public ModelAndView cart_list(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			mav.setViewName("member/login");
		} else {
			
			// 사지 않은 카트 목록(cart)
			System.out.println("멤버번호는 " + loginUser.get("MSEQ"));
			HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("mseq", loginUser.get("MSEQ"));
			paramMap1.put("ref_cursor", null);
			cs.notBuyList(paramMap1);
			ArrayList<HashMap<String, Object>> list 
				= (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
			System.out.println("list사이즈는 " + list.size());
			mav.addObject("notBuy_cartList", list);

			// 산 카트 목록(cart)
			HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("mseq", loginUser.get("MSEQ"));
			paramMap2.put("ref_cursor", null);
			cs.buyList(paramMap2);
			ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>) paramMap2.get("ref_cursor");
			mav.addObject("buy_cartList", list2);

			// 사지 않은 카트 장소 시간 목록 (content_loc_seat_view) HashMap<String, Object>
			if (list.size() != 0) {
				
				HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
				ArrayList<HashMap<String, Object>> list3 = new ArrayList<HashMap<String, Object>>();

				for (int i = 0; i < list.size(); i++) {
					paramMap3.put("cseq", list.get(i).get("CSEQ"));
					paramMap3.put("area", list.get(i).get("AREA"));
					paramMap3.put("ref_cursor", null);
					cs.select_Content_Loc_Seat_View(paramMap3);

					list3.addAll((ArrayList<HashMap<String, Object>>) paramMap3.get("ref_cursor"));
				}

				mav.addObject("notBuy", list3);

			}

			// 산 카트 장소 시간 목록 (content_loc_seat_view)
			if (list2.size() != 0) {

				HashMap<String, Object> paramMap4 = new HashMap<String, Object>();
				ArrayList<HashMap<String, Object>> list4 = new ArrayList<HashMap<String, Object>>();
				
				System.out.println("산 목록 " + list2.get(0).get("CSEQ"));
				System.out.println("산 목록 " + list2.get(0).get("AREA"));
				System.out.println("산 사이즈 " + list2.size());
				
				
				
				for (int i = 0; i < list2.size(); i++) {
					paramMap4.put("cseq", list2.get(i).get("CSEQ"));
					paramMap4.put("area", list2.get(i).get("AREA"));
					paramMap4.put("ref_cursor", null);
					cs.sclsvb(paramMap4);
					
					list4.addAll((ArrayList<HashMap<String, Object>>) paramMap4.get("ref_cursor"));
				}
				System.out.println(list4.size());
				System.out.println(list4);
				mav.addObject("buy", list4);

			}

			// member_grade_view_vo 대리인 cnickname
			if (list.size() != 0) {

				HashMap<String, Object> paramMap5 = new HashMap<String, Object>();
				ArrayList<HashMap<String, Object>> list5 = new ArrayList<HashMap<String, Object>>();

				String [] checkboxes = new String[list.size()];
				
				for (int i = 0; i < list.size(); i++) {
					paramMap5.put("mseq2", list.get(i).get("MSEQ2"));
					System.out.println("mseq2=================================" + list.get(i).get("MSEQ2"));
					paramMap5.put("ref_cursor", null);
					cs.getCnickName(paramMap5);

					list5.addAll((ArrayList<HashMap<String, Object>>) paramMap5.get("ref_cursor"));
					System.out.println("list5========================================" + list5);
					if(list5.get(i).get("CNICKNAME").equals("선택 안함")) {
						checkboxes[i] = "No";
						System.out.println("여기왔습니다");
					}else {
						System.out.println("대리인 선택함");
						checkboxes[i] = "Yes";
						
					}
				}
				mav.addObject("checkbox", checkboxes);
				mav.addObject("defuty", list5);
			}

			mav.setViewName("cart/cartList");
		}

		return mav;

	}
	
	/*
	 * @RequestMapping("/orderInsert") public String
	 * order_insert(@RequestParam("cartseq") String cartseq) {
	 * 
	 * // System.out.println(cartseq.split(",")); // String[] arr =
	 * cartseq.split(","); // System.out.println(arr[0] + " " +arr[1]);
	 * 
	 * for(int i = 0; i < arr.length; i++) { cs.orderCart(Integer.parseInt(arr[i]));
	 * }
	 * 
	 * return "redirect:/cartList"; }
	 */
	
	@RequestMapping("/orderInsert")
	public String order_insert(@RequestParam("cartseq") String[] cartseq, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if( loginUser == null) {
			return "member/login";
		} else {
			for(int i = 0; i < cartseq.length; i++) {
				cs.orderCart(Integer.parseInt(cartseq[i]));
			}
		}
		
		
		
		return "redirect:/cartList";
		
	}
	
	@RequestMapping("/cartDelete")
	public String cart_delete(@RequestParam("cartseq") String[] cartseq, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if( loginUser == null) {
			return "member/login";
		} else {
			for(int i = 0; i < cartseq.length; i++) {
				cs.deleteCart(Integer.parseInt(cartseq[i]));
			}
		}
		
		
		
		return "redirect:/cartList";
		
	}
}
