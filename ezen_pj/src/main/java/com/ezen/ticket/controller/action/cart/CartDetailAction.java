package com.ezen.ticket.controller.action.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.CartDao;
import com.ezen.ticket.dto.CartVO;
import com.ezen.ticket.dto.ContentVO;
import com.ezen.ticket.dto.MemberVO;

public class CartDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 장바구니에서 주문하기 눌러서 넘어갈 페이지
		String url = "ticket.do?command=cartList";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		
		if(mvo == null) {
			url = "member/login.jsp";
		}else {
			String [] cartseqArr = request.getParameterValues("cartseq");
			CartDao cdao = CartDao.getInstance();
			for(String cartseq : cartseqArr) {
				cdao.hoonUpdateCart(cartseq);
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);

	}

}
