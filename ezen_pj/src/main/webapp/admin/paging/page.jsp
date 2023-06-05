<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	text-align: center;
}

#paging {
	font-size: 110%;
	font-weight: bold;
}
</style>
</head>
<body>
	<!-- 상품리스트, 주문리스트, 회원리스트, Qna리스트에 공통으로 include 될 페이지 영역 -->
	<div id="paging" align="center">
		<c:url var="action" value="${ param.command }" />
		<c:if test="${ paging.prev }">
			<a href="${ action }&page=${ paging.beginPage - 1 }">◀</a>&nbsp;
		</c:if>

		<c:forEach begin="${ paging.beginPage }" end="${ paging.endPage }" var="index">
			<c:choose>
				<c:when test="${ paging.page == index }">[${ index }]</c:when>
				<c:otherwise>
					<a href="${ action }&page=${ index }">${ index }</a>&nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${ paging.next }">
			<a href="${ action }&page=${ paging.endPage + 1 }">▶</a>
		</c:if>
	</div>
</body>
</html>