<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 메인 페이지</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mgMain.css'/>">
<link rel="stylesheet" href="./css/font.css" />
  </head>
  <body>
      <% String userid = (String)session.getAttribute("userid");
	if (userid == null || !userid.equals("admin")){
		response.sendRedirect("main");
		return;
	}%>
    <div class="container">
      <div class="navigation">
        <ul>
           <li>
            <a href="mgMain">
              <span class="icon"><ion-icon name="logo-docker"></ion-icon></span>
              <span class="title">DanMat</span>
            </a>
          </li>
          <li>
            <a href="mgMain">
              <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
              <span class="title">관리자 페이지</span>
            </a>
          </li>
          <li>
            <a href="manager/searchUser">
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
          <li>
            <a href="/Danmat/manager/cross/crossList">
              <span class="icon"><ion-icon name="alert-circle-outline"></ion-icon></span>
              <span class="title">게임설정</span>
            </a>
          </li>
        </ul>
      </div>

        <!-- main -->
        <div class="main">
          <div class="topbar">
            <div class="toggle">
              <ion-icon name="menu-outline"></ion-icon>
            </div>
            <!-- search -->
            <div class="search">
              <input type='date' id='currentDate'/>
            </div>
            <!-- userImg -->
            <div class="user">
              <!-- <img src="user.jpg"> -->
            </div>
          </div>

          <!-- cards -->
          <div class="cardBox">
            <div class="card">
              <div>
                <div class="numbers">${total_user}</div>
                <div class="cardName">전체 유저</div>
              </div>
              <div class="iconBx">
                <ion-icon name="people-outline"></ion-icon>
              </div>
            </div>
            <div class="card">
              <div>
                <div class="numbers">${new_user }</div>
                <div class="cardName">신규 유저</div>
              </div>
              <div class="iconBx">
                <ion-icon name="person-add-outline"></ion-icon>
              </div>
            </div>
            <div class="card">
              <div>
                <div class="numbers">${new_board }</div>
                <div class="cardName">새 글</div>
              </div>
              <div class="iconBx">
                <ion-icon name="eye-outline"></ion-icon>
              </div>
            </div>
            <div class="card">
              <div>
                <div class="numbers">${userid}</div>
                <div class="cardName">랭킹</div>
              </div>
              <div class="iconBx">
                <ion-icon name="trophy-outline"></ion-icon>
              </div>
            </div>
          </div>

          <!--orer details list-->
          <div class="details">
            <div class="recentOrders">
              <div class="cardHeader">
                <h2>최근 게시글</h2>
                <a href="board" class="btn">전체보기</a>
              </div>
              <table>
                <thead>
                  <tr>
                    <td>번호</td>
                    <td>제목</td>
                    <td>등록일</td>
                    <td>관리</td>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${board_list }" var="board" begin="1" end="5" step="1">
                <tr>
                    <td>${board.board_idx }</td>
                    <td>${board.title }</td>
                    <td>${board.createDate }</td>
                    <form method="Get" action="/Danmat/manager/delete_Board">
                   <input type="hidden" name ="board_idx" value="${board.board_idx }"/>
                   <td><input type="submit" value="삭제"></td>
                   </form>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>

            <!--new customer-->
            <div class="recentCustomers">
              <div class="cardHeader">
                <h2>랭킹</h2>
              </div>
              <table>
                <th>
                  <tr width="50px"></div></tr><br>
                  <tr><h1>1위 단맛<br></h1></tr><br>
                  <tr><h2>2위 짠맛<br></h2></tr><br>
                  <tr><h3>3위 매운맛<br></h3></tr><br>
                </th>
              </table>
            </div>
          </div>
          
            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                      <div class="fbox_bottom">
                        <span>단어의 맛&copy;<br></span>
                        <span>Copyright danmat Corp. All Rights Reserved.</span>
                    </div>
             	   </div> 	  
      		    </div>
            </footer>

        </div>
    </div>
    	
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

    <script>
      // date
      document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);
    
      // MenuToggle
      let toggle = document.querySelector('.toggle');
      let navigation = document.querySelector('.navigation');
      let main = document.querySelector('.main');

      toggle.onclick = function(){
        navigation.classList.toggle('active');
        main.classList.toggle('active');
      }

      //add hoverd class in selected list item
      let list = document.querySelectorAll('.navigation li');
      function activeLink(){
        list.forEach((item) =>
          item.classList.remove('hovered'));
          this.classList.add('hoverd');
        }
        list.forEach((item) =>
        item.addEventListener('mouseover',activeLink));
    </script>
    
	</body>
</html> 