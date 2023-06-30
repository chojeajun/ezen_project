<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<style>
body {font-family: Arial, sans-serif; background-color: #EEEEEE; padding: 20px;}

.container {max-width: 800px; margin: 0 auto; text-align: center;}

h2 {margin-bottom: 20px;}

.table {width: 100%; border-collapse: collapse; margin-bottom: 20px;}

.table thead th {background-color: #FFA5A5; color: #000; padding: 10px; font-weight: bold;
						text-align: center;}

.table tbody td {padding: 10px; border-bottom: 1px solid #ddd;}

#main_Button, #mypage_Button {display: inline-block; padding: 10px 20px; background-color: #FFE3E3;
 text-color: #000; border: none; cursor: pointer; margin-right: 10px; font-weight: bold;}

#main_Button:hover, #mypage_Button:hover {background-color: #555;}

</style>

<form method="post" name="registerForm" style="margin: 0 auto;">
	<div class="container">
	    <h2>대리인 등록 현황</h2>
	
	    <table class="table">
	        <thead>
	            <tr>
	                <th>날짜</th>
	                <th>시작시간</th>
	                <th>종료시간</th>
	            </tr>
	        </thead>
	        <tbody>
	        		<c:forEach items="${ register }" var="regi">
	                <tr>
	                    <td>${regi.registerdate}</td>
	                    <td>${regi.starttime}</td>
	                    <td>${regi.endtime}</td>
	                </tr>
	                </c:forEach>
	        </tbody>
	    </table>
	    <input type="submit" id="main_Button" value="홈으로" onClick="go_main()">
	    <input type="submit" id="mypage_Button" value="마이페이지로" onClick="go_mypage()">
	</div>
</form>

<%@ include file="../../footer.jsp" %>