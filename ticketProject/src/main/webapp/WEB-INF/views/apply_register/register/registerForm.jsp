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
            if(minute > 59) {
                alert("분은 60분을 넘길 수 없습니다.");
                time.value = hours + ":00";
                return false;
            }
            time.value = hours + ":" + minute;
        }
     
    }
    
    function addTime() {
        var date = document.getElementById("date").value;
        var date = date.replace("-","").replace("-","");
        var startTime = document.getElementById("starttime").value;
        var endTime = document.getElementById("endtime").value;

        if (date === "" || startTime === "" || endTime === "") {
            alert("날짜와 시작 시간, 종료 시간을 입력해주세요.");
            return;
        }

        var item = {
            date: date,
            startTime: startTime,
            endTime: endTime
        };

        var items = sessionStorage.getItem('items');
        if (items) {
            items = JSON.parse(items);
            items.push(item);
        } else {
            items = [item];
        }

        sessionStorage.setItem('items', JSON.stringify(items));

        displayItems();

        // 입력 후에 입력 필드를 초기화합니다.
        document.getElementById("date").value = "";
        document.getElementById("starttime").value = "";
        document.getElementById("endtime").value = "";
    }

    function deleteItem(index) {
        var items = sessionStorage.getItem('items');
        if (items) {
            items = JSON.parse(items);
            items.splice(index, 1);
            sessionStorage.setItem('items', JSON.stringify(items));
            displayItems();
        }
    }

    function displayItems() {
        var items = sessionStorage.getItem('items');
        var output = document.getElementById("output");
        output.innerHTML = "";

        if (items) {
            items = JSON.parse(items);

            for (var i = 0; i < items.length; i++) {
                var item = items[i];

                var li = document.createElement("li");
                li.textContent = item.date + " " + item.startTime + " ~ " + item.endTime;

                var deleteButton = document.createElement("button");
                deleteButton.textContent = "삭제";
                deleteButton.className = "deleteButton";
                deleteButton.onclick = (function (index) {
                    return function () {
                        deleteItem(index);
                    };
                })(i);

                li.appendChild(deleteButton);
                output.appendChild(li);
            }
        }
    }

    function gotime() {
        var items = sessionStorage.getItem('items');
        if (!items) {
            alert("리스트에 아이템을 추가해주세요.");
            return;
        }

        var itemsArray = JSON.parse(items);
        var queryString = "";
        for (var i = 0; i < itemsArray.length; i++) {
            var item = itemsArray[i];
            queryString += "date=" + item.date + "&starttime=" + item.startTime + "&endtime=" + item.endTime;
            if (i !== itemsArray.length - 1) {
                queryString += "&";
            }
        }

        document.registerForm.action = "registerTimeForm?" + queryString;
        document.registerForm.submit();
        
     	// 폼 제출 후 sessionStorage를 초기화합니다
        sessionStorage.removeItem('items');
        displayItems();
    }


</script>

<style>
body { font-family: Arial, sans-serif; }

form { margin: 0 auto; }

.registerBox {margin: 20px; padding: 20px; background-color: #EEEEEE; border-radius: 5px;
text-align : center;}

.registerDate {width: 100%; border-collapse: collapse; margin-bottom: 20px; }

.datetime_tit {background-color: #FFA5A5; color: #fff; padding: 10px; color: #000;}

.datetimeBox {  align-items: center; justify-content: center; }

.datetimeBox input[type="date"], .datetimeBox input[type="text"] { margin: 0 5px; }

.timeBox { width: 80px; }

.datetimeBox input[type="date"],
.datetimeBox input[type="text"] {padding: 5px; margin-right: 10px; margin : 0 auto; text-align: center;}

#addButton,
#submit_Button {padding: 10px 20px; background-color: #FFE3E3;  border: none;
border-radius: 5px; cursor: pointer; font-weight: bold;}

#addButton {margin-right: 10px;}

#output {list-style-type: none; margin: 0; padding: 0;}

#output li {margin-bottom: 10px;}

#output li label {float:none;}

.deleteButton {margin-left: 10px; padding: 5px 10px; background-color: #f44336; color: #000;
 border: none; border-radius: 5px; cursor: pointer;}

input[type="date"]::-webkit-calendar-picker-indicator {background-color: #f44336;}
.registerDate .date{background-color: #fff;}
</style>

<form method="post" name="registerForm" style="margin: 0 auto;" encType="UTF-8">
    <!-- <input type="hidden" name="command" value="registerTimeForm"> -->

    <section name="registerBody" id="bodyBox_register">
        <div class="registerBox" name="registerBoxform">
            <div class="registerDate" name="registerDate">
                <div class="datetime">
                   <h1 class="datetime_tit">날짜와 시간</h1>
                    <div class="datetimeBox">
                        <input type="date" name="date" id="date">
                        <input type="text" id="starttime" name="starttime" class="timeBox" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5"/> ~
                        <input type="text" id="endtime" name="endtime" class="timeBox" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5"/>
                    </div>
                </div>
            </div>
            <ul id="output"></ul>
			<div class="btn_box">
				<button type="button" id="addButton" onclick="addTime()">추가</button>
				<input type="submit" id="submit_Button" value="저장" onClick="gotime()">
			</div>
		</div>
    </section>
</form>



<%@ include file="../../footer.jsp"%>