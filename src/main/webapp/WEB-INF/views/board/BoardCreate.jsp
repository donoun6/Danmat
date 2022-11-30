<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--  비회원 글쓰기 클릭시 로그인 화면으로 이동 -->
<%
String userid = (String) session.getAttribute("userid");
if (userid == null) {
	response.sendRedirect("../logIn");
	return;
}
%>
<c:set var="userid" value="${userid}"/>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
<title>게시글 작성</title>

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
		<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-1 mb-1">
			<div class="my-0 mr-md-auto font-weight-normal topLogo">
				<img src="<c:url value='/resources/images/logo_transparent.png'/>" alt="">
					<a href="../main"><span style="font-weight: bold;">danmat</span></a>
			</div>
			<nav class="navMenu">
				<a class="p-2" href="../notice">소식</a>
				<a class="p-2" href="../ranking">랭킹</a>
				<a class="p-2" href="../board">게시판</a>
				<c:choose>
					<c:when test="${userid eq null }">
					<a class="p-2" href="signUp">회원가입</a> <a class="btn loginBtn" href="logIn">로그인</a>
					</c:when>
					<c:when test="${userid == 'admin' }">
						<span class="p-2" style="font-size: 20px; color: white;">
						<a	class="admin" href="../mgMain">관리자페이지</a></span>
						<a class="btn loginBtn"	href="../logOut">로그아웃</a>
					</c:when>
				<c:otherwise>
				<span class="p-2" style="font-size: 20px; color: white;"><%=userid%>님</span>
				<a class="btn loginBtn" href="../logOut">로그아웃</a>
				</c:otherwise>
				</c:choose>
			</nav>
		</div>
		<div class="container" style="max-width: 80%;">
			<div class="card-deck mb-3 text-center">
				<div class="wrap" style="margin-top: -60px;">
					<div class="container" style="margin-top: 100px;">
						<div class="page-title">
							<div class="container">
								<h3>게시판 글쓰기</h3>
							</div>
						</div>
							
							<c:choose>
								<c:when test="${userid eq 'admin' }">
									<form method="post" modelAttribute="notice">
										<div class="form-group">
											<div style="display: inline-block;">
												<input type="checkbox" id="notice" name="notice" style="-webkit-appearance: checkbox;" value="Y">공지 등록&nbsp;
											</div>
											<div style="display: inline-block; width:85%;">
												<label for="writer" style="margin-top: 20px;">작성자</label>
												<input value="<%=userid%>" readonly="readonly" type="text" class="form-control" id="writer" name="userId" style="font-size: 25px; width: 90%;"/>
											</div>
											<label for="title">제목</label>
											<input type="text" class="form-control" id="title" name="title" maxlength="30"
												required="required" pattern=".{4,100}"
												style="font-size: 25px; height: 34px;" placeholder="제목(4글자 이상) 입력">
										</div>
									
										<div class="form-group">
											<label for="content" style="margin-top: 20px;">내용</label>
											<textarea class="form-control" rows="5" id="content"
												maxlength="300" name="content" placeholder="최대 300자 까지 입력이 가능합니다."
												required="required" style="font-size: 25px;"></textarea>
										</div>
										
										<button type="submit" class="btn btn-default"
											style="background: #6f61c9; color: #fff; margin-top: 30px;">등록
										</button>

										<button type="button" class="btn btn-default"
											style="background: #6f61c9; margin-top: 30px;">
											<a href="../board" style="color: #fff; text-decoration: none;">취소</a>
										</button>
									</form>
								</c:when>
								<c:when test="${userid ne null and userid ne 'admin'}">
									<form method="post" modelAttribute="board">
										<div class="form-group">
											<div style="width: 100%; display: inline-block;">
												<label for="writer" style="margin-top: 20px;">작성자</label>
												<input value="<%=userid%>" readonly="readonly" type="text" class="form-control" id="writer" name="userId" style="font-size: 25px;"/>
											</div>
											<label for="title">제목</label>
											<input type="text" class="form-control" id="title" name="title" minlength="2" maxlength="30"
												required="required" pattern=".{4,100}"
												style="font-size: 25px; height: 34px;" placeholder="제목(4글자 이상) 입력">
										</div>
									
										<div class="form-group">
											<label for="content" style="margin-top: 20px;">내용</label>
											<textarea class="form-control" rows="5" id="content"
												maxlength="300" name="content" placeholder="최대 300자 까지 입력이 가능합니다."
												required="required" style="font-size: 25px;"></textarea>
										</div>

										<input type="hidden" id="notice", name="notice" value="N"/>
										<button type="submit" class="btn btn-default"
											style="background: #6f61c9; color: #fff; margin-top: 30px;">등록</button>
										<button type="button" class="btn btn-default"
											style="background: #6f61c9; margin-top: 30px;">
											<a href="../board" style="color: #fff; text-decoration: none;">취소</a>
										</button>
									</form>
								</c:when>
							</c:choose>			
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
