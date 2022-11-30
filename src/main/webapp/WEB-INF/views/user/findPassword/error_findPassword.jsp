<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberShip Find User Password</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/user/signIn.css'/>">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
</head>
<body>
	<form action="findPassword" method="POST">
  <div class="wrap">
       <div class="login">
           <h2>Find Password</h2>
           <p class=error>아이디(로그인 전용 아이디) 또는 이메일을 잘못 입력했습니다.<br> 입력하신 내용을 다시 확인해주세요.</p>
           <div class="login_id">
               <h4>아이디</h4>
               <input type="text" name="userid" placeholder="5~20자의 영문 소문자, 숫자와 사용 가능합니다." minlength="5" maxlength="20" pattern="^[a-zA-Z0-9]+$"/><br>
           </div>
           <div class="login_id">
               <h4>이메일</h4>
               <input type="email" name="email" placeholder="이메일 형식을 입력해주세요."  maxlength="20"/><br>
           </div>
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
             <input type="submit" value="다음" style="cursor: pointer;">
             <p class="main"><a href="main">메인페이지</a></p>
           </div>
       </div>
   </div>
	</form>
</body>
</html>
