package com.ezen.ticket.controller.action.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ReviewDao;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.dto.ReviewVO;

public class ReviewWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "ticket.do?command=reviewList";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) {
			url = "ticket.do?command=loginForm";
		} else {
			ReviewVO rvo = new ReviewVO();
			rvo.setId(mvo.getId());
			rvo.setMseq(rvo.getRseq());
			rvo.setTitle(request.getParameter("title"));
			rvo.setContent(request.getParameter("content"));
			ReviewDao rdao = ReviewDao.getInstance();
			rdao.insertReview(rvo);
		}

		response.sendRedirect(url);
	}

}
