package com.ezen.ticket.controller.action.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ContentDao;
import com.ezen.ticket.dto.ContentVO;

public class ContentFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * HttpSession session = request.getSession(); MemberVO mvo =
		 * (MemberVO)session.getAttribute("loginUser");
		 * 할 필요가 없음 누구나 대리티켓팅을 할 수 있음.
		 */
		
		ContentDao cdao = ContentDao.getInstance();
		ArrayList<ContentVO> content = cdao.selectAll();
		
		request.setAttribute("content", content);
		request.getRequestDispatcher("content/content.jsp").forward(request, response);
		
		
	}

}
