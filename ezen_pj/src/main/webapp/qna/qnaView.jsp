<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_image_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket Shop</title>

<link rel="stylesheet" href="/css/qna.css">

</head>
<article>
<h2> 고객 게시판 </h2> <h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다. </h3>
<form>
<table>
	<tr><th>제목</th><td width="500" background="pink" style="text-align:left; ">${QnaVO.title}</td></tr>
	<tr><th>등록일</th><td align="left" style="text-align:left;">
	<fmt:formatDate value="${QnaVO.indate}" type="date"/></td></tr>
	<tr><th>질문내용</th>
	<td align="left" style="text-align:left;font-size:115%;"><pre>${QnaVO.content}</pre></td></tr>
	<tr><th>답변내용</th><td align="left" style="text-align:left;color:white;">${QnaVO.reply}</td></tr>
</table><div class="clear"></div>
<div id="buttons" style="float:right">
	<input type="button" value="목록보기" class="submit" onclick="location.href='ticket.do?command=qnaList'">
	<input type="button" value="돌아가기" class="cancel" onclick="location.href='ticket.do?command=index'">
</div>
</form>
</article>

<%@ include file="../footer.jsp" %>