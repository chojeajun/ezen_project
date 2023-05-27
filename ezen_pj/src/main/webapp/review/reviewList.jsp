<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">후기 게시판</h2>
		<h3>고객님들의 티켓팅 후기를 작성하는 후기 게시판입니다.</h3>
		<form name="formm" method="post" class="review_form">
			<table id="reviewList">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>답변 여부</th>
				</tr>
				<c:forEach items="${reviewList }" var="reviewVO">
					<tr>
						<td>${reviewVO.rseq }</td>
						<td>
							<a href="ticket.do?command=reviewView&rseq=${ reviewVO.rseq }">${reviewVO.title}</a>
						</td>
						<td>${ reviewVO.id}</td>
						<td>
							<fmt:parseDate var="indateStr" value="${reviewVO.indate}" pattern="yyyy-MM-dd" />
							<fmt:formatDate var="indate" value="${indateStr}" pattern="yyyy-MM-dd" />
							${indate}
						</td>
						<td>
							<c:choose>
								<c:when test="${reviewVO.repyn=='N' }">no</c:when>
								<c:when test="${reviewVO.repyn=='Y' }">yes</c:when>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="clear"></div>
			<div id="paging" style="font-size: 120%; font-weight: bold;">
				<c:url var="action" value="ticket.do?command=reviewList" />
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
			<div id="buttons" style="float: right">
				<input type="button" value="글쓰기" class="submit" style="color: black" onclick="location.href='ticket.do?command=reviewWriteForm'">
				<input type="button" value="돌아가기" class="cancel" style="color: black" onclick="location.href='ticket.do?command=index'">
			</div>
			<div class="clear"></div>
			<br>
		</form>
	</div>
</div>

<%@ include file="../footer.jsp"%>