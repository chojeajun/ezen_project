<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<article>

	<section name="registerBody" id="bodybox_register">
		<div class="registerBox" name="registerboxform">
			<table class="registerDate" name="registerDate">
				<tr class="tableHead">
					<th>날짜</th>
					<th>시간</th>
					<th>콘텐츠</th>
				</tr>
				<td class="date">
					<div class="dateBox">
						<%@ include file="calendar.jsp" %>
				<td class="time">
					<div class="timeBox">
						<c:forEach items="${date}" var="contentVO">
							<ul>
								<li><a href="#" onclick="return false">contentVO.?</a></li>
							</ul>
						</c:forEach>
					</div>
				</td>
				<td class="content">
					
				</td>
			</table>
		</div>
	</section>

</article>


<%@ include file="../../footer.jsp"%>