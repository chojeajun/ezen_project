package com.ezen.ticket.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.AdminDao;
import com.ezen.ticket.dto.AdminVO;
import com.ezen.ticket.dto.ContentVO;
import com.ezen.ticket.util.Paging;

public class AdminProductListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/adminProductList.jsp";

		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
		if (avo == null)
			url = "ticket.do?command=admin";
		else {
			if(request.getParameter("changMenu") != null) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			
			Paging paging = new Paging();
			paging.setDisplayPage(10);
			paging.setDisplayRow(10);

			if (request.getParameter("page") != null) {
				paging.setPage(Integer.parseInt(request.getParameter("page")));
				session.setAttribute("page", Integer.parseInt(request.getParameter("page")));
			} else if (session.getAttribute("page") != null) {
				paging.setPage((Integer) session.getAttribute("page"));
			} else {
				paging.setPage(1);
			}
			
			String key = "";
			if (request.getParameter("key") != null) {
				// 검색할 단어가 같이 전달되었다면
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if (session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			} else {
				key = "";
				session.removeAttribute("key");
			}

			AdminDao adao = AdminDao.getInstance();
			int count = adao.getAllCount("product_all_content_view", "title", key);
			
			paging.setTotalCount(count);
			
			ArrayList<ContentVO> productList = adao.adminProductList(paging, key);
			//ArrayList<ContentVO> productList2 = adao.adminProductList2(paging, key);
			
			request.setAttribute("productList", productList);
			//request.setAttribute("productList2", productList2);
			request.setAttribute("paging", paging);
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
