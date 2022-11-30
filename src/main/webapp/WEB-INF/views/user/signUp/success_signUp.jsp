<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberShip Login</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/user/success_signUp.css'/>">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
</head>
<body>
  <div class="wrap">
       <div class="login">
           <h2> ${user.userid }님 <br> 반갑습니다!</h2>
           <div class="img"><img src="<c:url value='/resources/images/logo_transparent.png'/>" alt=""></div>
           <p><a href="main">홈페이지로 이동</a></p>
     </div>
  </div>
</body>
</html>