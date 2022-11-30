<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.cross.Danmat.User.Service.*" %>
  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <title>SignUp</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/common/nomalize.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/common/default.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/user/signUp.css'/>" >
    <script type="text/javascript" src="./js/prefix.js"></script>
  </head>
			    <% 
	    String userid = (String)session.getAttribute("userid");
		if (userid != null ){
			response.sendRedirect("main");
			return;
		}
	    %>
  <body>
    <div id="wrap">
      <div class="title">
        <h2><a href="main">DanMat</a></h2>
      </div>
      <form:form modelAttribute="user" method="post">
        <table class="table_a">
          <tr>
            <td>
              <ul class="id">
                <li>
                  <h2>아이디</h2>
                </li>
                
                <li><form:input path="userid" type="text" placeholder="5~20자의 영문 소문자,숫자와 사용가능합니다." minlength="5" maxlength="20" pattern="^[a-zA-Z0-9]+$"/></li>
              </ul>
            </td>
          </tr>
          <tr>
            <td>
              <ul>
                <li>
                  <h2>비밀번호</h2>
                </li>
                <form:input path="passwd" type="password" placeholder="8~16자의 영문 대 소문자,숫자를 사용하세요." minlength="8" maxlength="16" pattern="^[a-zA-Z0-9]+$"/>
              </ul>
            </td>
          </tr>
          <tr>
            <td>
              <ul>
                <li>
                  <h2>본인 이메일</h2>
                </li>
                <form:input path="email" type="email" placeholder="이메일 형식을 입력해주세요." maxlength="20"/>
              </ul>
            </td>
          </tr>
        </table>
        <div class="button-group">
          <button type="submit" class="gbtn normal" cursor: pointer;>가입하기</button>
        </div>
      </form:form>
    </div>
    <footer>
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
          <h2>danmat</h2>
          <div class="copy">Copyright danmat Corp. All Rights Reserved.</div>
        </div>
      </div>
    </footer>
  </body>

  </html>
