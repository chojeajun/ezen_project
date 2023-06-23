package com.ezen.ticket.controller.action.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.OrderDao;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.dto.OrderVO;

public class OrderViewAction implements Action {
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "order/orderView.jsp";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			url = "ticket.do?command=loginForm";
		}else {
			OrderDao odao=OrderDao.getInstance();
			ArrayList<OrderVO> orderList=new ArrayList<OrderVO>();
			
			//한번에 여러개 시킨 주문이면 'xx외 3개'로 나타내고 1개 시켰으면 걍 이름 나오게 하는 작업 
			
			ArrayList<Integer> oseqList=new ArrayList<Integer>();
			oseqList=odao.getOrderListOseq(mvo.getMseq());		//orders에 있는 oseq들 안겹치게 한개씩만 가져옴
			
			ArrayList<OrderVO> orderListByOseq=new ArrayList<OrderVO>();
			if(oseqList.size()!=0) {
				for(Integer oseq: oseqList) {
					
					orderListByOseq=odao.getOrderList(oseq);
						
					OrderVO firstProduct=orderListByOseq.get(0);//첫번째 제품 백업
					int listsize=orderListByOseq.size();
					
					int totalPrice = 0;
			    		for( OrderVO ovo : orderListByOseq) {
			    			totalPrice += ovo.getContent_price()* ovo.getQuantity()+ovo.getCom_price();
						firstProduct.setTotalPrice(totalPrice);
							if(orderListByOseq.size()>1) { //주문한 게 여러개인 경우
							firstProduct.setOrderTitle(firstProduct.getTitle()+" 외 "+(orderListByOseq.size()-1)+"건");
							}else { //한개인 경우
								firstProduct.setOrderTitle(firstProduct.getTitle());
							}
			    		}
			    		orderList.add(firstProduct);
				}
			}
			request.setAttribute("orderList", orderList);
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}