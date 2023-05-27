<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../header.jsp" %>

<form name="frm" method="post" class="form">

	<section class="body_Choose">
		<header>
			<div class="titlearea_Choose" tabindex="-1">
				<div class="hello">안녕하세요!</div>
				<div class="afterHello">티켓을 원하시면 신청을, 티켓팅에 도전하시면 일정 등록을 해주세요.</div>
			</div>
		</header>
			<div class="bodyarea_Choose">
				<div class="mainArea_UserChoice">
					<div class="buttonBoxArea">
						<div id=buttonBox>
							<button type="button"	class="button" name="applyButton" onClick="go_apply()">
								<div class="box">
									<div class="box_img">
										<div class="Image">
											<img	src="images/anr/ticket.png" alt="티켓팅 신청" width="250" height="250">
										</div>
									</div>
									<div class="box_text">
										<div class="smallBox_text">
											<div class="box_textTitle">
												<div class="textTitle">티켓팅 신청하기</div>
											</div>
											<div class="box_textContent">
												<div class="textContent">
												원하시는 공연 정보와 대리인을 선택해주세요.
												</div>
											</div>
										</div>
									</div>
								</div>
							</button>
						</div>
					</div>
					<div class="buttonBoxArea">
						<div id=buttonBox>
							<button type="button"	class="button" name="registerButton" onClick="go_register()">
								<div class="box">
									<div class="box_img">
										<div	class="Image">
											<img src="images/anr/calendar.png"	alt="일정 등록" width="300" height="300"></div>
										</div>
									<div class="box_text">
										<div class="smallBox_text">
											<div class="box_textTitle">
												<div class="textTitle">일정 등록하기</div>
											</div>
											<div class="box_textContent">
												<div class="textContent">
												티켓팅이 가능하신 날짜와 시간을 등록해주세요.
												</div>
											</div>
										</div>
									</div>
								</div>
							</button>
						</div>
					</div>
				</div>
			</div>
	</section>
</form>

<%@ include file="../footer.jsp" %>