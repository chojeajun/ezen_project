package com.ezen.ticket.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.QnaDao;
import com.ezen.ticket.dto.QnaVO;

public class QnaCheckPassAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qvo = QnaDao.getQna(num);
		String url = null;
		if (QnaVO.getPass() == null) {
			request.setAttribute("message", "비밀번호오류 관리에게 문의 하세요");
			url = "qna/qnaCheckPass.jsp";
		} else if (QnaVO.getPass().equals(pass)) {
			url = "qna/checkSuccess.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("pass", "T");
		} else {
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
			url = "qna/qnaCheckPass.jsp";
		}

		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}
}
