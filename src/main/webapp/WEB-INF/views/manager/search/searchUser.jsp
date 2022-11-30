<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>searchUser</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mgMain.css'/>">
<link rel="stylesheet" href="./css/font.css" />
</head>

<body>
<body>
    <% String userid = (String)session.getAttribute("userid");
	if (userid == null || !userid.equals("admin")){
		response.sendRedirect("http://localhost:8080/Danmat/main");
		return;
	}%>
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
            <a href="searchUser">
              <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
              <span class="title">유저 관리</span>
            </a>
          </li>
          <li>
            <a href="/Danmat/manager/mgBoard">
              <span class="icon"><ion-icon name="clipboard-outline"></ion-icon></span>
              <span class="title">게시판</span>
            </a>
          </li>
          <li>
            <a href="/Danmat/manager/mgBoard">
              <span class="icon"><ion-icon name="alert-circle-outline"></ion-icon></span>
              <span class="title">소식</span>
            </a>
          </li>
        </ul>
      </div>
      
        <!-- main -->
        <div class="main">
          <div class="topbar">
            <div>
              <!-- <ion-icon name="menu-outline"></ion-icon>-->
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
				<div class="wrap">
               	<h2>회원 관리</h2>
              	<form:form action="searchUser" method="POST">
              		<select name="type">
						<option value="unknown">--선택--</option>
						<option value="id">ID</option>
						<option value="email">Email</option>
					</select><br>
					<input type="text" name="Name">
					<input type="submit" value="검색">
				 </form:form>
				<c:forEach var="searchList" items="${user_list}">
					<h3 class="searchInfo"></h3>
				</c:forEach>
				<p class="mgMain"><a href="/Danmat/mgMain">메인페이지</a></p>
				</div>
       		 </div>
       		 
             <table>
               <thead>
                 <tr>
                   <td>회원 아이디</td>
                   <td>이메일</td>
                   <td>등록일</td>
                   <td>관리</td>
                 </tr>
               </thead>
               <tbody>
                 <c:forEach items="${user_list }" var="user_list">
                 <tr>
                   <td>${user_list.userid}</td>
                   <td>${user_list.email }</td>
                   <td>${user_list.regDate }</td>
                   <form method="Get" action="/Danmat/manager/delete_User">
                   <input type="hidden" name = userid value="${user_list.userid }"/>
                   <td><input type="submit" value="삭제"></td>
                   </form>
                 </tr>
                 </c:forEach>
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
	   </div>
				          
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
		
</body>
</html>
