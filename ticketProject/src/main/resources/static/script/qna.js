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

//  리뷰수정 버튼 클릭  // 수정 폼으로 이동
function go_upd(qseq) {
	var url = "ticket.do?command=qnaEditForm&rseq=" + qseq
	location.href = url
}

// 리뷰 댓글수정 수정 팝업? 
function go_rep_edit(qseq, repseq) {
	console.log( "리뷰번호" + rseq);
	console.log("댓글번호" + repseq);
	var url ="ticket.do?command=qnaReplyEditForm&rseq="  + qseq;
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
	window.open(url, opt );
}


//function checkPass(boardnum, popupWinName) {
//	var url = "board.do?command=boardPassForm&num=" + boardnum;
//	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
//	window.open(url, popupWinName, opt);
//}

function reply_update() {
	if(document.formm.content.value == "") {
		alert("내용 ㄱ?");
	} else {
		document.formm.action = "ticket.do?command=qnaReplyEdit"
		document.formm.submit();
	}
}


// 작성 아이디와 로그인한 아이디가 일치하지 않는다면 수정하기 버튼 자체를 노출시키지 않음
function qna_update() {
	if(document.formm.subject.value == "" ) {
		alert("제목은 필수 입력 사항입니다.");
		document.formm.subject.focus();
		//return false;
	}else if(document.formm.content.value == "") {
		alert("내용은 필수 입력 사항입니다.");
		document.formm.content.focus();
		//return false;
	} else {
		if(confirm("수정 ㄱ?")) {
			document.formm.action ="ticket.do?command=qnaEdit";
			document.formm.submit();
		}
	}
}


/* 리뷰 삭제  */
function review_delete(qseq) {
	console.log(document.formm.qseq);
	console.log(document.formm.qseq.value);
	if(confirm("정말 삭제하시겠습니까?")) {
		//return;
		document.formm.action ="ticket.do?command=qnaDelete&qseq=" + qseq;
		document.formm.submit();
	}
}



//function review_chk_pwd(reviewrseq, popupWinName) {
//	var url = "ticket.do?command=reviewChkForm&rseq=" + reviewrseq;
//	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
//	window.open(url, popupWinName, opt);
//}




/* review reply 리뷰댓글작성 */
function reply_chk() {
	if(document.rev_formm.reply.value == "") {
		alert("댓글 내용을 입력해주세요");
		document.rev_formm.reply.focus();
		return false;
	}
	return true;
}

function go_wrt(){
	document.frm.action = "qnaWrite";
	document.frm.submit();
}