function loginCheck(){
	if( document.frm.id.value.length==0){
		alert("아이디를 입력하세요");
		document.frm.userid.focus();
		return false;
	}
	if( document.frm.pwd.value.length==0){
		alert("비밀번호를 입력하세요");
		document.frm.pwd.focus();
		return false;
	}
	return true;
}



function idCheck(){
	if( document.frm.id.value==""){
		alert("아이디를 입력하세요");
		document.frm.id.focus();
		return;
	}
	var inputid = document.frm.id.value;
	var opt = "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=200";
	window.open("board.do?command=idcheck&userid=" + inputid , "idcheck", opt);
}



function idok( id  ){
	opener.frm.id.value = id;
	opener.frm.reid.value = id;
	self.close();
}


function joinCheck(){
	if( document.frm.id.value.length<4){
		alert("아이디는 4글자 이상이어야 합니다.");  
		document.frm.id.focus();	
		return false;
	}else if( document.frm.id.value != document.frm.reid.value){
		alert("아이디 중복체크를 하지 않았습니다"); 	
		document.frm.id.focus();	 
		return false;
	}else if( document.frm.name.value.length==0){
		alert("이름은 필수입력사항입니다");		
		document.frm.name.focus();		
		return false;
	}else if( document.frm.pwd.value==""){
		alert("비밀번호는 반드시 입력하여야 합니다");		
		document.frm.pwd.focus();		
		return false;
	}else if( document.frm.pwd.value != document.frm.pwd_check.value){
		alert("비밀번호와 확인이 일치하지 않습니다");		
		document.frm.pwd_check.focus();		
		return false;
	}else{ 	return true; }
}




function qnaCheck(){
	if( document.frm.pwd.value=="" ){
		alert("비밀번호는 수정/삭제시에 필요합니다");
		document.frm.pwd.focus();
		return false;
	}
	if( document.frm.title.value=="" ){
		alert("제목은 필수사항입니다");
		document.frm.title.focus();
		return false;
	}
	if( document.frm.content.value=="" ){
		alert("내용을 입력해주세요");
		document.frm.content.focus();
		return false;
	}
	return true;
}


function reply_check(){
	if( document.frm_reply.reply.value=='' ){
		alert("댓글 내용을 입력하세요");
		document.frm_reply.reply.focus();
		return false;
	}
	return true;
}



function checkPass( qnanum,  popupWinName ){
	console.log(qnanum);
	var url = "ticket.do?command=qnaPassForm&qseq=" + qnanum;
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
	window.open( url, popupWinName , opt);
}



function passCheck(){
	if (document.frm.pwd.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		document.frm.pwd.focus();
		return false;
	}
	return true;
}