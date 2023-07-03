<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp"%>
<%@ include file="../../include/admin/sub_menu.jsp"%>

<article>
<h1>공연리스트</h1>
<form name="frm" method="post">
	<table style= "margin-left: 513px;">
		<tr><td width="642">
			공연명<input type="text" name="key" value="${key}">
			<input class="btn2" type="button" name="btn_search" value="검색" onClick="go_search('contentList');">
			<input class="btn2" type="button" name="btn_total" value="전체보기 " onClick="go_total('contentList');">
			<input class="btn2" type="button" name="btn_write" value="공연등록"  	onClick="go_wrt();"></td></tr>
	</table>
	<table id="contentList" align="center">
		<tr><th>번호</th><th>공연명</th><th>아티스트</th><th>컨텐츠</th><th>가격</th><th>등록일</th><th>연령제한</th></tr>
		<c:choose>
	    	<c:when test="${contentListSize<=0}">
	    		<tr><td width="100%" colspan="7" align="center" height="23">등록된 상품이 없습니다.</td></tr>
	    	</c:when>
	    	<c:otherwise>
	    		<c:forEach items="${contentList}" var="contentVO">
			   		<tr><td height="23" align="center" >${contentVO.CSEQ}</td>
						<td style="text-align:left; padding-left:50px; padding-right:0px;">
						<a href="#" onClick="go_detail('${contentVO.CSEQ}')">${contentVO.ARTIST}</a></td>
						<td><fmt:formatNumber value="${contentVO.content}"/></td>
						<td><fmt:formatNumber value="${contentVO.PRICE}"/></td>
			      		<td><fmt:formatDate value="${contentVO.INDATE}"/></td>
			      		<td><fmt:formatDate value="${contentVO.age}"/></td>
			      		<td><c:choose>
			      			<c:when test='${contentVO.useyn=="N"}'>미사용</c:when>
			   	 			<c:otherwise>사용</c:otherwise> 
						</c:choose></td>
					</tr>
			    </c:forEach> 
	    	</c:otherwise>
	    </c:choose>
	</table>
</form><br>

<jsp:include page="../../include/paging/paging.jsp">
	<jsp:param name="command" value="contentList" />
</jsp:include>

</article>

<%@ include file="../../include/admin/footer.jsp"%>









