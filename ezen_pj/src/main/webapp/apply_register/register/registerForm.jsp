<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<script type="text/javascript">
    function inputTimeColon(time) {
        // replace 함수를 사용하여 콜론( : )을 공백으로 치환한다.
        var replaceTime = time.value.replace(/\:/g, "");
        // 텍스트박스의 입력값이 4~5글자 사이가 되는 경우에만 실행한다.
        if(replaceTime.length >= 4 && replaceTime.length < 5) {
            var hours = replaceTime.substring(0, 2);      // 선언한 변수 hours에 시간값을 담는다.
            var minute = replaceTime.substring(2, 4);    // 선언한 변수 minute에 분을 담는다.
            // isFinite함수를 사용하여 문자가 선언되었는지 확인한다.
            if(isFinite(hours + minute) == false) {
                alert("문자는 입력하실 수 없습니다.");
                time.value = "00:00";
                return false;
            }
			// 두 변수의 시간과 분을 합쳐 입력한 시간이 24시가 넘는지를 체크한다.
            if(hours + minute > 2400) {
                alert("시간은 24시를 넘길 수 없습니다.");
                time.value = "24:00";
                return false;
            }
            // 입력한 분의 값이 60분을 넘는지 체크한다.
            if(minute > 60) {
                alert("분은 60분을 넘길 수 없습니다.");
                time.value = hours + ":00";
                return false;
            }
            time.value = hours + ":" + minute;
        }
     
    }
    
    function addTime() {
    	 sessionStorage.removeItem('date');
    	 sessionStorage.removeItem('startTime');
    	 sessionStorage.removeItem('endTime');
    	 
    	var date = document.getElementById("date").value;
        var startTime = document.getElementById("starttime").value;
        var endTime = document.getElementById("endtime").value;
        
        sessionStorage.setItem('date',date);
        sessionStorage.setItem('startTime',startTime);
        sessionStorage.setItem('endTime',endTime);
        
        if (date === "" || startTime === "" || endTime === "") {
            alert("날짜와 시작 시간, 종료 시간을 입력해주세요.");
            return;
        }
        
        var output = document.getElementById("output");
        var li = document.createElement("li");
        li.textContent = date + " " + startTime + " ~ " + endTime;
        output.appendChild(li);
        
        // 입력 후에 입력 필드를 초기화합니다.
        document.getElementById("date").value = "";
        document.getElementById("starttime").value = "";
        document.getElementById("endtime").value = "";
        
    }
    
    function gotime(){
    	
    	var date = sessionStorage.getItem('date');
    	var starttime = sessionStorage.getItem('startTime');
    	var endtime = sessionStorage.getItem('endTime');
    	alert(date);
    	document.registerForm.action = "ticket.do?command=registerTimeForm&date=" + date + "&starttime=" + starttime + "&endtime=" + endtime;
    	document.registerForm.submit();
    }
</script>

<form method="post" name="registerForm" style="margin: 0 auto;" encType="UTF-8">
    <!-- <input type="hidden" name="command" value="registerTimeForm"> -->

    <section name="registerBody" id="bodyBox_register">
        <div class="registerBox" name="registerBoxform">
            <table class="registerDate" name="registerDate">
                <tr class="tableHead">
                    <th><h1>날짜와 시간</h1></th>
                </tr>
                <tr>
                <td class="datetime">
                    <div class="datetimeBox">
                        <input type="date" name="date" id="date">
                        <input type="text" id="starttime" name="starttime" class="timeBox" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5"/> ~
                        <input type="text" id="endtime" name="endtime" class="timeBox" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5"/>
                    </div>
                </td>
                </tr>
            </table>
            <button type="button" id="addButton" onclick="addTime()">추가</button>
            <input type="submit" id="submit_Button" value="저장" onClick="gotime()">
        </div>
    </section>
</form>

<ul id="output" name></ul>

<%@ include file="../../footer.jsp"%>
