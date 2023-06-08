package com.ezen.ticket.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ReviewDao;
import com.ezen.ticket.dto.ReviewReplyVO;
import com.ezen.ticket.dto.ReviewVO;

public class AdminReviewViewNoCountAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("rseq"));
		System.out.println("rseq  가 받아지냐 ?" +  num); // 받아지는것 확인함
		
		ReviewDao rdao = ReviewDao.getInstance();
		
		ArrayList<ReviewReplyVO> list = rdao.selectReply(num); 
		request.setAttribute("replyList", list); // list 긁어온걸 replyList 에 파라미터전달
		
		ReviewVO rvo = rdao.getReview(num);
		request.setAttribute("reviewVO", rvo); // 파라미터전달
		
		System.out.println("댓글 리스트 = >" +  list.size());
		
		RequestDispatcher dp = request.getRequestDispatcher("ticket.do?command=adminReviewView");
		dp.forward(request, response);

	}

}
