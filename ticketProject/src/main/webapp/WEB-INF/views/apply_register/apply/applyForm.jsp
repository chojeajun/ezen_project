<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>


<form name="frm" class="form">
<section name="applyBody" id="bodyBox_apply">
	<div class="applyBox" name="applyBoxForm">
	

						<!-- 이 페이지 예전에 쌤한테 질문했을 때 ajax로 해결할 수 있다고 함
							지금은 하나 누르면 페이지가 새로 뜨는데 한페이지에서 정보 뜨게 하면 최고일 것 같음 
							파이팅 누군지는 몰라도 - 나다
						-->
			<div class="applyTitleBox">
			
				<ul class="applyTitle">
					<li>공연 종류</li>
					<li>공연</li>
					<li>상세 정보</li>
					<li>날짜</li>
					<li>시간</li>
					<li>좌석 구역</li>
				</ul>
			</div>
			<div class="applyBodyBox">
				<div class="categoryBox bodyBox">
					<ul>
						<li id="category" onclick="select_category(0)">전체</li>
						<li id="category" onclick="select_category(1)">콘서트</li>
						<li id="category" onclick="select_category(2)">뮤지컬</li>
						<li id="category" onclick="select_category(3)">스포츠</li>
						<li id="category" onclick="select_category(4)">페스티벌</li>
						<li id="category" onclick="select_category(5)">전시/행사</li>
					</ul>
				</div>
				<div class="contentNameBox bodyBox">
						<ul>
						<!--이 부분은 for문 돌릴 때 js로 클릭한 li의 아이디를 넘겨야 하는데 for문으로 돌리니까 전부 id가 같아서
						모든 정보가 다 넘겨졌었음
						그래서 varStatus="state" 한다음에 id에 state.count붙이고 ${state.count}로 순서 숫자 넘겨서,,,,
						글로 설명하기 어렵다
						js 펑션에서 cseq+숫자로 아이디 지정하는 거임
						무슨 소린지 모르겠으면 걍 카톡해 머리아프다...............-->
					<c:forEach items="${contentList}" var="contentVO" varStatus="state">
							<li onclick="saveCseq('${state.count}')"><a href="applyContentSelect?cseq=${contentVO.CSEQ}&category=${category}&locationNum=${contentVO.LOCATIONNUM}">
							${contentVO.TITLE}</a>
								<input type="hidden" value="${contentVO.CSEQ}" name="cseq" id='${"cseq"+=state.count}' class="li_count">
							</li>
					</c:forEach>
						</ul>
				</div>
				<div class="detailBox bodyBox">
					<!-- 선택한 공연 정보(포스터, 제목, 위치, 아티스트) -->
					<div class="contentDetail">
						<div class="poster">
					<c:forEach items="${contentTableList}" var="contentTL">
							<img src="${contentTL.IMAGE}" width="220px" height="294">
					</c:forEach>
						</div>
						<div class="detail">
					<c:forEach items="${contentTableList}" var="contentTL">
							<div class="header1">공연명</div><div class="box3">${contentTL.TITLE}</div>
					</c:forEach>
					<c:forEach items="${contentTableList}" var="contentTL">
							<div class="header1">아티스트</div><div class="artist box3">${contentTL.ARTIST}</div>
					</c:forEach>
					<c:forEach items="${contentLocationList}" end="0" var="contentLL">
							<div class="header1">공연 위치</div><div class="location box3">${contentLL.LOCATIONNAME}</div>
					</c:forEach>
						</div>
					</div>
				</div>
				<div class="dateBox bodyBox">
				<!-- 얘도 위의 반복문하고 똑같은 방법으로 함 begin=1을 한 이유는 0부터 시작하게 그냥 두니까
				공백도 떠서 1부터 시작하게 함 -->
							<ul class="date">
						<c:forEach items="${contentDateList}" var="contentDL" varStatus="state">
								<li onclick="saveDate('${state.count}')"><a href="applyContentSelect?cseq=${contentDL.CSEQ}&category=${category}&locationNum=${locationNum}&contentDate=${contentDL.CONTENTDATE}">
								<input type="hidden" value="${contentDL.CONTENTDATE}" name="date" id='${"date"+=state.count}'>
								<fmt:formatDate value="${contentDL.CONTENTDATE}" pattern="yyyy-MM-dd" /></a>
								</li>
						</c:forEach>
							</ul>
				</div>
				<div class="timeBox bodyBox">
					<ul>
						<c:forEach items="${contentTimeList}" var="contentTil" varStatus="state">
							<li onclick="saveTime('${state.count}')" class="liclick_time">${contentTil.CONTENTTIME}
							<input type="hidden" value="${contentTil.CONTENTTIME}" name="time" id='${"time"+=state.count}' >
							</li>
						</c:forEach>
					</ul>
				</div>
				
				<script>
					//챗선생이 해주심
					//클릭하면 색 바뀌게 하는거!
					//time
					const non_Click = document.querySelectorAll(".liclick_time");
					function handleClick1(event) {
					  // div에서 모든 "click" 클래스 제거
					  non_Click.forEach((e) => {
					    e.classList.remove("click");
					  });
					  // 클릭한 div만 "click"클래스 추가
					  event.target.classList.add("click");
					}
					non_Click.forEach((e) => {
					  e.addEventListener("click", handleClick1);
					});
					</script>
				
				<div class="seatingChartBox bodyBox">
					<!-- 좌석 선택-좌석도 위에 보여주고 밑에 좌석 선택 -->
					<div class="seatingChartBoxSeparate">
						<div class="seatingChartDetailBox">
							<div class="seatingChartImg">
							<!-- 좌석도도 content_loc_seat_view에서 가져와야함 -->
					<c:forEach items="${contentLocationList}" end="0" var="contentLL">
								<img alt="좌석도" src="${contentLL.AREAIMAGE}" width="220" height="220">
					</c:forEach>
							</div>
						<div class="AreaBox">
									<ul>
							<c:forEach  items="${contentAreaList}" var="contentAL" varStatus="state">
										<li onclick="saveArea('${state.count}')" class="liclick_area">
										<div class="area1">${contentAL.AREA}</div><div class="price1">${contentAL.PRICE}원</div>
										<input type="hidden" value="${contentAL.AREA}" name="area" id='${"area"+=state.count}'>
										</li>
							</c:forEach>
									</ul>
					
					<script>
					//area
					    const nonClick = document.querySelectorAll(".liclick_area");

					    function handleClick3(event) {
					        nonClick.forEach((e) => {
					            e.classList.remove("click");
					        });
					        event.currentTarget.classList.add("click");
					    }
					
					    nonClick.forEach((e) => {
					        e.addEventListener("click", handleClick3);
					    });
						 
					</script>

						</div>
						</div>
					</div>
					<div class="quantityBox">
						<div class="quantityTitle">수량</div>
						<div class="quantityArea">
							<input type="number" min="1" max="10" id="quantity" onchange="saveQuantity()">
						</div>
					</div>
				</div>
			</div>
		</div>		
	</section>
	<section class=buttonSection>
		<div class=buttonBox>
			<div class=groupBox>
					<div class=buttonSmallBox>
						<input class="button1" type="button" value="뒤로" onclick="goMain()">
					</div>
					<div class=buttonSmallBox>
						<input type="submit" class="button1 next" value="다음" onclick="return applyFormCheck()"/>
					</div>
					<div class=buttonSmallBox>
						<input class="button1" type="button" value="장바구니" onclick="insertCart()">
					</div>
					<div class=buttonSmallBox>
						<input class="button1" type="button" value="다시 선택" onclick="go_apply()">
					</div>
			</div>
		</div>
	</section>
</form>	
	


<%@ include file="../../footer.jsp" %>