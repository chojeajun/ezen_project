/**
 * 
 */

/* apply_Or_Register.jsp의 js*/
function go_apply() {

	sessionStorage.removeItem('selectedCseq');
	sessionStorage.removeItem('selectedDate');
	sessionStorage.removeItem('selectedTime');
	sessionStorage.removeItem('selectedArea');
	sessionStorage.removeItem('selectedQuantity');
	sessionStorage.removeItem('selectedCom');
	document.anrFrm.action = 'categorySelect?category=0';
	// 전체보기 카테고리 선택한 페이지로 연결함categorySelect
	document.anrFrm.submit();
}

function go_apply_hoon() {
	sessionStorage.removeItem('selectedCseq');
	sessionStorage.removeItem('selectedDate');
	sessionStorage.removeItem('selectedTime');
	sessionStorage.removeItem('selectedArea');
	sessionStorage.removeItem('selectedQuantity');
	sessionStorage.removeItem('selectedCom');
}

function go_register() {
	document.anrFrm.action = 'registerForm';
	document.anrFrm.submit();

}


/* 여기부턴 applyForm.jsp의 js*/

function select_category(category) {
	switch (category) {
		case 0: location.href = 'categorySelect?category=0';
			sessionStorage.removeItem('selectedCseq');
			sessionStorage.removeItem('selectedDate');
			sessionStorage.removeItem('selectedTime');
			sessionStorage.removeItem('selectedArea');
			sessionStorage.removeItem('selectedQuantity');
			break;
		case 1: location.href = 'categorySelect?category=1';
			sessionStorage.removeItem('selectedCseq');
			sessionStorage.removeItem('selectedDate');
			sessionStorage.removeItem('selectedTime');
			sessionStorage.removeItem('selectedArea');
			sessionStorage.removeItem('selectedQuantity');
			break;
		case 2: location.href = 'categorySelect?category=2';
			sessionStorage.removeItem('selectedCseq');
			sessionStorage.removeItem('selectedDate');
			sessionStorage.removeItem('selectedTime');
			sessionStorage.removeItem('selectedArea');
			sessionStorage.removeItem('selectedQuantity');
			break;
		case 3: location.href = 'categorySelect?category=3';
			sessionStorage.removeItem('selectedCseq');
			sessionStorage.removeItem('selectedDate');
			sessionStorage.removeItem('selectedTime');
			sessionStorage.removeItem('selectedArea');
			sessionStorage.removeItem('selectedQuantity');
			break;
		case 4: location.href = 'categorySelect?category=4';
			sessionStorage.removeItem('selectedCseq');
			sessionStorage.removeItem('selectedDate');
			sessionStorage.removeItem('selectedTime');
			sessionStorage.removeItem('selectedArea');
			sessionStorage.removeItem('selectedQuantity');
			break;
		case 5: location.href = 'categorySelect?category=5';
			sessionStorage.removeItem('selectedCseq');
			sessionStorage.removeItem('selectedDate');
			sessionStorage.removeItem('selectedTime');
			sessionStorage.removeItem('selectedArea');
			sessionStorage.removeItem('selectedQuantity');
			break;
	}
}


function saveCseq(num) {
	sessionStorage.setItem('selectedCseq', document.getElementById('cseq' + num).value);
	sessionStorage.removeItem('selectedDate');
	sessionStorage.removeItem('selectedTime');
	sessionStorage.removeItem('selectedArea');
	sessionStorage.removeItem('selectedQuantity');
}
function saveDate(num) {
	sessionStorage.setItem('selectedDate', document.getElementById('date' + num).value);
	sessionStorage.removeItem('selectedTime');
	sessionStorage.removeItem('selectedArea');
	sessionStorage.removeItem('selectedQuantity');
}
function saveTime(num) {
	sessionStorage.setItem('selectedTime', document.getElementById('time' + num).value);
	/*document.getElementById('time'+num).style.backgroundColor = 'red';*/
	sessionStorage.removeItem('selectedArea');
	sessionStorage.removeItem('selectedQuantity');

}

function saveArea(num) {
	sessionStorage.setItem('selectedArea', document.getElementById('area' + num).value);
	sessionStorage.removeItem('selectedQuantity');
	var Myelement = document.getElementById("quantity");
	Myelement.value = "";
}
function saveQuantity() {
	sessionStorage.setItem('selectedQuantity', document.getElementById('quantity').value);
}

function applyFormCheck() {
	var cseq = sessionStorage.getItem('selectedCseq');
	var date = sessionStorage.getItem('selectedDate');
	var time = sessionStorage.getItem('selectedTime');
	var area = sessionStorage.getItem('selectedArea');
	var quantity = sessionStorage.getItem('selectedQuantity');
	if (cseq == null) {
		alert("공연을 선택해 주세요.");
		return false;
	} else if (date == null) {
		alert("공연 날짜를 선택해 주세요.");
		return false;
	} else if (time == null) {
		alert("공연 시간을 선택해 주세요.");
		return false;
	} else if (area == null) {
		alert("공연 구역을 선택해 주세요.");
		return false;
	} else if (quantity == null) {
		alert("티켓 수량을 선택해 주세요.");
		return false;
	} else {
		document.frm.action = 'applySelectCommissioner?cseq=' + cseq + '&date=' + date + '&time=' + time + '&area=' + area + '&quantity=' + quantity;
		document.frm.submit();

	}
}

function goMain() {
	document.frm.action = '/';
	document.frm.submit();
}


function insertCart() {
	var cseq = sessionStorage.getItem('selectedCseq');
	var date = sessionStorage.getItem('selectedDate');
	var time = sessionStorage.getItem('selectedTime');
	var area = sessionStorage.getItem('selectedArea');
	var quantity = sessionStorage.getItem('selectedQuantity');
	var cartseq = localStorage.getItem('cartSeq');

	if (cseq == null) {
		alert("공연을 선택해 주세요.");
		return false;
	} else if (date == null) {
		alert("공연 날짜를 선택해 주세요.");
		return false;
	} else if (time == null) {
		alert("공연 시간을 선택해 주세요.");
		return false;
	} else if (area == null) {
		alert("공연 구역을 선택해 주세요.");
		return false;
	} else if (quantity == null) {
		alert("티켓 수량을 선택해 주세요.");
		return false;
	} else {
		var url;
		if (cartseq == null) {
			if (sessionStorage.getItem('selectedCom') != null) {
				var mseq2 = Number(sessionStorage.getItem('selectedCom'));
				url = 'applyCart?cseq=' + cseq + '&date=' + date + '&time=' + time + '&area=' + area + '&quantity=' + quantity + '&mseq2=' + mseq2;
			} else {
				url = 'applyCart?cseq=' + cseq + '&date=' + date + '&time=' + time + '&area=' + area + '&quantity=' + quantity;
			}
		} else {
			var mseq2 = Number(sessionStorage.getItem('selectedCom'));
			url = 'applyCart?cseq=' + cseq + '&date=' + date + '&time=' + time + '&area=' + area + '&quantity=' + quantity + '&mseq2=' + mseq2 + '&cartseq=' + cartseq;
		}

		var opt = "toolbar=no, menubar=no, resizable=no, width=448, height=400, scrollbars=no";
		window.open(url, "InsertCart", opt);
		localStorage.removeItem('cartSeq');
	}
}



function refresh() {
	sessionStorage.removeItem('selectedCom');
}

function apply() {
	var cseq = sessionStorage.getItem('selectedCseq');
	var date = sessionStorage.getItem('selectedDate');
	var time = sessionStorage.getItem('selectedTime');
	var area = sessionStorage.getItem('selectedArea');
	var quantity = sessionStorage.getItem('selectedQuantity');
	var mseq2 = sessionStorage.getItem('selectedCom');
	if (mseq2 == null) {
		alert("대리인을 선택해 주세요.");
		return false;
	} else {
		document.frm.action = 'apply?cseq=' + cseq + '&date=' + date + '&time=' + time + '&area=' + area + '&quantity=' + quantity + '&mseq2=' + mseq2;
		return true;
	}
}

function go_main() {
	sessionStorage.removeItem('selectedCseq');
	sessionStorage.removeItem('selectedDate');
	sessionStorage.removeItem('selectedTime');
	sessionStorage.removeItem('selectedArea');
	sessionStorage.removeItem('selectedQuantity');
	sessionStorage.removeItem('selectedCom');
	document.registerForm.action = "/";
	document.registerForm.submit();
}

function go_mypage() {
	document.registerForm.action = "mypage";
	document.registerForm.submit();
}
