<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file="../../header.jsp" %>

<form name="frm" method="post" class="form">
	<section class="body_Choose2">
		<div class="buttonBoxArea">
			<div class="Header">선택하신 공연</div>
			<div class="detailBody">
				<div class="posterZone">
					<div class="posterBox">
					<c:forEach items="${detailList}" var="dl">
						<img alt="공연 포스터" src="${dl.image}" width=300 height=400>
					</c:forEach>
					</div>
				</div>
				<div class="detailZone">
					<c:forEach items="${detailList}" var="dl">
						<div class="detail title">공연명 : <c:out value="${dl.title}"/></div>
						<div class="detail artist">아티스트 : <c:out value="${dl.artist}"/></div>
					</c:forEach>
					<c:forEach items="${areaList}" var="al">
						<div class="detail location">공연 위치 : ${al.locationName}</div>
					</c:forEach>
					<div class="detail dateTime">공연 일정 : ${date}&nbsp;${param.time}</div>
					<c:forEach items="${areaList}" var="al">
						<div class="detail area">공연 구역 : ${al.area}&nbsp;${al.price}원</div>
					</c:forEach>
						<div class="detail tTime">티켓팅 일정 : ${tDateTime}</div>
				</div>
			</div>
		</div>
		<div class="buttonBoxArea2">
			<div class="Header">대리인 리스트</div>
			<div class="detailBody">
				<div class="detailBody2">
					<table>
						<tr><td>닉네임</td><td>등급</td><td>성공</td><td>가능한 시간대</td><td>커미션비</td></tr>
					</table>
					<div class="com_list">
						<ul>
						  	<c:forEach items="${comList}" var="cl" varStatus="state">
                                <li onclick="saveCommissioner('${state.count}')" class="liclick_com">
                                    <div class="noClickDiv">${cl.cnickname}</div>
                                    <div class="noClickDiv">${cl.gname}</div>
                                    <div class="noClickDiv">${cl.success}</div>
                                    <div class="noClickDiv">${cl.registerdate}&nbsp;${cl.starttime}~${cl.endtime}</div>
                                    <div class="noClickDiv">${cl.com_price}원</div>
                                    <input type="hidden" value="${cl.mseq}" name="mseq2" id='${"Com"+=state.count}'>
                                    <input type="hidden" value="${cl.com_price}" name="com_price" id='${"com_price"+=state.count}'>
                                </li>
                            </c:forEach>
                        </ul>
                        <script>
                        
                        //대리인 선택했을 때 js로 가격얻는 함수
                        function saveCommissioner(num){
                     		var comPrice = document.getElementById('com_price'+num).value;
                     	 	var cprice = document.querySelector('.cprice');
                   		  	cprice.innerText = comPrice;
                     		var ticketPrice = document.getElementById('ticketPriceH').value;
                     	 	var totalPrice = document.querySelector('.totprice');
                     	 	totalPrice.innerText=Number(ticketPrice)+Number(comPrice)+'원';
                     	
                        	
                        	sessionStorage.removeItem('selectedCom');
                        	sessionStorage.setItem('selectedCom',document.getElementById('Com'+num).value);
                        }
                        
                        
                        
                            const non_Click1 = document.querySelectorAll(".liclick_com");

                            function handleClick2(event) {
                                non_Click1.forEach((e) => {
                                    e.classList.remove("click");
                                });
                                event.currentTarget.classList.add("click");
                            }

                            non_Click1.forEach((e) => {
                                e.addEventListener("click", handleClick2);
                            });
                        </script>
					</div>
				</div>
				<div class="detailBody3">
					<div class="priceBox">
						<div class="totprice p"></div><div class="p css1">=</div><div class="cprice p"></div><div class="p css1">+</div>
						<c:forEach items="${areaList}" var="al">
							<div class="tprice p">${al.price*quantity}</div>
							<input type="hidden" id="ticketPriceH" value="${al.price*quantity}">
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
		<section class=buttonSection>
		<div class=buttonBox>
			<div class=groupBox>
					<div class=buttonSmallBox>
						<input class="button1" type="button" value="뒤로" onclick="history.go(-1)">
					</div>
					<div class=buttonSmallBox>
						<input type="submit" class="button1 next" value="신청하기" onclick="return apply()"/>
					</div>
					<div class=buttonSmallBox>
						<input class="button1" type="button" value="장바구니" onclick="insertCart()">
					</div>
					<div class=buttonSmallBox>
						<input class="button1" type="button" value="다시 선택" onclick="refresh()">
					</div>
			</div>
		</div>
	</section>
</form>

<%@ include file="../../footer.jsp" %>