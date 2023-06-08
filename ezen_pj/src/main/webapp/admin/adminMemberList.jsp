<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/admin/sub_menu.jsp"%>
<%@ include file="header.jsp" %>
<script src="./script/admin.js"></script>
<article class="mbl_box">
	<h1>회원리스트</h1>
	<form name="frm" method="post">
			<div class="search_box">
				<h2 class="search_name">회원 이름으로 검색</h2>
				<input type="text" name="key" value="${ key3 }">
				<input class="btn" type="button" value="검색" onclick="go_search('adminMemberList')">
				<input class="btn"  type="button" value="전체보기" onclick="go_total('adminMemberList')">
			</div>				
	<table id="memberList">
		<tr>
			<th>아이디(탈퇴여부)</th>
			<th>이름</th>
			<th>닉네임</th>
			<th>이메일</th>
			<th>우편번호</th>
			<th>주소</th>
			<th>전화</th>
			<th>가입일</th>
		</tr>
		<c:forEach items="${ memberList }" var="memberVO">
			<tr>
				<td>
					${ memberVO.id }
					<c:choose>
						<c:when test="${ fn:contains(memberVO.useyn,'Y') }">
							<input type="checkbox" name="useyn" disabled="disabled">
						</c:when>
						<c:otherwise>
								<input type="checkbox" name="useyn" checked="checked" disabled="disabled">
						</c:otherwise>
					</c:choose>
				</td>
				<td>${ memberVO.name }</td> 
				<td>${ memberVO.nickname }</td>
				<td>${ memberVO.email }</td>
				<td>${ memberVO.zip_num }</td> 
				<td>${ memberVO.address1 }</td>
				<td>${ memberVO.phone }</td> 
				<td><fmt:formatDate value="${ memberVO.indate }"/></td>
			</tr>
		</c:forEach>
	</table>
	<div class="clear"></div>
	<jsp:include page="/admin/paging/page.jsp">
		<jsp:param name="command" value="ticket.do?command=adminMemberList"/>
	</jsp:include>
	</form>
</article>


<%@ include file="footer.jsp" %>
