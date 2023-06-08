package com.ezen.ticket.controller;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.controller.action.IndexAction;
import com.ezen.ticket.controller.action.admin.AdminAction;
import com.ezen.ticket.controller.action.admin.AdminContentDeleteAction;
import com.ezen.ticket.controller.action.admin.AdminContentEditFormAction;
import com.ezen.ticket.controller.action.admin.AdminContentInsertFormAction;
import com.ezen.ticket.controller.action.admin.AdminContentListDetailAction;
import com.ezen.ticket.controller.action.admin.AdminLoginAction;
import com.ezen.ticket.controller.action.admin.AdminLoginFormAction;
import com.ezen.ticket.controller.action.admin.AdminMemberListAction;
import com.ezen.ticket.controller.action.admin.AdminProductListAction;
import com.ezen.ticket.controller.action.admin.AdminQnaListAction;
import com.ezen.ticket.controller.action.admin.AdminQnaReplyAction;
import com.ezen.ticket.controller.action.admin.AdminQnaReplyDeleteAction;
import com.ezen.ticket.controller.action.admin.AdminQnaViewAction;
import com.ezen.ticket.controller.action.admin.AdminReviewListAction;
import com.ezen.ticket.controller.action.admin.AdminReviewListDeleteAction;
import com.ezen.ticket.controller.action.admin.AdminReviewReplyDeleteAction;
import com.ezen.ticket.controller.action.admin.AdminReviewViewAction;
import com.ezen.ticket.controller.action.admin.AdminReviewViewNoCountAction;
import com.ezen.ticket.controller.action.admin.AdminSuccessListAction;
import com.ezen.ticket.controller.action.admin.AdminSuccessListDeleteAction;
import com.ezen.ticket.controller.action.admin.AdminSuccessReplyDeleteAction;
import com.ezen.ticket.controller.action.admin.AdminSuccessViewAction;
import com.ezen.ticket.controller.action.admin.AdminSuccessViewNoCountAction;
import com.ezen.ticket.controller.action.anr.ApplyAction;
import com.ezen.ticket.controller.action.anr.ApplyAndRegisterAction;
import com.ezen.ticket.controller.action.anr.ApplyCartAction;
import com.ezen.ticket.controller.action.anr.ApplyContentSelectAction;
import com.ezen.ticket.controller.action.anr.ApplySelectCommissionerAction;
import com.ezen.ticket.controller.action.anr.CategorySelectAction;
import com.ezen.ticket.controller.action.anr.RegisterFormAction;
import com.ezen.ticket.controller.action.anr.RegisterTimeFormAction;
import com.ezen.ticket.controller.action.cart.CartDeleteAction;
import com.ezen.ticket.controller.action.cart.CartDetailAction;
import com.ezen.ticket.controller.action.cart.CartDetailUpdateAction;
import com.ezen.ticket.controller.action.cart.CartListAction;
import com.ezen.ticket.controller.action.cart.CartUpdateAction;
import com.ezen.ticket.controller.action.content.ConcertFormAction;
import com.ezen.ticket.controller.action.content.ContentDetailAction;
import com.ezen.ticket.controller.action.content.ContentFormAction;
import com.ezen.ticket.controller.action.content.ContentSearchAction;
import com.ezen.ticket.controller.action.content.ExhibitionFormAction;
import com.ezen.ticket.controller.action.content.FestivalFormAction;
import com.ezen.ticket.controller.action.content.MusicalFormAction;
import com.ezen.ticket.controller.action.content.SportsFormAction;
import com.ezen.ticket.controller.action.member.ContractAction;
import com.ezen.ticket.controller.action.member.EditFormAction;
import com.ezen.ticket.controller.action.member.FindZipNumAction;
import com.ezen.ticket.controller.action.member.IdCheckFormAction;
import com.ezen.ticket.controller.action.member.JoinAction;
import com.ezen.ticket.controller.action.member.JoinFormAction;
import com.ezen.ticket.controller.action.member.LoginAction;
import com.ezen.ticket.controller.action.member.LoginFormAction;
import com.ezen.ticket.controller.action.member.LogoutAction;
import com.ezen.ticket.controller.action.member.MemberUpdateAction;
import com.ezen.ticket.controller.action.mypage.MyAllRegisterAction;
import com.ezen.ticket.controller.action.mypage.MyRegisterAction;
import com.ezen.ticket.controller.action.mypage.MyRegisteredAction;
import com.ezen.ticket.controller.action.mypage.MypageAction;
import com.ezen.ticket.controller.action.order.OrderDetailAction;
import com.ezen.ticket.controller.action.order.OrderViewAction;
import com.ezen.ticket.controller.action.qna.DeleteQnaAction;
import com.ezen.ticket.controller.action.qna.QnaCheckPassAction;
import com.ezen.ticket.controller.action.qna.QnaListAction;
import com.ezen.ticket.controller.action.qna.QnaPassFormAction;
import com.ezen.ticket.controller.action.qna.QnaViewAction;
import com.ezen.ticket.controller.action.qna.QnaViewNoCountAction;
import com.ezen.ticket.controller.action.qna.QnaWriteAction;
import com.ezen.ticket.controller.action.qna.QnaWriteFormAction;
import com.ezen.ticket.controller.action.qna.UpdateQnaAction;
import com.ezen.ticket.controller.action.qna.UpdateQnaFormAction;
import com.ezen.ticket.controller.action.review.ReviewDeleteAction;
import com.ezen.ticket.controller.action.review.ReviewEditAction;
import com.ezen.ticket.controller.action.review.ReviewEditFormAction;
import com.ezen.ticket.controller.action.review.ReviewListAction;
import com.ezen.ticket.controller.action.review.ReviewReplyAction;
import com.ezen.ticket.controller.action.review.ReviewReplyDeleteAction;
import com.ezen.ticket.controller.action.review.ReviewReplyEditFormAction;
import com.ezen.ticket.controller.action.review.ReviewViewAction;
import com.ezen.ticket.controller.action.review.ReviewViewNoCountAction;
import com.ezen.ticket.controller.action.review.ReviewWriteAction;
import com.ezen.ticket.controller.action.review.ReviewWriteFormAction;
import com.ezen.ticket.controller.action.success.SuccessDeleteAction;
import com.ezen.ticket.controller.action.success.SuccessEditAction;
import com.ezen.ticket.controller.action.success.SuccessEditFormAction;
import com.ezen.ticket.controller.action.success.SuccessListAction;
import com.ezen.ticket.controller.action.success.SuccessReplyAction;
import com.ezen.ticket.controller.action.success.SuccessReplyDeleteAction;
import com.ezen.ticket.controller.action.success.SuccessViewAction;
import com.ezen.ticket.controller.action.success.SuccessViewNoCountAction;
import com.ezen.ticket.controller.action.success.SuccessWriteAction;
import com.ezen.ticket.controller.action.success.SuccessWriteFormAction;


public class ActionFactory {

	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		
		//System.out.println("22 "+ command);
		Action ac = null;
		
		if(command.equals("index")) ac = new IndexAction();
		
		// main 화면
		else if(command.equals("contentForm")) ac = new ContentFormAction();
		else if(command.equals("concertForm")) ac = new ConcertFormAction();
		else if(command.equals("musicalForm")) ac = new MusicalFormAction();
		else if(command.equals("sportsForm")) ac = new SportsFormAction();
		else if(command.equals("festivalForm")) ac = new FestivalFormAction();
		else if(command.equals("exhibitionForm")) ac = new ExhibitionFormAction();
		else if(command.equals("contentDetail")) ac = new ContentDetailAction();
		else if(command.equals("contentSearch")) ac = new ContentSearchAction();
		
		//qna

		else if(command.equals("qnaList")) ac = new QnaListAction();
		else if (command.equals("qnaView")) ac = new QnaViewAction();
		else if( command.equals("qnaViewNoCount") ) ac = new QnaViewNoCountAction();
		else if (command.equals("qnaWriteForm")) ac = new QnaWriteFormAction();
		else if (command.equals("qnaWrite")) ac = new QnaWriteAction();
		else if (command.equals("deleteqna")) ac = new DeleteQnaAction();
		else if (command.equals("updateqnaForm")) ac = new UpdateQnaFormAction();
		else if (command.equals("updateqna")) ac = new UpdateQnaAction();
		else if( command.equals("qnaPassForm") ) ac = new QnaPassFormAction();
		else if( command.equals("qnaCheckPass") ) ac = new QnaCheckPassAction();
		
		
		//anr
		else if (command.equals("applyAndRegister")) ac = new ApplyAndRegisterAction();
		else if (command.equals("categorySelect")) ac = new CategorySelectAction();
		else if (command.equals("applyContentSelect")) ac = new ApplyContentSelectAction();
		else if (command.equals("applySelectCommissioner")) ac = new ApplySelectCommissionerAction();
		else if (command.equals("applyCart")) ac = new ApplyCartAction();
		else if (command.equals("apply")) ac = new ApplyAction();

		else if (command.equals("registerForm")) ac = new RegisterFormAction();
		else if (command.equals("registerTimeForm")) ac = new RegisterTimeFormAction();
		
		
		// login
		else if(command.equals("loginForm")) ac = new LoginFormAction();
		else if(command.equals("login")) ac = new LoginAction();
		else if(command.equals("logout")) ac = new LogoutAction();
		// join
		else if(command.equals("contract")) ac = new ContractAction();
		else if(command.equals("joinForm")) ac = new JoinFormAction();
		else if(command.equals("idCheckForm")) ac = new IdCheckFormAction();
		else if(command.equals("findZipNum")) ac = new FindZipNumAction();
		else if(command.equals("join")) ac = new JoinAction();
		// update, delete
		else if(command.equals("editForm")) ac = new EditFormAction();
		else if(command.equals("memberUpdate")) ac = new MemberUpdateAction();
		// delete 는 마이페이지에서 기능추가
		// review
		else if(command.equals("reviewList")) ac = new ReviewListAction();
		else if(command.equals("reviewView")) ac = new ReviewViewAction();
		else if(command.equals("reviewWriteForm")) ac = new ReviewWriteFormAction();
		else if(command.equals("reviewWrite")) ac = new ReviewWriteAction();  
		else if(command.equals("reviewEditForm")) ac = new ReviewEditFormAction();
		else if(command.equals("reviewEdit")) ac = new ReviewEditAction();
		else if(command.equals("reviewDelete")) ac = new ReviewDeleteAction();
		// review 댓글
		else if(command.equals("reviewReply")) ac = new ReviewReplyAction();
		else if(command.equals("reviewViewNoCount")) ac = new ReviewViewNoCountAction();
		else if(command.equals("reviewReplyDelete")) ac = new ReviewReplyDeleteAction();
		else if(command.equals("reviewReplyEditForm")) ac = new ReviewReplyEditFormAction();

		
		
		// cart
		else if(command.equals("cartList")) ac = new CartListAction();
		else if(command.equals("cartDelete")) ac = new CartDeleteAction();
		else if(command.equals("cartDetail")) ac = new CartDetailAction();
		else if(command.equals("cartUpdate")) ac = new CartUpdateAction();
		else if(command.equals("cartDetailUpdate")) ac = new CartDetailUpdateAction();
		// mypage
		else if(command.equals("mypage")) ac = new MypageAction();
		else if(command.equals("myRegister")) ac = new MyRegisterAction();
		else if(command.equals("myRegistered")) ac = new MyRegisteredAction();
		else if(command.equals("myAllRegister")) ac = new MyAllRegisterAction();
		
		// order
		else if(command.equals("orderView")) ac = new OrderViewAction();
		else if(command.equals("orderDetail")) ac = new OrderDetailAction();
		
		//success
		else if(command.equals("successList")) ac = new SuccessListAction();
		else if(command.equals("successView")) ac = new SuccessViewAction();
		else if(command.equals("successWriteForm")) ac = new SuccessWriteFormAction();
		else if(command.equals("successWrite")) ac = new SuccessWriteAction();
		else if(command.equals
				("successEditForm")) ac = new SuccessEditFormAction();
		else if(command.equals("successEdit")) ac = new SuccessEditAction();
		else if(command.equals("successDelete")) ac = new SuccessDeleteAction();
		
		//success 댓글
		else if(command.equals("successReply")) ac = new SuccessReplyAction();
		else if(command.equals("successViewNoCount")) ac = new SuccessViewNoCountAction();
		else if(command.equals("successReplyDelete")) ac = new SuccessReplyDeleteAction();
		
		//admin
		else if(command.equals("admin")) ac = new AdminAction();
		else if(command.equals("adminLoginForm")) ac = new AdminLoginFormAction();
		else if(command.equals("adminLogin")) ac = new AdminLoginAction();
		
		// 상품 리스트 조회 
		else if(command.equals("adminProductList")) ac = new AdminProductListAction();
		else if(command.equals("adminContentListDetail")) ac = new AdminContentListDetailAction();
		else if(command.equals("adminContentEditForm")) ac = new AdminContentEditFormAction();
//		else if(command.equals("adminContentEdit")) ac = new AdminContentEditAction();
		else if(command.equals("adminContentDelete")) ac = new AdminContentDeleteAction();
		else if(command.equals("adminContentInsertForm")) ac = new AdminContentInsertFormAction();
		
		//admin qna reply
		else if(command.equals("adminQnaList")) ac = new AdminQnaListAction();
		else if(command.equals("adminQnaView")) ac = new AdminQnaViewAction();
		else if(command.equals("adminQnaReply")) ac = new AdminQnaReplyAction();
		else if(command.equals("adminQnaReplyDelete")) ac = new AdminQnaReplyDeleteAction();
		
		
		
		//  admin member List
		else if(command.equals("adminMemberList")) ac = new AdminMemberListAction();
		
		
		// admin success List
		else if(command.equals("adminSuccessList")) ac = new AdminSuccessListAction();
		else if(command.equals("adminSuccessView")) ac = new AdminSuccessViewAction();
		else if(command.equals("adminSuccessListDelete")) ac = new AdminSuccessListDeleteAction();
		else if(command.equals("adminSuccessReplyDelete")) ac = new AdminSuccessReplyDeleteAction();
		else if(command.equals("adminSuccessViewNoCount")) ac = new AdminSuccessViewNoCountAction();
		
		// admin review List
		else if(command.equals("adminReviewList")) ac = new AdminReviewListAction();
		else if(command.equals("adminReviewView")) ac = new AdminReviewViewAction();
		else if(command.equals("adminReviewListDelete")) ac = new AdminReviewListDeleteAction();
		else if(command.equals("adminReviewReplyDelete")) ac = new AdminReviewReplyDeleteAction();
		else if(command.equals("adminReviewViewNoCount")) ac = new AdminReviewViewNoCountAction();
		
		//System.out.println("33 "+ command);
		return ac;
	}

	
	
	

}
		// joi
