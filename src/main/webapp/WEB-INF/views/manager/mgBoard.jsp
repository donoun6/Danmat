<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
  <meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>post</title>
    <script type="text/javascript" src="./js/.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/board/board.css'/>">
  </head>
  <body>
  <% 
    String userid = (String)session.getAttribute("userid");
    if (userid == null || !userid.equals("admin")){
        response.sendRedirect("main");
        return;
    }
    %>
    <header id="header">
  <h1><a href="main"><img src="<c:url value='/resources/images/logo_transparent.png'/>" style="left: 85px; top:0; width: 100px; opacity:0.6;">danmat</a></h1>
  <nav class="links">
	<ul>
		<li><a href="#">소식</a></li>
        <li><a class="p-2" href="ranking">랭킹</a></li>
        <li><a class="p-2" href="board">게시판</a></li>
		<%if(userid == null ){%>
		<li><a href="signUp">회원가입</a></li>
		<li><a href="logIn" class="loginBtn" style="font-size: 20px; color:#c2bde3;">로그인</a></li>
		<%} else {%>
        <li><a class="btn loginBtn" href="logOut" style="font-size: 20px; color:#c2bde3; width: 100%;">로그아웃</a></li>
	<%}%>
	</ul>
  </nav>
  <nav class="main">
    <ul>
      <li class="search">
        <a class="fa-search" href="#search">Search</a>
        <form id="search" method="get" action="#">
        </form>
      </li>
      <li class="menu">
        <a class="fa-bars" href="#menu">Menu</a>
      </li>
    </ul>
  </nav>
</header>

      <!-- board list area -->
        <div id="board-list">
            <div class="container">
                <table class="board-table">
                    <thead>
                    <tr>
                        <th scope="col" class="th-num">번호</th>
                        <th scope="col" class="th-title">제목</th>
                        <th scope="col" class="th-date">등록일</th>
                    </tr>
                    </thead>
                    <tbody>
                      <tr>
                          <td>10</td>
                          <th>
                            <a href="#!">[공지사항] 우리 C조 화이팅</a>
                            <p>테스트</p>
                          </th>
                          <td>2022.08.19</td>
                      </tr>
                    <tr>
                        <td>9</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <th>
                          <a href="#!">[공지사항] 우리 C조 화이팅</a>
                          <p>테스트</p>
                        </th>
                        <td>2022.08.19</td>
                    </tr>
                    </tbody>
                </table>
                <a href="boardCreate" class="writer">글쓰기</a>
            </div>
        </div>
         <div class="paging">
          <ul class="paging" id="pagination">
          <li><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#">6</a></li>
          <li><a href="#">7</a></li>
          <li><a href="#">8</a></li>
          <li><a href="#">9</a></li>
          <li><a href="#">10</a></li>
          <li><a href="#">...</a></li>
        </ul>
     </div>
    </section>
    <!-- Footer -->
	<section id="footer">
	  <div class="pagination-wrapper clearfix">
	    <ul class="pagination float--right" id="pages">
	      <ul class="icons">
       	   <li><span class="label" style="margin-left: 30px; font-size: 42px; color: #6f61c9;">danmat</span></li>
     	  </ul>
   	    </ul>
 	  </div>
 	 <p class="copyright">Copyright danmat Corp. All Rights Reserved.</p>
	</section>
  </body>
</html>
