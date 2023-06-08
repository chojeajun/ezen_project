<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>
<script src="./script/admin.js"></script>
<article class="pro_box">
	<h1>상품리스트</h1>

	<form name="frm" method="post">
		<table>
			<tr>
				<td width="642">
					상품명 :
					<input type="text" name="key" value="${ key }">
					<input class="btn" type="button" name="btn_search" value="검색" onclick="go_search('adminProductList')">
					<input class="btn" type="button" name="btn_total" value="전체보기" onclick="go_total('adminProductList')">
					<input class="btn" type="button" name="btn_write" value="상품등록" onclick="go_wrt()">
				</td>
			</tr>
		</table>
	</form>

	<table id="productList" style="width:100%;">
		<tr>
			<th>번호</th>
			<th>타이틀</th>
			<th>아티스트</th>
			<th>장소</th>
			<th>카테고리</th>
			<th>베스트</th>
		</tr>
		<c:forEach items="${ productList }" var="productVO">
			<tr>
				<td style="text-align: left;">${ productVO.cseq }</td>
				<td>${ productVO.title }</td>
				<td>${ productVO.artist }></td>
				<td>${ productVO.locationName }</td>
				<td>${ productVO.category }</td>
				<td>${ productVO.bestyn }</td>
			</tr>
		</c:forEach>
	</table>
	<br> <br>
	<jsp:include page="/admin/paging/page.jsp">
		<jsp:param name="command" value="ticket.do?command=adminProductList" />
	</jsp:include>
	<!-- jsp:param 으로 필요한 내용을 전달하고 완성된 페이지를 include 합니다 -->
</article>


<%-- 				<td>${ productVO.category}</td> --%>
<%-- 				<td>${ productVO.bestyn }</td> --%>
<%-- 				<td><textarea rows="" cols="">${ productVO.content }</textarea></td> <!-- 클릭시 해당 상세보기로 이동 --> --%>


<!-- 	<h3>도착을 했습니다~~~</h3> -->
<!-- 	<input type="button" value="1:1 문의하기" onclick="location.href='ticket.do?command=adminQnaList'"> -->
<%@ include file="footer.jsp" %>