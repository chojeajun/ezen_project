<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
<div class="review_img_box"></div>
<div id="review_box" style="width: 1200px; margin: 50px auto;">
	<div class="review_content">
		<h2 class="review_title">성공 내역 게시판</h2>
		<h3>고객님들의 성공 내역을 볼 수 있는 게시판 입니다.</h3>
		<form name="formm" method="post" class="review_form">
			<table id="reviewList">
				<tr class="review_list_h">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>관리</th>
				</tr>
				<c:forEach items="${successList }" var="successVO">
					<tr>
						<td>${successVO.sucseq }</td>
						<td width="30%">
							<a href="ticket.do?command=adminSuccessView&sucseq=${ successVO.sucseq }">${successVO.title}</a>
						</td>
						<td>${ successVO.id}</td>
						<td>
							<fmt:parseDate var="indateStr" value="${successVO.indate}" pattern="yyyy-MM-dd" />
							<fmt:formatDate var="indate" value="${indateStr}" pattern="yyyy-MM-dd" />
							${indate}
						</td>
						<td>
							<input type="button" value="삭제" onclick="location.href='ticket.do?command=adminSuccessListDelete&sucseq=${ successVO.sucseq }'">
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="clear"></div>
			<div id="paging" style="font-size: 120%; font-weight: bold;">
				<c:url var="action" value="ticket.do?command=adminSuccessList" />
				<c:if test="${paging.prev}">
					<a href="${action }&page=${paging.beginPage-1}">◀</a>&nbsp;</c:if>
				<c:forEach begin="${paging.beginPage }" end="${paging.endPage }" var="index">
					<c:choose>
						<c:when test="${paging.page==index }">[${index }]</c:when>
						<c:otherwise>
							<a href="${action }&page=${index}">${index }</a> &nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.next }">
					<a href="${action }&page=${paging.endPage+1}">▶</a> &nbsp;</c:if>
			</div>
			<div class="clear"></div>
			<br>
			<div class="rev_btn_box btn_box" style="float: left">
				<input type="button" value="메인으로" class="cancel" onclick="location.href='ticket.do?command=adminProductList'">
			</div>
			<div class="clear"></div>
			<br>
		</form>
	</div>
</div>



<%@ include file="footer.jsp"%>