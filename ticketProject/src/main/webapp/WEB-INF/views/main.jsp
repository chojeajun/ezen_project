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
					<c:forEach items="${ bestList }" end="3" var="bestContentVO">
						<div class="imgview4">
							<div id="img1">
								<a href="contentDetail?cseq=${ bestContentVO.CSEQ }">
									<img src="${ bestContentVO.IMAGE }" />
								</a>
							</div>
							<div id="title1">
								<h3>${ bestContentVO.TITLE }</h3>
							</div>
							<div id="apply">
								<a
									href="applyContentSelect?cseq=${ bestContentVO.CSEQ }
										&category=${ bestContentVO.CATEGORY }&locationNum=${ bestContentVO.LOCATIONNUM }">
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
		<h1>New List</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ newList }" end="3" var="concertVO">
			<div id="item">
				<a href="contentDetail?cseq=${ concertVO.CSEQ }">
					<img src="${ concertVO.IMAGE }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ concertVO.TITLE }</h1>
				<a
					href="applyContentSelect?cseq=${ concertVO.CSEQ }&category=${ concertVO.CATEGORY }
						&locationNum=${ concertVO.LOCATIONNUM }"><h3>신청하기</h3>
				</a>
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
				<c:forEach items="${ successList }" end="3" var="successVO">
					<div class="successitem">
						<div class="successlisttitle">
							<a
								href="successView?sucseq=${ successVO.SUCSEQ }">
								<h2>${ successVO.TITLE }</h2>
							</a>
						</div>
						<div class="successlistid">
							<h2>${ successVO.SUCSEQ }.&nbsp;${ successVO.ID }</h2>
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

<div id="bannerList">
	<div id="view">
		<div id="imgs">
			<c:forEach items="${ bannerList }" var="bannerVO">
				<img src="${ bannerVO.IMAGE }" />
			</c:forEach>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>