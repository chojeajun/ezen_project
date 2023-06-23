package com.ezen.ticket.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.QnaDao;

public class AdminQnaReplyDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qseq=Integer.parseInt(request.getParameter("qseq"));
		String url="ticket.do?command=adminQnaView&qseq="+qseq+"";
		int qrseq=Integer.parseInt(request.getParameter("qrseq"));
		
		QnaDao qdao=QnaDao.getInstance();
		int result=qdao.deleteQnaReply(qrseq);
		if(result==1) {
			System.out.println("qna 답글 삭제 성공");
			result=0;
			result=qdao.updateQnaRepYtoN(qseq);
			if(result==1) {
				System.out.println("답변상태 업데이트 완!");
			}else {
				System.out.println("답변상태 업뎃 안됨ㅠ");
			}
		}else {
			System.out.println("qna답글 삭제 실패ㅠ");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
