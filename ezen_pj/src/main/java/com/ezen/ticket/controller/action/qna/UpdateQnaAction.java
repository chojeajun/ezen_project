package com.ezen.ticket.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.QnaDao;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qvo = new QnaVO();
		
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String path = context.getRealPath("upload");
		
		MultipartRequest multi = new MultipartRequest(
				request,path,20*1024*1024,"UTF-8",new DefaultFileRenamePolicy()
		);
		
		qvo.setUserid(multi.getParameter("userid"));
		qvo.setPass(multi.getParameter("pass"));
		qvo.setEmail(multi.getParameter("email"));
		qvo.setTitle(multi.getParameter("title"));
		qvo.setContent(multi.getParameter("content"));
		qvo.setNum(Integer.parseInt(multi.getParameter("num")));
		
		
		if(multi.getFilesystemName("newFile")==null)
			qvo.setImgfilename(multi.getParameter("oldFile"));
		else
			qvo.setImgfilename(multi.getFilesystemName("newFile"));
		
		qdao.updateBoard( qvo );
		String url  = "ticket.do?command=qnaViewNoCount&num=" + qvo.getNum();
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}