<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../header.jsp"%>

<div id="cartlistwrap">
	<form id="frmcartList" method="post" name="frm">
		<c:choose>
			<c:when test="${ notBuy_cartList.size() == 0 }">
				<h3
					style="margin-top: 60px; line-height: 80px; font-size: 50px; text-align: center; margin-bottom: 60px; width: 1200px; height: 80px; border: 3px dashed red;">장바구니가
					비었습니다</h3>
			</c:when>
			<c:otherwise>
				<div class="cartList_box">
					<h1>장바구니 목록</h1>
					<table class="cartItemList1">
						<tr>
							<th style="width: 5%;">번호</th>
							<th style="width: 20%;">제목</th>
							<th style="width: 20%">장소</th>
							<th style="width: 10%">좌석</th>
							<th style="width: 14%">콘텐츠 일자</th>
							<th style="width: 10%">콘텐츠 일시</th>
							<th style="width: 6%">총수량</th>
							<th style="width: 10%">대리인</th>
							<th style="width: 5%">선택</th>
						</tr>
						<c:forEach items="${ notBuy }" var="contentVO">
							<input type="hidden" id="cseq" name="cseq"
								value="${ contentVO.CSEQ }" />
							<input type="hidden" id="area" name="area"
								value="${ contentVO.AREA }" />
							<tr>
								<%!int seq = 1;%>
								<td><%=seq%></td>
								<td>${ contentVO.TITLE }</td>
								<td>${ contentVO.LOCATIONNAME }</td>
								<td>${ contentVO.AREA }</td>


								<c:forEach items="${ notBuy_cartList }" begin="<%=seq-1 %>"
									end="<%=seq-1 %>" var="cartListVO">
									<input type="hidden" id="quantity" name="quantity"
										value="${ cartListVO.QUANTITY }" />
									<input type="hidden" id="contentdate" name="contentdate"
										value="${ cartListVO.CONTENTDATE }" />
									<input type="hidden" id="contenttime" name="contenttime"
										value="${ cartListVO.CONTENTTIME }" />
									<td><fmt:formatDate value="${ cartListVO.CONTENTDATE }"
											pattern="yyyy-MM-dd" /></td>
									<td>${ cartListVO.CONTENTTIME }</td>
									<td>${ cartListVO.QUANTITY }</td>
									<%-- <c:if test="${ cartListVO.MSEQ2 == 0 }">
										<td>선택 안함</td>
									</c:if> --%>
									<c:forEach items="${ defuty }" begin="<%=seq-1 %>"
										end="<%=seq-1 %>" var="defutyVO">
										<c:if test="${ not empty defutyVO.CNICKNAME }">
											<td>${ defutyVO.CNICKNAME }</td>
										</c:if>
										<c:forEach items="${checkbox }" var="checkbox"
											begin="<%=seq-1%>" end="<%=seq-1%>">
												<input type="hidden" name="checkbox" value="${ checkbox }"/>
													<td><input type="checkbox" name="cartseq"
														value="${ cartListVO.CARTSEQ }"></td>
												
										</c:forEach>
									</c:forEach>
								</c:forEach>
							</tr>
							<%
							seq += 1;
							%>
						</c:forEach>
						<%
						seq = 1;
						%>
					</table>
					<div id="buttons">
						<input type="button" value="계속 쇼핑" class="comeon"
							onclick="location.href='/'" />
						<c:if test="${ notBuy_cartList.size() != 0 }">
							<input type="button" value="주문하기" class="submit"
								onclick="go_cart_order();" />
							<input type="button" value="수정하기" class="update"
								onclick="go_cart_update();" />
							<!-- command=cartOrderDetail, jsp = detail/orderDetail -->
							<input type="button" value="삭제하기" class="cancel"
								onclick="go_cart_delete();" />
						</c:if>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<h1>구매 내역</h1>
		<c:choose>
			<c:when test="${ buy_cartList.size() == 0 }">
				<h3 id="notbuyanything">구매한 내역이 없습니다.</h3>
			</c:when>
			<c:otherwise>
				<table class="cartItemList">
					<tr>
						<th>번호</th>
						<th>일시</th>
						<th>시간</th>
						<th>장소</th>
						<th>좌석</th>
						<th>총수량</th>
						<th>신청날짜</th>
					</tr>
					<c:forEach items="${ buy_cartList }" var="cartListVO">
						<tr>
							<td><%=seq%></td>
							<td><fmt:formatDate value="${ cartListVO.CONTENTDATE }"
									pattern="yyyy-MM-dd" /></td>
							<td>${ cartListVO.CONTENTTIME }</td>
							<c:forEach items="${ buy }" begin="<%=seq-1 %>" end="<%=seq-1 %>"
								var="buyVO">
								<td>${ buyVO.LOCATIONNAME }</td>
							</c:forEach>
							<td>${ cartListVO.AREA }</td>
							<td>${ cartListVO.QUANTITY }</td>
							<td><fmt:formatDate value="${ cartListVO.INDATE }"
									pattern="yyyy-MM-dd" /></td>
						</tr>
						<%
						seq += 1;
						%>
					</c:forEach>
					<%
					seq = 1;
					%>
				</table>
			</c:otherwise>
		</c:choose>
	</form>
</div>

<%@ include file="../footer.jsp"%>