package com.ezen.ticket.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ReviewDao;

public class AdminReviewReplyDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int repseq = Integer.parseInt(request.getParameter("repseq")); // 댓글번호
		int rseq = Integer.parseInt(request.getParameter("rseq")); // 리뷰리스트 번호
		System.out.println("전달받은 댓글번호 >" +repseq);
		System.out.println("전달받은 리뷰번호 >" +rseq);
		
		ReviewDao rdao = ReviewDao.getInstance();
		rdao.deleteReviewReply(repseq); // rdao에서 댓글번호를 넣어서 댓글 delete 를 진행

		String url ="ticket.do?command=adminReviewViewNoCount&rseq=" + rseq;

		request.getRequestDispatcher(url).forward(request, response);

	}

}
