package com.ezen.ticket.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.AdminDao;
import com.ezen.ticket.dto.AdminVO;

public class AdminContentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "ticket.do?command=adminProductList";

		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
		if (avo == null)
			url = "ticket.do?command=admin";
		else {
			int cseq=Integer.parseInt(request.getParameter("cseq"));
			AdminDao adao=AdminDao.getInstance();
			int result=adao.adminDeleteContent(cseq);
			if(result==1) {
				System.out.println("어드민 공연 삭제 성공");
			}else {
				System.out.println("어드민 공연 삭제 실패");
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
