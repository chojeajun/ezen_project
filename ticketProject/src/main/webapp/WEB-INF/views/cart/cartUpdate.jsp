<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../header.jsp"%>

<div id="cartupdatelistwrap">
	<form id="updatecart" name="frmm" method="post">
		<div class="cartupdateList_box">
			<h1>수정할 항목</h1>
			<c:forEach items="${ content }" var="contentVO">
				<div class="updatetitle">
					<h1>${ contentVO.title }</h1>
				</div>
				<div class="updateimage">
					<img src="${ contentVO.image }" />
				</div>
			</c:forEach>
			<table class="cartupdatetable">
				<tr>
					<th>일자</th>
					<th>시간</th>
					<th>좌석</th>
					<th>총수량</th>
					<th>대리인</th>
				</tr>
				<c:forEach items="${ cart }" var="cartVO">
					<tr>
						<td><fmt:formatDate value="${ cartVO.contentdate }"
								pattern="yyyy-MM-dd" /></td>
						<td>${ cartVO.contenttime }</td>
						<td>${ cartVO.area }</td>
						<td><input type="text" name="quantity" id="quantity"
							value="${ cartVO.quantity }" /></td>
						<td>
							<div id="defuty">
								<select name="defuty" id="defuty_select">
									<option value="선택안함">선택 안함</option>
									<c:forEach items="${ defuty }" var="defutyVO">
										<option value="defuty_nicknames">${ defutyVO.cnickname }</option>
									</c:forEach>
								</select>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div id="buttons">
				<input type="button" value="수정하기" class="update" onclick="location.href='ticket.do?command=cartDetailUpdate&quantity=${cartVO.quantity}&defuty=${ defutyVO.cnickname }'"/>
				<input type="button" value="뒤로가기" class="back" onclick="location.href='ticket.do?command=cartList'"/>
			</div>
		</div>
	</form>
</div>
<%@ include file="../footer.jsp"%>