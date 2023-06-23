package com.ezen.ticket.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.QnaDao;
import com.ezen.ticket.dto.AdminVO;

public class AdminQnaReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "ticket.do?command=adminQnaView";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
		if(avo == null) {
			url = "ticket.do?command=adminLoginForm";
		}else {
			String replyContent=request.getParameter("reply");
			int qseq=Integer.parseInt(request.getParameter("qseq"));
			String id=request.getParameter("adminId");
			QnaDao qdao=QnaDao.getInstance();
			int result=qdao.insertQnaReply(qseq,id,replyContent);
			
			if(result==0) {
				System.out.println("qna답글 추가 실패ㅠ");
			}else {
				System.out.println("qna답글 추가 성공~!~!!!!");
				result=qdao.updateQnaRepNtoY(qseq); //답변성공 한 경우에만 답변 여부 업데이트하게 함
				if(result==1) {
					System.out.println("답변상태 업데이트 완!");
				}else {
					System.out.println("답변상태 업뎃 안됨ㅠ");
				}
			}
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
