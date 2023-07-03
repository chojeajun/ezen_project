package com.ticket.t1.controller;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
            ArrayList<RegisterTimeVO> regi = rs.getMyRegister(membervo);
            mav.addObject("register", regi);

            mav.setViewName("apply_register/register/registerTimeForm");
        }
        return mav;
    }
	
	
	//--------------------------------------------------------------------------------------------------------------
	
	@Autowired
	ApplyService as;
	
	
	
}
