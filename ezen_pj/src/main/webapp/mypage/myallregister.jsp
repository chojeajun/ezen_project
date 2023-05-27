<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="css/myregister.css?ver=1" />

<!----------------------------------현 시간 날짜 변수에 저장(date, time 사용)---------------------------------------->
<!-- 현 날짜 시간을 java.util.Date()로 가져옴 변수 today에 저장 -->
<c:set var="today" value="<%=new java.util.Date()%>" />

<!-- today 저장한 날짜를 date변수에 저장 -->
<c:set var="date">
   <fmt:formatDate value="${ today }" pattern="yyyy-MM-dd" />
</c:set>
<%-- <fmt:formatDate var="today" value="${ now }" pattern="yyyy-MM-dd"/> --%>

<!-- today 저장한 시간을 time변수에 저장 -->
<c:set var="time">
   <fmt:formatDate value="${ today }" pattern="HH:mm" />
</c:set>
<%-- <fmt:formatDate value="${ hour }" type="time" timeStyle="short"/> --%>

<!-------------------------------------------------------------------------------------------------------------------->

<div id="myRegister_box">
   <div id="buttons">
      <input type="button" id="myregister_button" value="나의 대리할 내역"
         onclick="location.href='ticket.do?command=myRegister'" /> <input
         type="button" id="myregistered_button" value="나의 대리한 내역"
         onclick="location.href='ticket.do?command=myRegistered'" /> <input
         type="button" id="myregisterall_button" value="나의 전체 대리 내역"
         onclick="location.href='ticket.do?command=myAllRegister'" /> <input
         type="button" id="go_home_button" value="마이 페이지로"
         onclick="location.href='ticket.do?command=mypage'" />
   </div>

   <h2>나의 전체 대리 내역</h2>
   <table id="registeredtable">
      <tr>
         <th>번호</th>
         <th>닉네임</th>
         <th>대리 날짜</th>
         <th>시작 시각</th>
         <th>마감 시각</th>
      </tr>
      <c:forEach items="${ myAllRegister }" var="myRegisterVO">


         <!----------------------------------register 날짜 시작시간 끝날시간 저장(mrDate, mrEndTime, mrStartTime 사용)-------------------------->
         <!-- register 날짜 mrDate 변수이름으로 만듦 jsp로 사용 -->
         <fmt:parseDate var="registerDate"
            value="${ myRegisterVO.registerdate }" pattern="yyyyMMdd" />
         <fmt:formatDate var="mrDate" value="${ registerDate }"
            pattern="yyyy-MM-dd" />


         <!-- register 시작시간 mrStartTime 변수이름으로 만듦 jsp로 사용 -->
         <fmt:parseDate var="registerStartTime"
            value="${ myRegisterVO.starttime }" pattern="HH:mm" />
         <fmt:formatDate var="mrStartTime" value="${ registerStartTime }"
            pattern="HH:mm" />


         <!-- register 끝날시간 mrEndTime 변수이름으로 만듦 jsp로 사용 -->
         <fmt:parseDate var="registerEndTime"
            value="${ myRegisterVO.endtime }" pattern="HH:mm" />
         <fmt:formatDate var="mrEndTime" value="${ registerEndTime }"
            pattern="HH:mm" />
         <!------------------------------------------------------------------------------------------------------------------------------------------->




         <tr>
            <td>${ myRegisterVO.rtseq }</td>
            <c:forEach items="${ member }" var="memberNickname">
               <td>${ memberNickname }</td>
            </c:forEach>
            <td>${ mrDate }</td>
            <td>${ mrStartTime }</td>
            <td>${ mrEndTime }</td>
         </tr>


      </c:forEach>
   </table>
</div>

<%@ include file="../footer.jsp"%>