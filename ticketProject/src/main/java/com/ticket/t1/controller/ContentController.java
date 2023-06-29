package com.ticket.t1.controller;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.service.ContentService;

@Controller
public class ContentController {
	
	@Autowired
	ContentService cs;
	
	@RequestMapping("/")
	public String root() {
		return "start";
	}
	
	@RequestMapping("/webmain")
	public ModelAndView webmain() {
		
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor1", null); // 메인 베너용
		paramMap.put("ref_cursor2", null); // 베스트용
		paramMap.put("ref_cursor3", null); // 새로운 항목용
		paramMap.put("ref_cursor4", null); // 성공 내역용
		
		cs.getBestNewSuccessBannerList( paramMap );
		
		ArrayList<HashMap<String, Object>> list1
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		ArrayList<HashMap<String, Object>> list2
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		ArrayList<HashMap<String, Object>> list3
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor3");
		ArrayList<HashMap<String, Object>> list4
		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor4");
		
		mav.addObject("bannerList", list1);
		mav.addObject("bestList", list2);
		mav.addObject("newList", list3);
		mav.addObject("successList", list4);
		//mav.addObject("size", list3.size());
		
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/contentDetail")
	public ModelAndView content_detail(@RequestParam("cseq") int cseq) {
//		System.out.println("content_detail 메서드");
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("cseq", cseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		cs.getContentTimeList( paramMap );
		
		ArrayList<HashMap<String, Object>> list1
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		ArrayList<HashMap<String, Object>> list2
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		
//		System.out.println(list2.get(0).get("CONTENTDATE"));
//		System.out.println(list2.get(0).get("CONTENTTIME"));
		
		mav.addObject("content", list1);
		mav.addObject("list", list2.get(0));
		mav.setViewName("content/contentDetail");
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------
	
	@RequestMapping("/mobilemain")
	public ModelAndView mobilemain() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("mobileindex");
		return mav;
	}
	
	/*
	 * @RequestMapping("/catagory") public ModelAndView catagory(
	 * 
	 * @RequestParam("kind") String kind, HttpServletRequest request) {
	 * 
	 * ModelAndView mav = new ModelAndView(); HashMap<String, Object> paramMap = new
	 * HashMap<String, Object>(); paramMap.put("kind", kind);
	 * paramMap.put("ref_cursor", null);
	 * 
	 * ps.getKindList( paramMap );
	 * 
	 * ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String,
	 * Object>>) paramMap.get("ref_cursor"); mav.addObject("productKindList", list);
	 * mav.setViewName("product/productKind");
	 * 
	 * return mav;
	 * 
	 * }
	 */

}
