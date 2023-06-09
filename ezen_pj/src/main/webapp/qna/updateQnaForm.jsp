<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty pwd}">
	<jsp:forward page='../ticket.do?command=qnaViewNoCount&qseq=${qna.qseq}' />
</c:if>

<c:if test="${empty loginUser}">
	<jsp:forward page='../ticket.do?command=loginForm' />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateQnaForm.jsp</title>
<link rel="stylesheet" type="text/css" href="css/qna.css">
<script src="script/qna.js" ></script>
</head>
<body>

<div id="wrap" align="center">
	<h1>게시글 수정</h1>
	<form name="frm" method="post" action="ticket.do?command=updateqna"
	enctype="multipart/form-data">
	<input type="hidden" name="qseq" value="${qna.qseq}">
	<table>
		<tr><th>작성자</th><td>${qna.id}
			<input type="hidden" name="id" value="${loginUser.id}"></td></tr>
		<tr><th>비밀번호</th>
			<td><input type="password" name="pwd" size="12" >* (게시물 수정 삭제시 필요합니다.)</td></tr>
		<tr><th>제목</th>
			<td><input type="text" value="${qna.title}" size="70" name="title">*</td></tr>
		<tr><th>내용</th>
			<td><textarea cols="70" rows="15" name="content">${qna.content}</textarea>*</td>
			
		
		 <tr>
			<th>이미지</th>
			<td>
			<c:choose>
			
			<c:when test="${empty qna.image}">
			</c:when>
			<c:otherwise>
			<img src="./images/${qna.image }" height="80">
			</c:otherwise>
			
			</c:choose> &nbsp;&nbsp;
			<input type="file" name="newFile"/>
			<input type="hidden" name="oldFile" value="${qna.image}">
			</td>
		</tr>
	
	</table><br>
	<input type="submit" value="수정" onClick="return qnaCheck()">
	<input type="reset" value="다시 작성">
	<input type="button" value="돌아가기" 
		onClick="location.href='ticket.do?command=qnaViewNoCount&qseq=${qna.qseq}'">
	</form>
</div>
</body>
</html>