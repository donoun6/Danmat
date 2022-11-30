<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Crossword 생성</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/crossword/crossSearch.css'/>">
<link rel="stylesheet" href="./css/font.css" />
</head>

<body>
    <div class="container">
      <div class="navigation">
        <ul>
          <li>
            <a href="/Danmat/mgMain">
              <span class="icon"><ion-icon name="logo-docker"></ion-icon></span>
              <span class="title">DanMat</span>
            </a>
          </li>
          <li>
            <a href="/Danmat/mgMain">
              <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
              <span class="title">관리자 페이지</span>
            </a>
          </li>
          <li>
            <a href="/Danmat/manager/searchUserList">
              <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
              <span class="title">유저 관리</span>
            </a>
          </li>
          <li>
            <a href="/Danmat/board">
              <span class="icon"><ion-icon name="clipboard-outline"></ion-icon></span>
              <span class="title">게시판</span>
            </a>
          </li>
          <li>
            <a href="/Danmat/notice">
              <span class="icon"><ion-icon name="alert-circle-outline"></ion-icon></span>
              <span class="title">소식</span>
            </a>
          </li>
          <li>
            <a href="/Danmat/manager/cross/crossList">
              <span class="icon"><ion-icon name="game-controller-outline"></ion-icon></span>
              <span class="title">게임 설정</span>
            </a>
          </li>
        </ul>
      </div>
	
        <!-- main -->
        <div class="main">
          <div class="topbar">
            <div>
            <!--  <ion-icon name="menu-outline"></ion-icon>-->
            </div>
            <!-- search -->
            <div>
              <label>
              </label>
            </div>
            <!-- userImg -->
            <div class="user">
              <!-- <img src="user.jpg"> -->
            </div>
          </div>
          
         <!--orer details list-->
          <div class="details">
            <div class="recentOrders">
              <div class="cardHeader">
           	    <h2>게임 리스트</h2> <br>
           	    <!-- 역순 표시 방법 -->
           	    <c:set var="tableLength" value="${fn:length(gameTable)}"/>
           	    <c:set var="gidLength" value = "${fn:length(gidList)}"/>
       		 	<c:forEach items="${gameTable}" var="game" varStatus="status">
       		 		${gameTable[tableLength - status.count]}
       		 		<form action="/Danmat/manager/cross/crossDelete" method="get">
       		 			<input type="hidden" name="gid" value="${gidList[gidLength - status.count].takeGid}"/>
       		 			<input type="submit" value="삭제하기">
       		 		</form>
       		 	</c:forEach>
       		 	
				       		 	
		        
       		  </div>
       		 </div>
       		 	
            <!-- Footer 영역 -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                      <span>단어의 맛&copy;<br></span>
                      <span>Copyright danmat Corp. All Rights Reserved.</span>
                    </div>
                </div>
            </footer>
          </div>
          
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	
</body>
</html>
