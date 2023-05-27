package com.ezen.ticket.controller.action.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ReviewDao;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.dto.ReviewVO;
import com.ezen.ticket.util.Paging;

public class ReviewListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "review/reviewList.jsp";
		//int rseq = Integer.parseInt(request.getParameter("rseq")); // 조회할 리뷰 리스트 번호

		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) {
			url = "ticket.do?command=loginForm";
		} else {
			ReviewDao rdao = ReviewDao.getInstance();
			int page = 1;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			Paging paging = new Paging();
			paging.setPage(page);
			int count = rdao.getAllcount();
			paging.setTotalCount(count);
			ArrayList<ReviewVO> list = rdao.selectReview(paging);
			
			System.out.println(list.size());
			request.setAttribute("reviewList", list);
			request.setAttribute("paging", paging);

		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
