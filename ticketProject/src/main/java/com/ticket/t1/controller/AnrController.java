package com.ticket.t1.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.RegisterTimeVO;
import com.ticket.t1.service.ApplyService;
import com.ticket.t1.service.OrderService;
import com.ticket.t1.service.RegisterService;

@Controller
public class AnrController {

	@Autowired
	RegisterService rs;
	
	@Autowired
	OrderService os;
	
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
	
	@RequestMapping("/categorySelect")
	public ModelAndView category_select( HttpServletRequest request, 
			@RequestParam("category") int category) {
				
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
	
	@RequestMapping("/applyCart")
	public ModelAndView apply_cart( HttpServletRequest request,
			@RequestParam("cseq") int cseq, @RequestParam("date") String ddate,
			@RequestParam("time") String time, @RequestParam("area") String area,
			@RequestParam("quantity") String quantity, 
			@RequestParam(value="mseq2", required=false) String smseq2,
			@RequestParam(value="cartseq", required=false) String scartseq) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session= request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			int mseq = Integer.parseInt(loginUser.get("MSEQ").toString()); 
			mav.setViewName("apply_register/apply/applyCart");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cseq", cseq);
			paramMap.put("ref_cursor", null);
			as.selectContentForLocNum(paramMap);
			ArrayList<HashMap<String, Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			int locationNum = Integer.parseInt(list.get(0).get("LOCATIONNUM").toString());
			String date = ddate.substring(0, 10);
			if(scartseq == null) {
				if(smseq2 != null) {
					int mseq2 = Integer.parseInt(smseq2);
					as.insertCart(mseq, cseq, date, time, area, mseq2, Integer.parseInt(quantity), locationNum);
				} else {
					System.out.println("time=================" + time);
					as.insertCart(mseq, cseq, date, time, area, Integer.parseInt(quantity), locationNum);
				}
			} else {
				int cartseq = Integer.parseInt(scartseq);
				as.hoouUpdateCart(cartseq, Integer.parseInt(smseq2));
			}
			mav.addObject("message", "장바구니에 담겼습니다. 즐거운 하루 되세요");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/applySelectCommissioner", method=RequestMethod.POST)
	public ModelAndView apply_select_commissioner(HttpServletRequest request,
			@RequestParam("cseq") int cseq, @RequestParam("area") String areas,
			@RequestParam("date") String dates, @RequestParam("time") String times,
			@RequestParam("quantity") int quantity/*, @RequestParam(value="cartseq", required=false) String cartseqStr*/	) {
		
		System.out.println("quantity : " + quantity);
		String[] area = areas.split(",");
		String [] time = times.split(",");
		String [] date = dates.split(",");
		System.out.println("area : " + area[0]);
		System.out.println("cseq : " + cseq);
		System.out.println("date : " + date[0]);
		System.out.println("time : " + time[0]);
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser == null) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cseq", cseq);
			paramMap.put("ref_cursor", null);
			as.selectFromContentByTitle(paramMap);
			ArrayList<HashMap<String, Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("detailList", list);
			System.out.println("detailList===============================" + list);
			String tDate = list.get(0).get("TDATETIME").toString().substring(0, 8);
			String tTimeStr = list.get(0).get("TDATETIME").toString().substring(8, 12);
			int tTime = Integer.parseInt(tTimeStr);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdfReal = new SimpleDateFormat("yyyy-MM-dd");
			
			Date formatDate;
			try {
				formatDate = (Date) sdf.parse(tDate);
				String parseTDate = sdfReal.format(formatDate);
				tTimeStr = tTimeStr.substring(0, 2) + ":" + tTimeStr.substring(2);
				System.out.println("티켓팅날짜:" + parseTDate+" 시간:" + tTimeStr);
				mav.addObject("tDateTime", parseTDate + "   " + tTimeStr);
			} catch(ParseException e) {
				e.printStackTrace();
			}
			
			HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("date", tDate);
			paramMap2.put("ref_cursor", null);
			as.getCommissioner(paramMap2);
			ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>) paramMap2.get("ref_cursor");
			
			HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
			for(int i = 0; i < list2.size(); i++) {
				paramMap3.put("tDate", tDate);
				System.out.println("startTime= " + Integer.parseInt(list2.get(i).get("STARTTIME").toString().replace(":", "")));
				paramMap3.put("startTime", Integer.parseInt(list2.get(i).get("STARTTIME").toString().replace(":", "")));
				paramMap3.put("tTime", tTime);
				System.out.println("tTime= " + tTime);
				System.out.println("endTime= " + Integer.parseInt(list2.get(i).get("ENDTIME").toString().replace(":", "")));
				paramMap3.put("endTime", Integer.parseInt(list2.get(i).get("ENDTIME").toString().replace(":", "")));
				paramMap3.put("mseq", Integer.parseInt(loginUser.get("MSEQ").toString()));
				paramMap3.put("ref_cursor", null);
				as.getCommissioner1(paramMap3);
			}
			list = (ArrayList<HashMap<String, Object>>) paramMap3.get("ref_cursor");
			if(list != null) {
				if(list.size() != 0) {
					for(int i = 0; i < list.size(); i++) {
						try {
							if(list.get(i).get("REGISTERDATE") != null) {
								formatDate = (Date) sdf.parse(list.get(i).get("REGISTERDATE").toString());
								String registerDate = sdfReal.format(formatDate);
								list.get(i).put("REGISTERDATE", registerDate);
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			}
			mav.addObject("comList", list);
			System.out.println("comList==========================" + list);
			
			
			HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("cseq", cseq);
			paramMap1.put("area", area[0]);
			paramMap1.put("ref_cursor", null);
			as.selectAreaPrice(paramMap1);
			list = (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
			mav.addObject("areaList", list);
			System.out.println("areaList========================================" + list);
			
			
//			System.out.println(cseq+" "+area+" "+quantity+" "+date+" " + time);
			String dateStr = date[0].substring(0, 10);
			System.out.println("date : " + dateStr);
			mav.addObject("date", dateStr);
			mav.addObject("time", time[0]);
			mav.addObject("quantity", quantity);
//			if(cartseqStr != null) { // 34,34 : For input string
//				int cartseq = Integer.parseInt(cartseqStr);
//				mav.addObject("cartseq", cartseq);
//				System.out.println("cartseq=========================================" + cartseq);
//			}
			mav.setViewName("apply_register/apply/applySelectCommissioner");
		}
		return mav;
		
	}
	
	@RequestMapping("apply")
	public ModelAndView apply( HttpServletRequest request,
			@RequestParam("cseq") int cseq, @RequestParam("date") String ddate,
			@RequestParam("time") String time, @RequestParam("area") String area,
			@RequestParam("quantity") int quantity, @RequestParam("mseq2") int mseq2) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			
			mav.setViewName("apply_register/apply/applyFinalPage");
			String date = ddate.substring(0,10).replace("-", "");
			System.out.println("cseq==" + cseq);
			System.out.println("date==" + date);
			System.out.println("time==" + time);
			System.out.println("area==" + area);
			System.out.println("quantity==" + quantity);
			System.out.println("mseq2==" + mseq2);
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cseq", cseq);
			paramMap.put("ref_cursor", null);
			as.selectContentByCseq(paramMap);
			ArrayList<HashMap<String, Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			System.out.println("<select * from content where cseq = p_cseq>======" + list);
			int locationNum = Integer.parseInt(list.get(0).get("LOCATIONNUM").toString());
			
			os.insertOrders(Integer.parseInt(loginUser.get("MSEQ").toString()),
					cseq, date, time, area, quantity, mseq2, locationNum);
			as.insertCart( Integer.parseInt(loginUser.get("MSEQ").toString()), cseq, date, time, area, mseq2, quantity, locationNum);
		}
		
		return mav;
	}
	
	
}
