<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<form name="frm" action="ticket.do" method="post" class="resultForm">
<input type="hidden" name="command" value="orderView">
<section>
<h2>신청이 완료되었습니다!</h2>
	<div class="button2Box">
		<input type="submit" value="신청내역 보기" class="button2">
		<input type="button" value="메인으로" onclick="goMain()"class="button2">
	</div>
</section>
</form>
<%@ include file="../../footer.jsp" %>