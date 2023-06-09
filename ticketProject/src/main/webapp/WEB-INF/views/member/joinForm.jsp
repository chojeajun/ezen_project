<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼입니다</title>

</head>
<body>
	<form method="post" name="formm" style="margin: 0 auto;" action="join">
		<div id="join_box">
			<div class="join_title">
				<span>회원정보 입력</span> 홈페이지에서 사용할 정보를 입력해주세요.
			</div>
			<div class="join_content">
				<ul>
					<li> <span>* </span> <input type="text" name="id" placeholder="아이디" value="${dto.id}" />
						<input type="hidden" name="reid" value="${reid} ">
						<input type="button" value="중복체크" class="chk_id" onclick="idcheck();">
					</li>
					<li> <span>* </span> <input type="password" name="pwd" placeholder="비밀번호를 입력해주세요." /></li>
					<li> <span>* </span> <input type="password" name="pwdCheck" placeholder="비밀번호 재확인." /><br></li>
					<li> <span>* </span> <input type="text" name="name" placeholder="이름을 입력해주세요." value="${dto.name}" /></li>
					<li> <select name="gender" class="gender">
							<option value="무관">성별 선택</option>
							<option value="남">남</option>
							<option value="여">여</option>
					</select></li>
					<li> <input type="text" name="nickname" placeholder="별명을 입력해주세요." /></li>
					<li> <span>* </span> <input type="text" name="email" placeholder="이메일을 입력해주세요." value="${dto.email}" /></li>
					<li> <span>* </span> <input type="text"  name="phone" placeholder="(입력예 : 010-7777-3333)" value="${dto.phone}" /></li>
					<li><span>* </span> <input type="text" class="birthday" name="birth" placeholder="(입력예 yyyy-mm-dd)">
					<li>
						 <span>* </span> <input type="text"  id="sample6_postcode"  name="zip_num" placeholder="우편번호를 입력해주세요." readonly="readonly" />
						<input type="button" style="position:absolute; right:0;" onclick="sample6_execDaumPostcode()" class="dup" value="우편번호찾기"><br>
					</li>
        			<li><input type="text" id="sample6_address" size="50" name="address1" value="${ dto.address1 }" readonly><br></li>
			        <li><input type="text" id="sample6_detailAddress" name="address2" value="${ dto.address2 }" placeholder="상세주소 입력" size="50"><br></li>
        			<li><input type="text" id="sample6_extraAddress" name="address3" value="${ dto.address3 }" readonly><br></li>
				</ul>
				
				<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		
		<script>
		    function sample6_execDaumPostcode() {
		        new daum.Postcode( {
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수
		
		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    document.getElementById("sample6_extraAddress").value = extraAddr;
		                
		                } else {
		                    document.getElementById("sample6_extraAddress").value = '';
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('sample6_postcode').value = data.zonecode;
		                document.getElementById("sample6_address").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("sample6_detailAddress").focus();
		            }
		        }).open();
		    }
		</script><br>
				
				<div class="notice">
					<p><strong>*</strong>표시는<strong>필수</strong>입력 사항입니다</p>
				</div>
			</div>
			<div id="buttons">
				<input type="submit" value="회원가입" class="submit join_ok" >
				<input type="button" value="취소" class="cancel join_cancel" >
			</div>
		</div>
	</form>
</body>
</html>

<%@ include file="../footer.jsp"%>