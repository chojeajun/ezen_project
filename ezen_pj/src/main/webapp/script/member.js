/* 로그인 체크 */

function loginCheck() {
	if (document.loginFrm.id.value == "") {
		alert("아이디는 필수입력사항입니다.");
		document.loginFrm.id.focus();
		return false;
	} else if (document.loginFrm.pwd.value == "") {
		alert("비밀번호는 필수입력사항입니다.");
		document.loginFrm.pwd.focus();
		return false;
	} else {
		return true;
	}
}
/* formForm 이동하기 
	회원가입약관 동의하기 버튼 
 */

function go_next(){

   if( document.contractFrm.check_on1.checked == false ){
      alert('회원 약관에 동의하셔야 회원으로 가입이 가능합니다');
   }else if(document.contractFrm.check_on2.checked == false) {
	   alert('개인정보처리방침 약관에 동의하셔야 회원으로 가입이 가능합니다');
   } else {
	  document.contractFrm.action = 'ticket.do?command=joinForm';
      document.contractFrm.submit(); 
   }
}

/* id 중복체크  */
function checkid(){	
if(document.joinForm.id.value==""){		
	alert("아이디를 입력하고 중복체크를 진행하시오");
	document.joinForm.id.focus();
	return;
}
var url = "ticket.do?command=idCheckForm&id=" + document.joinForm.id.value;
var opt = "toolbar=no, menubar=no, resizable=no, width=600, height=400,scrollbars=no";
window.open(url,"checkid",opt);
}


/* 아이디 사용하기 버튼 클릭 */
function idok(userid){	
    opener.joinForm.id.value = userid;
    opener.joinForm.reid.value = userid;
    self.close();
}










