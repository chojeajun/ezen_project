package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;

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

		cs.getBestNewSuccessBannerList(paramMap);

		ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		ArrayList<HashMap<String, Object>> list3 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor3");
		ArrayList<HashMap<String, Object>> list4 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor4");
		
		mav.addObject("bannerList", list1);
		mav.addObject("bestList", list2);
		mav.addObject("newList", list3);
		mav.addObject("successList", list4);
		// mav.addObject("size", list3.size());

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

		cs.getContentTimeList(paramMap);

		ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");

//		System.out.println(list2.get(0).get("CONTENTDATE"));
//		System.out.println(list2.get(0).get("CONTENTTIME"));

		String contentdate = list2.get(0).get("CONTENTDATE").toString().substring(0, 10);
		mav.addObject("content", list1);
		mav.addObject("contentdate", contentdate);
		mav.addObject("contenttime", list2.get(0).get("CONTENTTIME"));
//		mav.addObject("list", list2.get(0));

		mav.setViewName("content/contentDetail");

		return mav;
	}

	@RequestMapping("/category")
	public ModelAndView category(@RequestParam("kind") int kind) {

		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		System.out.println("kind = " + kind);
		if (kind == 0) {
			paramMap.put("ref_cursor1", null); // 콘서트
			paramMap.put("ref_cursor2", null); // 뮤지컬
			paramMap.put("ref_cursor3", null); // 스포츠
			paramMap.put("ref_cursor4", null); // 페스티벌
			paramMap.put("ref_cursor5", null); // 전시/행사
			cs.getAllContent(paramMap);

			ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
			ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
			ArrayList<HashMap<String, Object>> list3 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor3");
			ArrayList<HashMap<String, Object>> list4 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor4");
			ArrayList<HashMap<String, Object>> list5 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor5");

			mav.addObject("concert", list1);
			mav.addObject("musical", list2);
			mav.addObject("sports", list3);
			mav.addObject("festival", list4);
			mav.addObject("exhibition", list5);

			mav.setViewName("content/content");

		} else if (kind == 1) {
			paramMap.put("ref_cursor", null);
			cs.getConcert(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("concert", list);
			mav.setViewName("content/concert");
		} else if (kind == 2) {
			paramMap.put("ref_cursor", null);
			cs.getMusical(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("musical", list);
			mav.setViewName("content/musical");
		} else if (kind == 3) {
			paramMap.put("ref_cursor", null);
			cs.getSports(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("sports", list);
			mav.setViewName("content/sports");
		} else if (kind == 4) {
			paramMap.put("ref_cursor", null);
			cs.getFestival(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("festival", list);
			mav.setViewName("content/festival");
		} else {
			paramMap.put("ref_cursor", null);
			cs.getExhibition(paramMap);
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("exhibition", list);
			mav.setViewName("content/exhibition");
		}

		return mav;
	}

	// -----------------------------------------------------------------------------------

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
