<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket Shop</title>

<link rel="stylesheet" href="/css/qna.css">
<script src="script/qna.js"></script>
</head>

<form action="ticket.do" method="get" name="frm_reply">
	<input type="hidden" name="command" value="adminQnaReply" /> 
	<input type="hidden" name="qnanum" value="${QnaVO.qseq}" />
	<input type="hidden" name="adminId" value="${loginAdmin.id}" />
	<article>
	<h2> 고객 게시판 </h2> <br><br> <h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다. </h3><br><br>
	
	<table class="qna_view_table">
		<tr><th>번호</th><td width="500" style="text-align:left; ">${QnaVO.qseq}</td></tr>
		<tr><th>제목</th><td width="500" style="text-align:left; ">${QnaVO.title}</td></tr>
		<tr><th>질문내용</th>
		<td align="left" style="text-align:left;font-size:100%; box-sizing: border-box; resize: none; padding:10px;"><pre>${QnaVO.content}</pre></td></tr>
		<tr><th>작성자</th><td align="left" style="text-align:left; font-size:115%;"><pre>&nbsp;&nbsp;${QnaVO.id}</pre></td></tr>
		<tr><th>등록일</th><td align="left" style="text-align:left;">
		<fmt:formatDate value="${QnaVO.indate}" type="date"/></td></tr>
		<tr><th>조회수</th><td>${QnaVO.readcount}</td></tr>
		
	</table>
			
	<c:set var="now" value="<%=new java.util.Date()%>" />
	
				<table>
					<tr>
						<th>문의 답글</th>
					</tr>
	
					<tr align="center">
						<td>관리자<input type="hidden" name="adminId" value="${loginAdmin.id}"></td>
						<td>
							<input type="text" name="reply" style="width:800px;height:50px;" >
						</td>
						<td><input type="submit" value="답글 작성" onClick="return reply_check();"></td>
					</tr>

					<tr align="center" class="qnaReplyList">
					<c:choose>
						<c:when test="${empty aqvo.qnaContent}">
						</c:when>
						<c:otherwise>
						<td>관리자</td>
						<td><fmt:formatDate value="${aqvo.writeDate}" pattern="MM/dd HH:mm" /></td>
						<td align="left">&nbsp;${aqvo.qnaContent}</td>
						<td>
							<input type="button" value="삭제"	 onClick = "location.href='ticket.do?command=deleteQnaReply&replynum=${qnaReplyVO.qrseq}'">
						</td>
						</c:otherwise>
					</c:choose>
					</tr>
				</table>
	
		
			<div class="clear"></div>
	<div id="buttons" style="float:right">
		<input type="button" value="메인화면으로 돌아가기" class="cancel" style="color:black" onclick="location.href='ticket.do?command=index'">
	</div>
	</article>
</form>

<%@ include file="../footer.jsp" %>