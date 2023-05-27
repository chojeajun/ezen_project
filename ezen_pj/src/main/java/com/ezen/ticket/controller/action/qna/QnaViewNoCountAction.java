package com.ezen.ticket.controller.action.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.QnaDao;
import com.ezen.ticket.dto.QnaVO;
import com.ezen.ticket.dto.ReplyVO;

public class QnaViewNoCountAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt( request.getParameter("num") );
		QnaDao qdao = QnaDao.getInstance();

		ArrayList<ReplyVO> list = qdao.selectQna( num );
		request.setAttribute("replyList", list);
		
		QnaVO qvo = qdao.getQna(num);
		request.setAttribute("qna", qvo);
		
		HttpSession session = request.getSession();
		session.removeAttribute("pass");
		
		RequestDispatcher rd = request.getRequestDispatcher("qna/qnaView.jsp");
		rd.forward(request, response);

	}

}