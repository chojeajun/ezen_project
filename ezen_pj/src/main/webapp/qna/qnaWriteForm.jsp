<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../header.jsp"%>
<%@ include file="sub_image_menu.jsp"%>

<article>
<h2> 고객 게시판 </h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다. </h3>
<form name="formm" method="post" action="ticket.do?command=qnaWrite" enctype="multipart/form-data">
	
	<fieldset>
		<legend>Board Info</legend>
		<label>Title</label> <input type="text" name="title" size="70"><br>
		<label>Content</label> <textarea rows="10" cols="80" name="content"></textarea><br>
		<label>Image</label> <input type="file" name="image"><br>
		
	</fieldset>
	
	<div class="clear"></div>
	
	<div id="buttons" style="float:right">
		<input type="submit" value="글쓰기" class="submit" style="color:black;">
		<input type="reset" value="취소" class="cancel" style="color:black;">
		<input type="button" value="돌아가기" class="submit" style="color:black;" onclick="location.href='ticket.do?command=qnaList'">
	</div>
	
</form>
</article>
<%@ include file ="../footer.jsp"%>
