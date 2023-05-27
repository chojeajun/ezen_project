<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<form method="post" name="registerForm" style="margin: 0 auto;">
    <!-- <input type="hidden" name="command" value="registerTimeForm"> -->

    <section name="registerBody" id="bodyBox_register">
        <div class="registerBox" name="registerBoxform">
            <table class="registerDate" name="registerDate">
                <tr class="tableHead">
                    <th><h1>날짜와 시간</h1></th>
                </tr>
                <td class="datetime">
                    <div class="datetimeBox">
                        <input type="datetime-local" name="starttime" id="starttime"> ~ 
                        <input type="datetime-local" name="endtime" id="endtime">
                    </div>
                </td>
            </table>
            <button type="button" id="addButton">추가</button>
        </div>
    </section>
</form>

<ul id="output"></ul>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function() {
    $("#addButton").click(function() {
        var startTime = $("#starttime").val();
        var endTime = $("#endtime").val();
        
        if (startTime === "" || endTime === "") {
            alert("날짜와 시간을 선택해주세요.");
            return;
        }
        
        var listItem = $("<li>").text("시작일시: " + startTime + ", 종료일시: " + endTime);
        var deleteButton = $("<button>").text("삭제");
        deleteButton.on("click", function() {
            listItem.remove();
        });
        listItem.append(deleteButton);
        $("#output").append(listItem);
        
        // 필요한 작업을 수행할 수 있습니다.
        // 예를 들면, 저장된 데이터를 서버에 전송하거나 다른 처리를 수행하는 등의 작업입니다.
        
        // 선택한 날짜와 시간 초기화
        $("#starttime").val("");
        $("#endtime").val("");
    });
});
</script>

<%@ include file="../../footer.jsp"%>
