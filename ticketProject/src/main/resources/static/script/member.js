/* 로그인 체크 */

function loginCheck() {
	if (document.loginFrm.id.value == "") {
		alert("아이디는 필수입력사항입니다.");
		document.loginFrm.id.focus();
		//return false;
	} else if (document.loginFrm.pwd.value == "") {
		alert("비밀번호는 필수입력사항입니다.");
		document.loginFrm.pwd.focus();
		//return false;
	} else {
		document.loginFrm.submit();
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
	  document.contractFrm.action = 'joinForm';
      document.contractFrm.submit(); 
   }
}

function idcheck() {// 스크립트가 늦게먹거나 적용이 안되는 경우가 종종 생김
	if(document.formm.id.value == "") {
		alert("아이디를 입력하고 중복체크를 진행하세요");
		document.formm.id.focus();
		return;
	}
	var url ="idCheckForm?id=" + document.formm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";
	window.open(url , "IdCheck" , opt);
}


function idok( userid ){
	opener.formm.id.value = userid;
	opener.formm.reid.value = userid;
	self.close();
}
/* 주소찾기  */
//function post_zip(){	
//	   	var url = "ticket.do?command=findZipNum";
//	    var opt = "toolbar=no, menubar=no, resizable=no, width=600, ,scrollbars=no";
//	    opt = opt + " height=300,top=300,left=300";
//	    window.open(url,"findZipNum",opt);
//}

/* 검색값을 주소에 입력 */
//function result(zip_num,sido,gugun,dong , bunji){	
//		// 함수 호출 형태 - result('123-123','서울시', '서대문구', '대현동' , '~~번지' )
//	    opener.document.joinForm.zip_num.value = zip_num;
//	    opener.document.joinForm.address1.value = sido+" " +gugun+" "+dong + " "+ bunji;
//	    self.close();
//	}

/* 생년월일 유효성체크 */
function birthCheck() {
	if(document.joinForm.birth.value == "" || document.joinForm.birth.value.length != 10) {
		alert("생년월일을 형식에 맞게 입력해 주세요.");
		alert(document.joinForm.birth.value.length);
       document.joinForm.birth.focus();     
	}	
}

function go_update(){
	if( document.m_frm.provider.value=='kakao' ){
		document.m_frm.action='memberUpdateKakao';
	}else{ 
		document.m_frm.action='memberUpdate';
	}

	document.m_frm.submit();
}






/* 회원정보 저장 */
// 마이바티스로 구성하면서 스크립트가 빠진
// 하지만 프론트단에서 밸리데이션 체크가 가능하다면 원칙적으로는 프론트에서 하는것이맞음
// 프론트에서 체크가 가능한데 굳이 빽단까지 데이터를 가지고 들어가서 튕겨낼 이유는 없음
//function join_insert(){ 
//   if( document.joinForm.id.value == "" ){
//      alert("아이디를 입력하여 주세요.");
//      document.joinForm.id.focus();     
//     }else if(document.joinForm.reid.value != document.joinForm.id.value){	
//	   alert("아이디 중복확인을 하지 않았습니다.");
//       document.joinForm.id.focus();	 
//     }else if(document.joinForm.pwd.value == ""){	
//	  alert("비밀번호를 입력해 주세요.");
//      document.joinForm.pwd.focus();	      
//     }else if(document.joinForm.pwd.value != document.joinForm.pwdCheck.value){	
//	  alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//      document.joinForm.pwdCheck.focus();	      
//     }else if(document.joinForm.name.value ==""){	
//	  alert("이름을 입력해 주세요.");
//      document.joinForm.name.focus();
//     }else if(document.joinForm.email.value ==""){	
//	  alert("이메일을 입력해주세요 주세요.");
//      document.joinForm.email.focus();
//     }else if(document.joinForm.phone.value ==""){	
//	  alert("전화번호를 입력해 주세요.");
//      document.joinForm.phone.focus();
//     }else if(document.joinForm.zip_num.value ==""){	
//	  alert("우편번호를 입력해주세요.");
//      document.joinForm.zip_num.focus();
//     }else if(document.joinForm.address1.value ==""){	
//	  alert("주소를 입력 주세요.");
//      document.joinForm.address1.focus();
//     }else{
//	     document.joinForm.action = "ticket.do";
//         document.joinForm.submit();
//   }
//}


/* updateForm 회원정보 수정 */
//function go_update(){ 
//   if( document.joinForm.pwd.value == "" ){
//      alert("비밀번호를 입력해 주세요.");
//      document.joinForm.pwd.focus();     
//     }else if(document.joinForm.pwd.value != document.joinForm.pwdCheck.value){	
//	   alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//       document.joinForm.pwd.focus();	 
//     }else if(document.joinForm.name.value == ""){	
//	  alert("이름을 입력해 주세요.");
//      document.joinForm.name.focus();	      
//     }else if(document.joinForm.nickname.value == ""){	
//	  alert("별명을 입력해 주세요.");
//      document.joinForm.nickname.focus();	      
//     }else if(document.joinForm.email.value ==""){	
//	  alert("이메일을 입력해 주세요.");
//      document.joinForm.email.focus();
//     }else if(document.joinForm.phone.value ==""){	
//	  alert("전화번호를 입력해 주세요.");
//      document.joinForm.phone.focus();
//     }else if(document.joinForm.birth.value =="" ){	
//	  alert("생년월일을 형식에 맞게 입력해 주세요");
//      document.joinForm.birth.focus();
//     }else if(document.joinForm.zip_num.value ==""){	
//	  alert("우편번호를 입력해 주세요.");
//      document.joinForm.zip_num.focus();
//     }else if(document.joinForm.address2.value ==""){	
//	  alert("상세주소를 입력해 주세요.");
//      document.joinForm.address1.focus();
//     }else{
//	     document.joinForm.action = "ticket.do";
//         document.joinForm.submit();
//   }
//}







