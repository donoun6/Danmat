<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.cross.Danmat.WordRelay.Dao.*" %>
<%@ page import="com.cross.Danmat.WordRelay.Dao.WordRelayDao" %>
<%@ page import="com.cross.Danmat.WordRelay.Domain.Word" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/resources/js/hangul-tools.js'/>"></script>
<title>Insert title here</title>
</head>
<body>
<c:set var="result" value="${result }" ></c:set>
	<%int result = (int)pageContext.getAttribute("result");
	if(result == 1 ) {%>
		<div class="gameOver"><h1>GAME OVER</h1><h2>끝말을 이어주세요!</h2><a href="game">넘어가기</a><a href="wordRelay">새로하기</a></div>
	<% }else if(result == 2 ) {%>
		<div class="gameOver"><h1>GAME OVER</h1><h2>등록된 단어가 아니예요!</h2></h2><a href="game">넘어가기</a><a href="wordRelay">새로하기</a></div>
	<% }else if(result == 3 ) {%>
		<div class="gameOver"><h1>GAME OVER</h1><h2>이미 사용한 단어예요!</h2></h2><a href="game">넘어가기</a><a href="wordRelay">새로하기</a></div>
	<% }else if(result == 4 ) {%>
		<div class="gameOver"><h1>Wait..!</h1><h2>컴퓨터가 사용할 단어가 없어요! 안타깝지만 재시작 하셔야 합니다.</h2><a href="wordRelay">이어하기</a></div>
		<% }else if(result == 0){ %>
		<c:forEach var="answer" items="${answer }">
		<div class="word-box">
       	${answer.word }
        </div>
			<div class="def-box">
				<h1>${answer.word }</h1>
				<p>${answer.def }</p>
			</div>
		</c:forEach>
		<!--  <div class="userAnswer">
			<h1 class="userWord">${input_text }</h1>
		</div> -->
	<%}%>	
	<div class="used-box">
	<c:forEach var="point" items="${point }">
		<h1>${point.point }</h1>	
	</c:forEach>
	<p><c:forEach var="used" items="${used }">${used.usedWord } ,</c:forEach>
	</p>
	</div>
</body>
</html>