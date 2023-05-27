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

public class ReviewViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "review/reviewView.jsp";
		int rseq = Integer.parseInt(request.getParameter("rseq"));
		System.out.println(rseq);
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			url ="ticket.do?command=loginForm";
		} else {
			ReviewDao rdao = ReviewDao.getInstance();
			ReviewVO rvo = rdao.getRiview(rseq);
			
//			ServletContext context = session.getServletContext();
//			String path = context.getRealPath("images");
//			
//			MultipartRequest multi = new MultipartRequest(
//					request,
//					path,
//					5*1024*1024,
//					"UTF-8",
//					new DefaultFileRenamePolicy()
//			);
			
			request.setAttribute("reviewVO", rvo);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}



