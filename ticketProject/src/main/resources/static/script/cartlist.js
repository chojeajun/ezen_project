/* 카트 리스트 삭제하기 버튼 */

function go_cart_delete() {

	var count = 0;

	if (document.frm.cartseq.length == undefined) {
		// 체크박스가 한 개인 경우
		if (document.frm.cartseq.checked == true)
			count++;
	} else {
		// 체크박스가 두 개이상인 경우 반복실행문을 이용하여 모든 체크박스를 하나씩 점검하여 체크된 갯수를 count합니다.
		for (var i = 0; i < document.frm.cartseq.length; i++) {
			if (document.frm.cartseq[i].checked == true)
				count++;
		}
	}

	if (count == 0) {
		alert('삭제할 항목을 선택하세요');
	} else {
		document.frm.action = "cartDelete";
		document.frm.submit();
		// jsp 파일에 있는 체크된  checkbox 들의 value들(cseq 값들)이 배열로 전송됩니다.
	}

}



/* 카트 리스트 주문하기 버튼 */

function go_cart_order() {
	var count = 0;
	if (document.frm.cartseq.length == undefined) {
		// 체크박스가 한 개인 경우
		if (document.frm.cartseq.checked == true){
			count++;
			cartseq = document.frm.cartseq.value;
			checkbox = document.frm.checkbox.value;
		}
			
	} else {
		// 체크박스가 두 개이상인 경우 반복실행문을 이용하여 모든 체크박스를 하나씩 점검하여 체크된 갯수를 count합니다.
		for (var i = 0; i < document.frm.cartseq.length; i++) {
			if (document.frm.cartseq[i].checked == true){
				count++;
				cartseq = document.frm.cartseq[i].value;
				checkbox = document.frm.checkbox[i].value;
			}
		}
		
	}

	if (count == 0) {
		alert('주문할 항목을 선택하세요');
	} else if(count > 1){
		alert('주문은 한개씩 가능합니다');	
	} else if (checkbox == 'No'){
		alert('대리인을 선택해주세요');
	} else {
		document.frm.action = "orderInsert?cartseq=" + cartseq;
		document.frm.submit();
		// jsp 파일에 있는 체크된  checkbox 들의 value들(cartseq 값들)이 배열로 전송됩니다.
	}

}
 

/* 카트리스트 수정하기 버튼*/

function go_cart_update() {
	
	sessionStorage.removeItem('selectedCseq');
	sessionStorage.removeItem('selectedDate');
	sessionStorage.removeItem('selectedTime');
	sessionStorage.removeItem('selectedArea');
	sessionStorage.removeItem('selectedQuantity');

	var count = 0;
	if (document.frm.cartseq.length == undefined) {
		// 체크박스가 한 개인 경우
		if (document.frm.cartseq.checked == true) {
			count++;
			var cseq = document.frm.cseq.value;
			var date = document.frm.contentdate.value;
			var time = document.frm.contenttime.value;
			var area = document.frm.area.value;
			var quantity = document.frm.quantity.value;
			var cartseq = document.frm.cartseq.value;
			localStorage.setItem('cartSeq', cartseq);
		}
	} else {
		for (var i = 0; i < document.frm.cartseq.length; i++) {
			if (document.frm.cartseq[i].checked == true) {
				count++;
				var cseq = document.frm.cseq[i].value;
				var date = document.frm.contentdate[i].value;
				var time = document.frm.contenttime[i].value;
				var area = document.frm.area[i].value;
				var quantity = document.frm.quantity[i].value;
				var cartseq = document.frm.cartseq[i].value;
				localStorage.setItem('cartSeq', cartseq);

			}
		}
	}


	sessionStorage.setItem('selectedCseq', cseq);
	sessionStorage.setItem('selectedDate', date);
	sessionStorage.setItem('selectedTime', time);
	sessionStorage.setItem('selectedArea', area);
	sessionStorage.setItem('selectedQuantity', quantity);


	if (count == 0) {
		alert('수정할 항목을 선택하세요');
	} else if (count >= 2) {
		alert('수정할 항목은 하나만 선택이 가능합니다');
	} else {
		
		document.frm.action = "applySelectCommissioner?cseq=" + cseq + "&date=" + date + "&time=" + time + "&area=" 
			+ area + "&quantity=" + quantity + "&cartseq=" + cartseq;
		document.frm.submit();
	}
}