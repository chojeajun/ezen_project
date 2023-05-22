<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="../../header.jsp" %>

<style type="text/css">
#bodyBox_apply{border:1px solid black;}
.applyBox{border:1px solid black; background:lightblue;}
.applyContent{border:1px solid black; background: lightsalmon;}
.applyTitleBox{border:1px solid blue;}
.applyTitle{border:1px solid black;}
.applyBodyBox{border:1px solid black; background:coral;}
.categoryBox{border:1px solid black;}
.contentNameBox{border:1px solid black;}
.dateBox{border:1px solid black;}
.timeBox{border:1px solid black;}
.detailBox{border:1px solid black;}
.seatingChartBox{border:1px solid black;}
</style>

<section name="applyBody" id="bodyBox_apply">
	<div class="applyBox" name="applyBoxForm">
	
		<div class="applyContent" name="applyContent">
			<div class="applyTitleBox">
				<ul class="applyTitle">
					<li>공연 종류</li>
					<li>공연</li>
					<li>날짜</li>
					<li>시간</li>
					<li>상세 정보</li>
					<li>좌석 구역</li>
				</ul>
			</div>
			<div class="applyBodyBox">
				<div class="categoryBox">
					<ul>
						<li>전체</li>
						<li>콘서트</li>
						<li>뮤지컬</li>
						<li>스포츠</li>
						<li>페스티벌</li>
						<li>전시/행사</li>
					</ul>
				</div>
				<div class="contentNameBox">
						<ul>
							<li><a href="#" onclick="return false">contentVO.title</a></li>
						</ul>
					<c:forEach items="${contentList }" var="contentVO">
					</c:forEach>
				</div>
				<div class="dateBox">
							<ul>
								<li><a href="#" onclick="return false">contentVO.tdate</a></li>
							</ul>
						<c:forEach items="${contentList}" var="contentVO">
						</c:forEach>
				</div>
				<div class="timeBox">
								<ul>
									<li><a href="#" onclick="return false">contentVO.?</a></li>
									<!-- 날짜를 선택하고 그 날짜에 맞는 시간을 선택하고 싶은데 어떻게 해야할까 -->
								</ul>
					<c:forEach items="${contentList}" var="contentVO">
					</c:forEach>
				</div>
				<div class="detailBox">
					<!-- 선택한 공연 정보(포스터, 제목, 위치, 아티스트) -->
					<div class="contentDetail">
						<div class="poster">
							<img src="">
						</div>
						<div class="detail">
							<span class="title">contentList.title</span>
							<span class="location">{contentList.location}</span>
							<span class="artist">{contentList.artist}</span>
						</div>
					</div>
				</div>
				<div class="seatingChartBox">
					<!-- 좌석 선택-좌석도 위에 보여주고 밑에 좌석 선택 -->
					<div class="seatingChartDetailBox">
						<div class="seatingChartImg">
							<img alt="" src="">
						</div>
						<c:forEach  items="${contentList}" var="contentVO">
							<div class="seatingChartAreaBox">
								<ul>
									<li><a href="#" onclick="return false">{contentVO.area}</a></li>
								</ul>
							</div>
						</c:forEach>
					</div>
				</div>

				
		</div>
	</div>
</section>
	
	

