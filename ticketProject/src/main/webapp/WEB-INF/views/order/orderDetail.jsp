<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<form name="frm" method="post" class="formOrder">


<section>
	<div class="orderList_box">
		<div class="title">주문 상세 내역</div>
		<table class="orderDTable">
			<tr class="titleTr">
				<td>주문 번호</td>
				<td>공연 정보</td>
				<td>대리인</td>
				<td>가격</td>
			</tr>
			</table>
				<ul class="orderDListUl">
			<c:forEach items="${orderDetailList}" var="odl">
					<li>
						<div class="od"><div id="oseq">${odl.OSEQ}</div><div>구매날짜 <fmt:formatDate value="${odl.INDATE}" pattern="yyyy-MM-dd" /></div></div>
						<div class="od"><div id="title">${odl.TITLE}</div><div>${odl.ARTIST}</div><div>${odl.LOCATIONNAME}&nbsp;&nbsp;${odl.AREA}</div><div>날짜/시간 <fmt:formatDate value="${odl.CONTENTDATE}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;${odl.CONTENTTIME}</div></div>
						<div class="od"><div style="font-weight: bold;">${odl.COM_NICKNAME }</div><div>${odl.COM_GRADE}</div></div>
						<div class="od"><div>좌석 가격 ${odl.CONTENT_PRICE }</div><div>커미션비 ${odl.COM_PRICE}</div><div>수량 ${odl.QUANTITY }</div><div id="totPrice">총합가격 ${odl.CONTENT_PRICE*odl.QUANTITY+odl.COM_PRICE}</div></div>
					</li>
			</c:forEach>
				</ul>
	</div>
</section>
<input type="button" class="goOrderView" value="목록" onclick="location.href='/myOrderView'"/>
</form>

<%@ include file="../footer.jsp" %>