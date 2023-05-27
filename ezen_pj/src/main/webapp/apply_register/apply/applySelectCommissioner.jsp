<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<form name="frm" action="ticket.do" method="post" class="form">
	<section class="body_Choose">
		<div class="buttonBoxArea">
			<div class="Header">선택하신 공연</div>
			<div class="detailBody">
				<div class="posterZone">
					<div class="posterBox"></div>
				</div>
				<div class="detailZone">
					<div class="detail title">공연명 : </div>
					<div class="detail location">공연 위치 : </div>
					<div class="detail dateTime">공연 일정 : </div>
					<div class="detail area">공연 구역 : </div>
					<div class="detail tTime">티켓팅 일정 : </div>
				</div>
			</div>
		</div>
		<div class="buttonBoxArea">
			<div class="Header">대리인 리스트</div>
			<div class="detailBody">
				<table>
					<tr><th></tr>
					<c:forEach items="{commissionerList}" var="commissionerVO" varStatus="state">
						<li onclick="saveTime('${state.count}')"><a href="#" onclick="return false">{contentTil.contentTime}</a>
						<input type="hidden" value="${contentTil.contentTime}" name="time" id='${"time"+=state.count}'>
						</li>
					</c:forEach>
					
				</table>
			</div>
		</div>
	</section>
</form>

<%@ include file="../../footer.jsp" %>