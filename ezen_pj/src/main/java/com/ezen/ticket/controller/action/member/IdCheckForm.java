package com.ezen.ticket.controller.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.MemberDao;
import com.ezen.ticket.dto.MemberVO;

public class IdCheckForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 아이디 체크 
		String id = request.getParameter("id");
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(id);
		
		if(mvo == null ) {
			request.setAttribute("result", -1);
		} else {
			request.setAttribute("result", 1);
		}
		request.setAttribute("id", id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/idcheck.jsp");
		dispatcher.forward(request, response);
		
	}

}
