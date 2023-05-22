package com.ezen.ticket.controller.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.dao.MemberDao;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.controller.action.Action;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(id);
		
		String url = "member/login.jsp";
		
		if( mvo == null)
			request.setAttribute("message", "없는 아이디입니다");
		else if( mvo.getUseyn().equals("Y")) request.setAttribute("message",
		  "회원 가입 후 탈퇴 이력이 있음. 관리자에게 문의하시오");
		else if( mvo.getPwd()==null)
			request.setAttribute("message", "DB오류. 관리자에게 문의하시오");
		else if( !mvo.getPwd().equals(pwd))
			request.setAttribute("message", "비밀번호가 틀렸읍니다");
	    else if( mvo.getPwd().equals(pwd)) {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("loginUser", mvo);
	    	url = "ticket.do?command=index";
	    }else
	    	request.setAttribute("message", "로그인 실패. 관리자에게 문의하시오");
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
		
		

	}

}
