<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.88.1">
  <title>랭킹</title>

  <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/pricing/">



  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="<c:url value='/resources/css/common/nomalize.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/common/default.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/common/bootstrap.min.css'/>" >



  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
  </style>


  <!-- Custom styles for this template -->
  <link rel="stylesheet" href="<c:url value='/resources/css/ranking/ranking.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/common/initial.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
  
</head>

<body>
  <div class="bigWrap">
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-1 mb-1">
      <div class="my-0 mr-md-auto font-weight-normal topLogo"><img src="<c:url value='/resources/images/logo_transparent.png'/>" alt=""><a href="main"><span>danmat</span></a></div>
      <nav class="navMenu">
      	<a class="p-2" href="game">게임</a>
        <a class="p-2" href="ranking">랭킹</a>
        <a class="p-2" href="board">게시판</a>
        <%
        String userid = (String)session.getAttribute("userid");
        if(userid == null){ %>
        <a class="p-2" href="signUp">회원가입</a>
        <a class="btn loginBtn" href="logIn" st>로그인</a>
        <% }else if (userid.contentEquals("admin")){ %>
            <span class="p-2" style="font-size: 20px; color: white; line-height: 20px;"><a class="admin" href="mgMain">관리자페이지</a></span>
            <a class="btn loginBtn" href="logOut">로그아웃</a>
       <% }else { %>     
        	<span class="p-2" style="font-size: 20px; color: white; line-height: 20px;"><%= userid %>님</span>
            <a class="btn loginBtn" href="logOut">로그아웃</a>
       <% } %>
      </nav>
    </div>
    <div class="container" style="min-width: 70%; margin-top: -30px;">
      <div class="card-deck mb-3 text-center">
        <div class="wrap">
			<article class="post">
				<table class="main" style="border-radius: 40px; overflow: hidden; border:1px solid rgba(160, 160, 160, 0.3);">
					<tbody style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
						<tr>
						<th style="width: 140px;">등수</th>
						<th>닉네임</th>
						<th>몇점</th>
					</tr>
					<tr>
						<td class="blink-effect"><img src="<c:url value='/resources/images/ranking/exp1.png'/>" style="width: 32px;"></td>
						<td class="number1">동환<img src="<c:url value='/resources/images/ranking/fuckcrown.png'/>" style="width: 30px;"></td>
						<td  class="number1">10000점</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/resources/images/ranking/exp2.png'/>" style="width: 28px;"></td>
						<td class="number2">동규</td>
						<td class="number2">2점</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/resources/images/ranking/exp3.png'/>" style="width: 24px;"></td>
						<td class="number3">경태</td>
						<td class="number3">1점</td>
					</tr>
					<tr>
						<td>4</td>
						<td>4</td>
						<td>4</td>
					</tr>
					<tr>
						<td>5</td>
						<td>5</td>
						<td>5</td>
					</tr>
					<tr>
						<td>6</td>
						<td>6</td>
						<td>6</td>
					</tr>
					<tr>
						<td>7</td>
						<td>7</td>
						<td>7</td>
					</tr>
					<tr>
						<td>8</td>
						<td>8</td>
						<td>8</td>
					</tr>
					<tr>
						<td>9</td>
						<td>9</td>
						<td>9</td>
					</tr>
					<tr>
						<td>10</td>
						<td>10</td>
						<td>10</td>
					</tr>
			</tbody>
				</table>
				<div class="paging">
					<ul class="paging" id="pagination">
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">6</a></li>
						<li><a href="#">7</a></li>
						<li><a href="#">8</a></li>
						<li><a href="#">9</a></li>
						<li><a href="#">10</a></li>
						<li><a href="#">...</a></li>
					</ul>
				</div>
			</article>
        </div>
      </div>
    </div>
  </div>

</body>

</html>
