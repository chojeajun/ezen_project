<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calendar</title>
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 10px;
        }

        th {
            background-color: lightgray;
        }
    </style>
</head>
<body>
    <h1>Calendar</h1>

    <%-- 현재 날짜와 시간 가져오기 --%>
    <%@ page import="java.util.*" %>
    <% Calendar cal = Calendar.getInstance();
       int year = cal.get(Calendar.YEAR);
       int month = cal.get(Calendar.MONTH) + 1;
       int day = cal.get(Calendar.DAY_OF_MONTH);
       int hour = cal.get(Calendar.HOUR_OF_DAY);
       int minute = cal.get(Calendar.MINUTE);
    %>

    <%-- 날짜 선택 폼 --%>
    <form method="post" action="">
        <label for="date">날짜 선택:</label>
        <input type="date" id="date" name="date" value="<%= year %>-<%= month %>-<%= day %>" required>
        <br>
        <label for="time">시간 선택:</label>
        <input type="time" id="time" name="time" value="<%= String.format("%02d", hour) %>:<%= String.format("%02d", minute) %>" required>
        <br>
        <input type="submit" value="선택">
    </form>

    <%-- 선택된 날짜와 시간 출력 --%>
    <% if (request.getMethod().equalsIgnoreCase("POST")) {
           String selectedDate = request.getParameter("date");
           String selectedTime = request.getParameter("time");
    %>
        <h2>선택된 날짜와 시간:</h2>
        <p>날짜: <%= selectedDate %></p>
        <p>시간: <%= selectedTime %></p>
    <% } %>
</body>
</html>
