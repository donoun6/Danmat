<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Crossword 생성</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mgMain.css'/>">
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
                <form action="crossAdd" method="POST">
             	   <h2>크로스 워드</h2> <br>
		           <div class="submit">
		             <p class="mgMain"><a href="mgMain" style="color: #fff;">메인페이지</a></p>
		           </div>
		        </form>
	              <table>
	                  <tr>
	                    <td>  </td>
	                    <td>번호</td>
	                    <td>크로스워드</td>
	                    <td>등록일</td>
	                    <td>관리</td>
	                  </tr>
	             <!--    <c:forEach items="${user_list }" var="user_list">
	                <tr>
	                  <td>${user_list.userid}</td>
	                  <td>${user_list.email }</td>
	                  <td>${user_list.regDate }</td>
	             <form method="Get" action="/Danmat/manager/delete_cross">
	                  <input type="hidden" name = userid value="${user_list.userid }"/>
	                  <td><input type="submit" value="삭제"></td>
	                  </form>  
	                </tr>
	                </c:forEach>  -->
	                  <tr>
	                    <td><input type=checkbox name=number/></td>
	                    <td>1</td>
	                    <td>들어갈 곳</td>
	                    <td>2022-08-26</td>
	                    <td><button>삭제</button></td>
	                  </tr>
	                  <tr>
	                    <td><input type=checkbox name=number/></td>
	                    <td>2</td>
	                    <td>들어갈 곳</td>
	                    <td>2022-08-26</td>
	                    <td><button>삭제</button></td>
	                  </tr>
	                </tbody>
	              </table>
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
