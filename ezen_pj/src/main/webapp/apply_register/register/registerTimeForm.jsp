<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<h2> 타임폼 페이지 입니다</h2>

<c:forEach items="${ register }" var="regi">
	${regi.rtseq }
</c:forEach>
<%@ include file="../../footer.jsp" %>