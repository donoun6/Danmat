<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberShip Find User Id</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/user/signIn.css'/>">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
</head>
<body>
	<form action="findPassword" method="POST">
  <div class="wrap">
       <div class="login">
       <div class="img"><img src="<c:url value='/resources/images/logo_transparent.png'/>" alt=""></div>
      		<c:forEach var="findPassword" items="${findPassword }">
           <h3 class="findinfo">회원님의 비밀번호 정보는 <br> '${findPassword.passwd }' 입니다.</h3>
            </c:forEach>
           <div class="login_etc">
               <a href="signUp">회원가입</a>
               <div class="checkbox">
                 <a href="logIn" style="margin-left: 40px;" >로그인</a>
               </div>
               <div class="forgot_pw">
                 <a href="findId" style="margin-left: 40px;">아이디 찾기</a>
           </div>
           </div>
           <div class="submit">
             <p class="main boder_main"><a href="main" style="color: #fff;">메인페이지</a></p>
           </div>
       </div>
   </div>
	</form>
</body>
</html>
