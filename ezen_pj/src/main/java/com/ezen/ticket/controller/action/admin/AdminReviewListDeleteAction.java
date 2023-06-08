package com.ezen.ticket.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ReviewDao;
import com.ezen.ticket.dto.AdminVO;

public class AdminReviewListDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		String url="";
		if( avo == null ) {
			url = "ticket.do?command=adminLoginForm";
		}else {
			ReviewDao rdao = ReviewDao.getInstance();
			int rseq = Integer.parseInt(request.getParameter("rseq"));
			rdao.deleteReview(rseq);
		}
		request.getRequestDispatcher("ticket.do?command=adminReviewList").forward(request, response);

	}

}
