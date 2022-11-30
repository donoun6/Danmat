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
  <title>Danmat</title>

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
  <link rel="stylesheet" href="<c:url value='/resources/css/common/initial.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>

<body>
  <div class="bigWrap">
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-1 mb-1">
      <div class="my-0 mr-md-auto font-weight-normal topLogo"><img src="<c:url value='/resources/images/logo_transparent.png'/>" alt=""><span>danmat</span></div>
      <nav class="navMenu">
      	<a class="p-2" href="game">게임</a>
        <a class="p-2" href="ranking">랭킹</a>
        <a class="p-2" href="board">게시판</a>
        <%
        String userid = (String)session.getAttribute("userid");
        if(userid == null){ %>
        <a class="p-2" href="signUp">회원가입</a>
        <a class="btn loginBtn" href="logIn" style="line-height: 30px;">로그인</a>
        <% }else if (userid.contentEquals("admin")){ %>
            <span class="p-2" style="font-size: 20px; color: white;"><a class="admin" href="mgMain">관리자페이지</a></span>
            <a class="btn loginBtn" href="logOut" style="line-height: 30px;">로그아웃</a>
       <% }else { %>     
        	<span class="p-2" style="font-size: 20px; color: white;"><%= userid %>님</span>
            <a class="btn loginBtn" href="logOut" style="line-height: 30px;">로그아웃</a>
       <% } %>
      </nav>
    </div>
    <div class="container">
      <div class="card-deck mb-3 text-center">
        <div class="wrap">
          <div class="cute1"></div>
          <div class="cute2"></div>
          <div class="cute3"></div>
          <div class="cute4"></div>
          <a class="svg1" href="#"><svg version="1.1" id="svg-ani" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 60.602 53.825" style="enable-background:new 0 0 60.602 53.825;" xml:space="preserve">
            <path class="st1" d="M16.484,23.521c-3.484-2.573-7.297-4.166-11.7-2.021c-4.738,2.314-6.377,7.973-2.992,12.175
              c6.201,7.695,19.675,11.557,29.139,10.129c-3.03-8.26,13.033-18.876,3.569-29.141c-6.562-7.114-15.596-0.388-17.987,8.786
              L16.484,23.521z"></path>
            <path class="st0" d="M53.643,37.397c2.584-2.166,4.479-4.762,3.657-8.481c-0.889-4.005-4.87-6.276-8.664-4.478
              c-6.946,3.289-12.319,12.777-12.964,20.193c6.803-0.79,11.907,13.291,21.392,8.002c6.575-3.668,3.133-11.724-3.369-15.201
              L53.643,37.397z"></path>
            <path class="st2" d="M39.727,2.521c0.331-0.471,0.753-0.888,1.298-1.238c1.612-1.015,2.946-1.739,4.867-0.947
              c3.528,1.464,3.042,7.188,1.541,9.914c0.436-1.516,1.77-2.405,3.208-2.846c2.624-0.805,6.156-1.866,8.195,0.9
              c0.923,1.254,0.94,3.152,0.273,4.512c-0.695,1.424-1.513,2.646-2.676,3.738c-2.265,2.129-4.761,3.666-7.69,4.625
              c-0.896,0.293-1.796,0.514-2.701,0.766c-1.208,0.332-2.472,0.201-3.688,0.456c0.045-0.018,0.146-0.032,0.202-0.044
              c-0.394,0.091-1.646-2.262-1.816-2.592c-0.532-0.999-0.944-2.06-1.262-3.146C38.236,12.348,37.01,6.444,39.727,2.521z"></path>
          <div class="backgroundImg">
            <span class="titleName1">단</span>
            <span class="titleName2">어</span>
            <span class="titleName1">의 </span>
            <span class="titleName2">맛</span>
            <span class="titleName1">!</span>
            <p class="description">우당탕탕! 좌충우돌! 왁자지껄! 천방지축! <br> 정신없고, 정도없는! <b>김준영B조</b> 과연, 그의 도착지는 어디인가!</p>
            <div class="StarBtn">
              <p class="startText"><a href="game">시작하기</a></p>
            </div>
          </div>
        </div>
      </div>
      <footer style="left: calc(50% - 191px );">
        <div class="inner">
          <div class="fbox">
            <ul>
              <li>이용약관</li>
              <li>개인정보처리방침</li>
              <li>책임의 한계와 법적고지</li>
              <li>회원정보 고객센터</li>
            </ul>
          </div>
          <div class="fbox_bottom">
            <h2>단어의맛&copy;</h2>
            <div class="copy">Copyright danmat Corp. All Rights Reserved.</div>
          </div>
        </div>
      </footer>
    </div>
  </div>
</body>

</html>
