package com.ticket.t1.controller;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticket.t1.dto.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.ticket.t1.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;

	@RequestMapping(value="/loginForm")
	public String loginForm() {
		return "member/login";
	}
	
			// jsp파일의 form action = login 을 타고 이리 옴  
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(
		// dto 라는 이름으로 객체를 받겠다 , 밸리데이션 membervo 진행하겠다 제약조건이름을 result  
			@ModelAttribute("dto") @Valid MemberVO membervo,	BindingResult result,
			HttpServletRequest request,	Model model	) {
		
		String url="member/login.jsp";
		if( result.getFieldError("id") != null )
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage() );
		else if( result.getFieldError("pwd") != null )
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", membervo.getId() );
			paramMap.put("ref_cursor", null);
			ms.getMember(paramMap);
			
			ArrayList< HashMap<String,Object> > list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if( list==null || list.size() == 0 ) {
				model.addAttribute("message" , "아이디가 없습니다");
				return "member/login";
			}
			HashMap<String, Object> mvo = list.get(0);
			System.out.println("존재하는 회원의 아이디 갯수는 " + list.size() + "개 입니다");
			if( mvo.get("pwd") == null )
				model.addAttribute("message" , "관리자에게 문의하세요 문의 ㄱ?");
			else if( !mvo.get("pwd").equals( membervo.getPwd() ) )
				model.addAttribute("message" , "비밀번호 안맞습니다 확인 ㄱ?");
			else  if( mvo.get("pwd").equals( membervo.getPwd() ) ) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				url = "redirect:/";
			}
		}
		return url;
	}
	
	
	
	
	
	
	
	

}
