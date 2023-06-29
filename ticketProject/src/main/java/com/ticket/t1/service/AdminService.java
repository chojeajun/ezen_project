package com.ticket.t1.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IAdminDao;
import com.ticket.t1.dto.BannerVO;
import com.ticket.t1.dto.ContentVO;
import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.OrderVO;
import com.ticket.t1.dto.QnaVO;
import com.ticket.t1.util.Paging;



@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public void getAdmin(HashMap<String, Object> paramMap) {
		adao.getAdmin( paramMap );		
	}

	public HashMap<String, Object> getContentList(HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();

		if( request.getParameter("first")!=null ) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}

		int page = 1;
		if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if( session.getAttribute("page")!= null ) {
			page = (int) session.getAttribute("page");
		} else {
			page = 1;
			session.removeAttribute("page");
		}

		String key = "";
		if( request.getParameter("key") != null ) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if( session.getAttribute("key")!= null ) {
			key = (String)session.getAttribute("key");
		} else {
			session.removeAttribute("key");
			key = "";
		} 
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		int count = adao.getAllCount( "content", "name", key );
		paging.setTotalCount(count);
		paging.paging();
		
		List<ContentVO> contentList = adao.listProduct( paging , key );
		result.put("contentList" , contentList);
		result.put("paging", paging);
		result.put("key", key);
		
		return result;
	}

	public void insertContent(@Valid ContentVO contentvo) {
		adao.insertContent(contentvo);
		}
		public void updateContent(ContentVO contentvo) {
			adao.updateContent(contentvo);
		}
		public HashMap<String, Object> getOrderList(HttpServletRequest request) {
			HashMap<String, Object> result = new HashMap<String, Object>();
			HttpSession session = request.getSession();

			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			
			int page = 1;
			if( request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if( session.getAttribute("page")!= null ) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}
			
			String key = "";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			} 

			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = adao.getAllCount( "order_view", "pname", key );
			paging.setTotalCount(count);
			paging.paging();
			
			List<OrderVO> orderList = adao.listOrders( paging , key );
			result.put("orderList" , orderList);
			result.put("paging", paging);
			result.put("key", key);
			
			return result;
		}

		public HashMap<String, Object> getMemberList(HttpServletRequest request) {
			  HashMap<String, Object> result = new HashMap<String, Object>();
		      HttpSession session = request.getSession();
		      
		      if( request.getParameter("first")!=null) {
		         session.removeAttribute("page");
		         session.removeAttribute("key");
		      }
		      int page=1;
		      if( request.getParameter("page") != null) {
		         page = Integer.parseInt(request.getParameter("page"));
		         session.setAttribute("page", page);
		      }else if( session.getAttribute("page") != null ) {
		         page = (int)session.getAttribute("page");
		      }else {
		         page = 1;
		         session.removeAttribute("page");
		      }
		      
		      String key = "";
		      if( request.getParameter("key") != null ) {
		         key = request.getParameter("key");
		         session.setAttribute("key", key);
		      } else if( session.getAttribute("key")!= null ) {
		         key = (String)session.getAttribute("key");
		      } else {
		         session.removeAttribute("key");
		         key="";
		      }
		      
		      Paging paging = new Paging();
		      paging.setPage(page);
		      
		      int count = adao.getAllCount("member", "name", key);
		      paging.setTotalCount(count);
		      paging.paging();
		      
		      List<MemberVO> list = adao.listMember(paging, key);
		      result.put("memberList", list);
		      result.put("paging", paging);
		      result.put("key", key);
		      
		      return result;

	}

		public HashMap<String, Object> getQnaList(HttpServletRequest request) {
			HashMap<String, Object> result = new HashMap<String, Object>();
			HttpSession session = request.getSession();

			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			
			int page = 1;
			if( request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if( session.getAttribute("page")!= null ) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}
			
			String key = "";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			} 
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = adao.getAllCount( "qna", "subject", key );
			paging.setTotalCount(count);
			paging.paging();
			
			List<QnaVO> qnaList = adao.listQna( paging , key );
			result.put("qnaList" , qnaList);
			result.put("paging", paging);
			result.put("key", key);
			
			return result;
		}

		public void updateOrderResult(int[] results) {
			
			for(int odseq : results)
			adao.updateOrderResult(odseq);
		}

		public void memberReinsert(String id, String useyn) {
			adao.memberReinsert(id,useyn);
		}

		public void updateQna(int qseq, String reply) {
			adao.updateQna(qseq,reply);
		}

		public List<BannerVO> getBannerList() {
			return adao.getBannerList();
		}

		public void insertBanner(BannerVO bannervo) {
			adao.insertBanner(bannervo);
		}

	

	
}

