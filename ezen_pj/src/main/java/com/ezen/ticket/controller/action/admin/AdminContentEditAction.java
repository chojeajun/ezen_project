package com.ezen.ticket.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.AdminDao;
import com.ezen.ticket.dto.AdminVO;
import com.ezen.ticket.dto.ContentVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminContentEditAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "ticket.do?command=adminContentListDetail";
		
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if(avo == null) {
			url ="ticket.do?command=loginAdmin";
		}else {
			ContentVO cvo = new ContentVO();
			ServletContext context = session.getServletContext();
			String path = context.getRealPath("images/content");
			MultipartRequest multi = new MultipartRequest(
					request,
					path,
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			
			cvo.setCseq(Integer.parseInt(multi.getParameter("cseq")));
			System.out.println(multi.getParameter("cseq"));
			cvo.setTitle(multi.getParameter("title"));
			cvo.setContent(multi.getParameter("content"));

			if(multi.getFilesystemName("image") == null) {
				cvo.setImage(multi.getParameter("oldimage"));
			} else {
				cvo.setImage(multi.getFilesystemName("image"));
			}
			
			AdminDao adao = AdminDao.getInstance();
			adao.updateContent(cvo);
			url = url + "&cseq=" + cvo.getCseq(); 
		}
		request.getRequestDispatcher(url).forward(request, response);
		
		
		
		
		
		
	}

}
