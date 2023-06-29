package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.service.SuccessService;

@Controller
public class SuccessController {
	
	@Autowired
	SuccessService ss;
	
	@RequestMapping("/successView")
	public ModelAndView success_view( HttpServletRequest request,
			@RequestParam("susseq") int seq) {
		
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("susseq", seq);
		paramMap.put("ref_cursor", null);
		
		ss.getSuccessListBySusseq( paramMap );
		
		ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		mav.addObject("successVO", list.get(0));
		mav.setViewName("success/successView");
		
		return mav;
		
	}
}
