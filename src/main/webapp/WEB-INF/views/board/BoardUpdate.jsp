<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value='/resources/css/board/boardwrite.css'/>">
<body>
	<header id="header">
		<h1>
			<a href="../main"><img src="<c:url value='/resources/images/logo_transparent.png'/>"
				style="left: 85px; top: 0; width: 100px; opacity: 0.6;">danmat</a>
		</h1>
		<nav class="links">
			<ul>
				<li><a href="notice">소식</a></li>
				<li><a class="p-2" href="../ranking">랭킹</a></li>
				<li><a class="p-2" href="../board">게시판</a></li>
				<%
				String userid = (String) session.getAttribute("userid");
				if (userid == null) {
				%>
				<li><a href="signUp">회원가입</a></li>
				<li><a href="logIn" class="loginBtn"
					style="font-size: 20px; color: #c2bde3;">로그인</a></li>
				<%
				} else {
				%>
				<li><a class="btn loginBtn" href="../logOut"
					style="font-size: 20px; color: #c2bde3; width: 100%;">로그아웃</a></li>
				<%
				}
				%>
			</ul>
		</nav>
	</header>

	<div class="container" style="margin-top: 100px;">
		<div class="page-title">
			<div class="container">
				<h3>게시글 수정</h3>
			</div>
		</div>
			<form method="post" action="boardUpdate">
			<div class="form-group">
				<label for="title">제목</label> <input class="form-control"	name="title" value="${board.title }" style="font-size: 25px;">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" name="content" style="font-size: 25px;">${board.content}</textarea>
			</div>
			<div class="form-group">
				<label for="writer">작성자</label> <input value="${board.userId}"	class="form-control" name="userId" readonly="readonly"	style="font-size: 25px;">
			</div>
			
			<!-- 현재 url이 board/board/Update?board_idx=4 로 되어있어서
			url을 수정해서 다른 수정 페이지로 이동할 수 있어 url을 통해 수정화면 이동해도
			작성자 id와 로그인된 id(또는 관리자) 계정 아닐시 수정,취소 버튼 보이지 않도록 수정  -->
			<c:set var="userId" value="${board.userId}" />
			<%
						String userId = (String) pageContext.getAttribute("userId");
						//if (userid != null) {
							if (userid.contentEquals(userId) || userid.contentEquals("admin")) {
						%>
			<button type="submit" class="btn btn-default" style="color: #fff; background: #6f61c9;">수정
				<input type="hidden" name="board_idx" value="${board.board_idx }"/>
			</button>
			</form>
			<form method="get" action="../board">
			<button type="submit" class="btn btn-default" style="color: #fff; background: #6f61c9;">취소</button>
			</form>
						<%
							} else { %>
								<button type="button" class="btn btn-default" style="background: #6f61c9; margin-top: 30px;">
									<a href="../board" style="color: #fff; text-decoration: none;">목록</a>
								</button>
							<% // }
						}
						%>
	</div>
</body>
</html>
