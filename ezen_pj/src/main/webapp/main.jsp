<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>


<div id="bestContent">
	<div class="title">
		<h1>Best Content</h1>
	</div>
	<div id="bestcontentback">
		<div class="imgview1">
			<div class="imgview2">
				<div id="imgview3">
					<c:forEach items="${ bestContent }" end="3" var="bestContentVO">
						<div class="imgview4">
							<div id="img1">
								<a href="ticket.do?command=detailcontent"> <img
									src="${ bestContentVO.image }" />
								</a>
							</div>
							<div id="title1">
								<h3>${ bestContentVO.title }</h3>
							</div>
							<div id="apply">
								<a href="ticket.do?command=detail">
									<h3>신청하기</h3>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="remote1">
				<ul>
					<li id="btn2">▶</li>
				</ul>
			</div>
			<div id="remote2">
				<ul>
					<li id="btn1">◀</li>
				</ul>
			</div>
		</div>

	</div>

</div>

<div id="concert">
	<div class="title">
		<h1>Concert</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ concert }" end="3" var="concertVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ concertVO.cseq }">
					<img src="${ concertVO.image }" />
				</a>
				<h1 style="line-height:30px; height:90px;">${ concertVO.title }</h1>
				<h3 style="width: 258px; height: 40px; font-size: 25px; margin-left: 10px; line-height: 40px; font-weight: bold; 
					background: black; color: papayawhip; cursor: pointer;">신청하기</h3>
			</div>
		</c:forEach>
	</div>
</div>

<div id="musical">
	<div class="title">
		<h1>Musical</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ musical }" end="3" var="musicalVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ musicalVO.cseq }">
					<img src="${ musicalVO.image }" />
				</a>
				<h1 style="line-height:30px; height:90px;">${ musicalVO.title }</h1>
				<h3
					style="width: 258px; height: 40px; font-size: 25px; margin-left: 10px; line-height: 40px; font-weight: bold;
                              color: red; border:2px dashed black; cursor: pointer; ">신청하기</h3>
			</div>
		</c:forEach>
	</div>
</div>

<div id="success">
	<div class="title">
		<h1>성&nbsp;공&nbsp;내&nbsp;역</h1>
	</div>
	<div class="successlist1">
		<div class="successlistrealview">
			<div class="successlistview">
				<c:forEach items="${ success }" end="3" var="successVO">
					<div class="successitem">
						<div class="successlisttitle">
							<h2>${ successVO.title }</h2>
						</div>
						<div class="successlistid">
							<h2>${ successVO.sucseq }.&nbsp;${ successVO.id }</h2>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="btn3">
			<ul>
				<li id="successleft">◁</li>
				<li id="successright">▷</li>
			</ul>
		</div>
	</div>

</div>


<%@ include file="footer.jsp"%>