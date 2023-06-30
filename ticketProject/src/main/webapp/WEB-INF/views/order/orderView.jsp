<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<form name="frm" action="ticket.do" method="post" class="formOrder">


<section>
	<div class="orderList_box">
		<div class="title">주문 목록</div>
		<table class="orderTable">
			<tr class="titleTr">
				<td>주문 번호</td>
				<td>공연명</td>
				<td>가격</td>
				<td>주문 날짜</td>
				<td></td>
			</tr>
			</table>
					<ul class="orderListUl">
			<c:choose>
				<c:when test="${orderList.size()==0}">
						<li>주문 내역 없음</li>
				</c:when>
				<c:otherwise>
					<c:forEach items="${orderList}" var="ol">
							<li>
								<div>${ol.oseq}</div>
								<div>${ol.orderTitle}</div>
								<div>${ol.totalPrice}</div>
								<div><fmt:formatDate value="${ol.oindate}" pattern="yyyy-MM-dd" /></div>
								<div><input type="button" class="orderDetailButton"value="주문 상세 보기" 
								onclick="location.href='ticket.do?command=orderDetail&oseq='+${ol.oseq}" /></div>
							</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
						</ul>
	</div>
</section>
</form>
<%@ include file="../footer.jsp" %>