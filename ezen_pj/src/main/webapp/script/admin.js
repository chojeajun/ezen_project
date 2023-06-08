/**
 * 
 */

function workerCheck() {
	if (document.frm.workId.value == "") {
		alert("아이디를 입력하세요.");
		return false;
	} else if (document.frm.workPwd.value == "") {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	return true;
}





/* 회원리스트 검색 */
function go_search(comm) {
	if(document.frm.key.value == "") {
		alert("검색어를 입력하세요");
		return;
	} 
	var url ="ticket.do?command=" + comm + "&page=1"; // 검색어로 검색한 결과의 1페이지로 이동
	document.frm.action = url;
	document.frm.submit();
}


/* 회원리스트 전체보기 */
function go_total(comm) {
	document.frm.key.value = "";
	document.frm.action = "ticket.do?command=" + comm + "&page=1";
	document.frm.submit();
}


/* product update */
//  리뷰수정 버튼 클릭  // 수정 폼으로 이동

function go_pro_upd(cseq) {
	console.log(cseq);
	
	var url = "ticket.do?command=adminContentEditForm&cseq=" + cseq
	location.href = url
}


/* 공연 추가 */
function go_wrt(){
	document.frm.action = "ticket.do?command=adminContentInsertForm";
	document.frm.submit();
}

function insert_content(){
	var date = document.getElementById("date").value;
    var date = date.replace("-","").replace("-","");
    
	document.frm.action = "ticket.do?command=adminContentInsert";
	document.frm.submit();
}


/* 상품수정  */
function pro_update() {
	if(confirm("수정 ㄱ?")) {
		return;
//		document.formm.action ="ticket.do?command=adminContentEdit";
//		document.formm.submit();
	}
	
}





