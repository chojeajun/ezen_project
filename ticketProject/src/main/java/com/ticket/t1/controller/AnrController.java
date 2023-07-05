package com.ticket.t1.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.Content_Time_View_VO;
import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.RegisterTimeVO;
import com.ticket.t1.service.ApplyService;
import com.ticket.t1.service.RegisterService;

@Controller
public class AnrController {

	@Autowired
	RegisterService rs;
	
	@RequestMapping("/applyAndRegister")
	public ModelAndView applyAndRegister(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			mav.setViewName("member/login");
		} else {
			mav.setViewName("apply_register/apply_Or_Register");
		}
		return mav;
	}
	
	@RequestMapping("/registerForm")
	public ModelAndView registerForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			mav.setViewName("member/login");
		} else {
			mav.setViewName("apply_register/register/registerForm");
		}
		return mav;
	}
	
	
	@RequestMapping("/registerTimeForm")
	public ModelAndView registerTimeForm(HttpServletRequest request, @ModelAttribute("dto") MemberVO membervo) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		System.out.println("@@@@@@@@@@@@" + loginUser.get("MSEQ"));
		if (loginUser == null) {
			mav.setViewName("member/login");
		} else {
//			int mseq = loginUser.get(Integer.parseInt("MSEQ").toString());
			int mseq = Integer.parseInt(loginUser.get("MSEQ").toString());
			System.out.println("받아와진 mseq ==========" + mseq);
            String[] date = request.getParameterValues("date");
            String[] starttime = request.getParameterValues("starttime");
            String[] endtime = request.getParameterValues("endtime");

            for (int i = 0; i < date.length -1; i++) {
                rs.insertRegisterTime(mseq, date[i], starttime[i], endtime[i]);
            }
            ArrayList<RegisterTimeVO> regi = rs.getMyRegister(mseq);
            System.out.println("$$$$$" + regi.size());
            mav.addObject("register", regi);

            mav.setViewName("apply_register/register/registerTimeForm");
        }
        return mav;
    }
	
	
	//--------------------------------------------------------------------------------------------------------------
	
	@Autowired
	ApplyService as;
	
	@RequestMapping(value="/categorySelect")
	public ModelAndView category_select( HttpServletRequest request, 
			@RequestParam(value="category") int category) {
				
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		System.out.println("카테고리 = " + category);
		
		if(loginUser == null) {
			mav.setViewName("member/login");
		}else {
			if(category == 0) { // 카테고리가 정해지지 않았을 경우 여기로 이동
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("category", category);
				paramMap.put("ref_cursor", null);
				as.selectContentAll(paramMap);
				ArrayList<HashMap<String, Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
				mav.addObject("contentList", list);
				mav.setViewName("apply_register/apply/applyForm");
			}else { // 카테고리가 정해질 경우 여기로 이동
				
				System.out.println("카테고리가 정해졌습니다"); 
				HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
				paramMap1.put("category", category);
				paramMap1.put("ref_cursor", null);
				as.selectContent(paramMap1);
				ArrayList<HashMap<String, Object>> list1
				= (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
				mav.addObject("contentList", list1);
				mav.setViewName("apply_register/apply/applyForm");
			}
			mav.addObject("category", category);
		}
		
		return mav;
		
	}
	
	@RequestMapping("/applyContentSelect")
	public ModelAndView apply_content_select( HttpServletRequest request,
		@RequestParam(value="contentDate", required=false) String contentdate,
		@RequestParam("cseq") int cseq,
		@RequestParam("category") int category,
		@RequestParam("locationNum") int locationNum) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			mav.setViewName("member/login");
		}else {
			
			if(contentdate != null) {
				String contentDate = contentdate.substring(0, 10);
				contentDate = contentDate.replace("-", "");
				
				System.out.println("contentDate:" + contentDate);
				System.out.println("category:" + category);
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
				if(category == 0) {
					paramMap.put("category", category);
					paramMap.put("ref_cursor", null);
					as.selectContentAll(paramMap);
					
					list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
					mav.addObject("contentList", list);
				} else {
					paramMap = new HashMap<String, Object>();
					paramMap.put("category", category);
					paramMap.put("ref_cursor", null);
					as.selectContent(paramMap);
					
					list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
					mav.addObject("contentList", list);
				}
				
				HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
				paramMap1.put("cseq", cseq);
				paramMap1.put("ref_cursor", null);
				as.selectFromContentByTitle(paramMap1);
				list = (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
				mav.addObject("contentTableList", list);
				
				HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
				paramMap3.put("cseq", cseq);
				paramMap3.put("ref_cursor", null);
				paramMap3.put("ref_cursor1", null);
				as.selectFromContentTimeByTitle(paramMap3);
				list = (ArrayList<HashMap<String, Object>>) paramMap3.get("ref_cursor");
				mav.addObject("contentDateList", list);
				list = (ArrayList<HashMap<String, Object>>) paramMap3.get("ref_cursor1");
				mav.addObject("locationNum", Integer.parseInt(list.get(0).get("LOCATIONNUM").toString()));
				
				as.selectFromLocationViewByTitle(paramMap1);
				list = (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
				mav.addObject("contentLocationList", list);
				
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("locationNum", locationNum);
				paramMap2.put("ref_cursor", null);
				as.selectFromContentAreaByTitle(paramMap2);
				list = (ArrayList<HashMap<String, Object>>) paramMap2.get("ref_cursor");
				mav.addObject("contentAreaList", list);
				
				HashMap<String, Object> paramMap4 = new HashMap<String, Object>();
				paramMap4.put("cseq", cseq);
				paramMap4.put("contentDate", contentDate);
				paramMap4.put("ref_cursor", null);
				as.selectTimeByDate(paramMap4);
				list = (ArrayList<HashMap<String, Object>>) paramMap4.get("ref_cursor");
				System.out.println(list);
				mav.addObject("contentTimeList", list);
				
				
//				List<Content_Time_View_VO> list1 = as.selectTimeByDate(cseq, contentDate);
//				System.out.println("list1:"+list1);
//				mav.addObject("contentTimeList", list1);
				
				
			}else {
				ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
				if(category == 0) {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("category", category);
					paramMap.put("ref_cursor", null);
					as.selectContentAll(paramMap);
					
					list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
					mav.addObject("contentList", list);
				} else {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("category", category);
					paramMap.put("ref_cursor", null);
					as.selectContent(paramMap);
					
					list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
					mav.addObject("contentList", list);
				}
				
				
				HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
				paramMap1.put("cseq", cseq);
				paramMap1.put("ref_cursor", null);
				as.selectFromContentByTitle(paramMap1);
				
				list = (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
				mav.addObject("contentTableList", list);
				
				HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
				paramMap3.put("cseq", cseq);
				paramMap3.put("ref_cursor", null);
				paramMap3.put("ref_cursor1", null);
				as.selectFromContentTimeByTitle(paramMap3);
				list = (ArrayList<HashMap<String, Object>>) paramMap3.get("ref_cursor");
				mav.addObject("contentDateList", list);
				list = (ArrayList<HashMap<String, Object>>) paramMap3.get("ref_cursor1");
				mav.addObject("locationNum", Integer.parseInt(list.get(0).get("LOCATIONNUM").toString()));
				
				as.selectFromLocationViewByTitle(paramMap1);
				list = (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
				mav.addObject("contentLocationList", list);
				
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("locationNum", locationNum);
				paramMap2.put("ref_cursor", null);
				as.selectFromContentAreaByTitle(paramMap2);
				
				list = (ArrayList<HashMap<String, Object>>) paramMap2.get("ref_cursor");
				mav.addObject("contentAreaList", list);
			}
			
			mav.addObject("category", category);
			mav.setViewName("apply_register/apply/applyForm");
		}
		
		
		
		
		
		return mav;
	}
	
	
	
}
