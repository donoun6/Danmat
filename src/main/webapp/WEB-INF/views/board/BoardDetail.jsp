<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  비회원 글쓰기 클릭시 로그인 화면으로 이동 -->
<%
String userid = (String) session.getAttribute("userid");
if (userid == null) {
	response.sendRedirect("../logIn");
	return;
}
%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
<title>Danmat 게시판</title>
<link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/pricing/">

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="<c:url value='/resources/css/common/nomalize.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/common/default.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/common/bootstrap.min.css'/>">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->

<link rel="stylesheet" href="<c:url value='/resources/css/common/initial.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/board/boardwrite.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>

<body>
	<div class="bigWrap">
		<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-1 mb-1">
			<div class="my-0 mr-md-auto font-weight-normal topLogo">
				<img src="<c:url value='/resources/images/logo_transparent.png'/>"
					alt=""> <a href="main"><span style="font-weight: bold;">danmat</span></a>
			</div>
			<nav class="navMenu">
				<a class="p-2" href="../notice">소식</a> <a class="p-2"
					href="../ranking">랭킹</a> <a class="p-2" href="../board">게시판</a>
					
				
				<%
				
				if (userid == null) {
				%>
				<a class="p-2" href="signUp">회원가입</a> <a class="btn loginBtn"
					href="logIn">로그인</a>
				<%
				} else if (userid.contentEquals("admin")) {
				%>
				<span class="p-2" style="font-size: 20px; color: white;"> <a
					class="admin" href="../mgMain">관리자페이지</a></span> <a class="btn loginBtn"
					href="../logOut">로그아웃</a>
				<%
				} else {
				%>
				<span class="p-2" style="font-size: 20px; color: white;"><%=userid%>님</span>
				<a class="btn loginBtn" href="../logOut">로그아웃</a>
				<%
				}
				%>
			</nav>
		</div>
		<div class="container" style="max-width: 80%;">
			<div class="card-deck mb-3 text-center">
				<div class="wrap" style="margin-top: -60px;">
					<div class="container" style="margin-top: 100px;">
						<div class="page-title">
							<div class="container">
								<h3>${board.title}</h3>
							</div>
						</div>

						<div class="form-group">
							<label for="title">제목</label> <input class="form-control"
								name="title" value="${board.title}" readonly="readonly"
								style="font-size: 25px;">
						</div>
						<div class="form-group">
							<label for="content">내용</label>
							<textarea class="form-control" rows="5" name="content"
								readonly="readonly" style="font-size: 25px;">${board.content}</textarea>
						</div>
						<div class="form-group">
							<label for="writer">작성자</label> <input value="${board.userId}"
								class="form-control" name="userId" readonly="readonly"
								style="font-size: 25px;">
						</div>
						<c:set var="userId" value="${board.userId}" />
						<%
						String userId = (String) pageContext.getAttribute("userId");
						if (userid != null) {
							if (userid.contentEquals(userId) || userid.contentEquals("admin")) {
						%>
						<form method="Get" action="boardUpdate" style="width: 0; display: inline;">
							<button type="submit" class="btn btn-default"
								style="background: #6f61c9; color: #fff; margin-top: 30px;">수정</button>
							<input type="hidden" name="board_idx" value="${board.board_idx}" />
						</form>

						<form method="Get" action="boardDelete" style="width: 0; display: inline;">
							<!-- <button type="button" class="btn btn-default" style="background: #6f61c9;">  -->
							<button type="submit" class="btn btn-default"
								style="background: #6f61c9; color: #fff; margin-top: 30px;">삭제</button>
							<input type="hidden" name="board_idx" value="${board.board_idx}"/>
						</form>
						<%
							}
						}
						%>
						<button type="button" class="btn btn-default" style="background: #6f61c9; margin-top: 30px;">
							<a href="../board" style="color: #fff;">목록</a>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>