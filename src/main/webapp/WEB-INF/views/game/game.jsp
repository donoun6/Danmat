<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
  <title>game</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css" />
  <link rel="stylesheet" href="<c:url value='/resources/css/common/initial.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/game/game.css'/>">
  <style>
       html,
       body {
         position: relative;
         height: 100%;
       }

       body {
         background: #F3F1F5;
         font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
         font-size: 14px;
         color: #000;
         margin: 0;
         padding: 0;
         font-family: 'BinggraeSamanco';
       }

       .swiper {
         width: 100%;
         height: 100%;
       }

       .swiper-slide {
         text-align: center;
         font-size: 18px;
         background: #fff;

         /* Center slide text vertically */
         display: -webkit-box;
         display: -ms-flexbox;
         display: -webkit-flex;
         display: flex;
         -webkit-box-pack: center;
         -ms-flex-pack: center;
         -webkit-justify-content: center;
         justify-content: center;
         -webkit-box-align: center;
         -ms-flex-align: center;
         -webkit-align-items: center;
         align-items: center;
       }

       .swiper-slide img {
       display: block;
       width: 100%;
       height: 100%;
       object-fit: cover;

       }
     </style>
</head>

<body>
  <header class="header">
    <div>
      <div class="logoImg"></div>
      <a href="main" style="text-decoration: none; color: black;"><h2 class="logo-title">danmat <em style="font-size:12px;">단맛</em></h2></a>
    </div>
      <div class="topbtn-wrap" style="z-index: 500;">
        <h1><a href="main">메인화면</a></h1>
        <h1><a href="ranking">랭킹</a></h1>
        <h1><a href="board">게시판</a></h1>
         <%
        String userid = (String)session.getAttribute("userid");
        if(userid == null){ %>
        <h1><a href="signUp">회원가입</a></h1>
        <h1><a href="logIn">로그인</a></h1>
        <% }else { %>     
        	<h1><%= userid %>님</h1>
            <h1><a href="logOut">로그아웃</a></h1>
       <% } %>

      </div>
  </header>
  <div class="wrap">
    <div class="swiper-wrap">
      <div class="swiper mySwiper">
        <div class="swiper-wrapper">
          <div class="swiper-slide first">
            <div class="main-wrap first">
              <div class="game-title">
                  <h1>CrossWord</h1>
              </div>
              <div class="circle">
              <div class="fluid"></div>
              <div class="fluid2"></div>
              <div class="img"></div>
              </div>
              <div class="text-wrap">
                  <h1 class="start-btn"><a id="Crossword" href="#">시작하기</a></h1>
              </div>
            </div>
          </div>
          <div class="swiper-slide secound">
            <div class="main-wrap second">
              <div class="game-title">
                  <h1>WordRelay</h1>
              </div>
              <div class="circle">
              <div class="fluid"></div>
              <div class="fluid2"></div>
              <div class="img"></div>
              </div>
              <div class="text-wrap">
                  <h1 class="start-btn"><a href="wordRelay">시작하기</a></h1>
              </div>
          </div>
          </div>
          <div class="swiper-slide third">
            <div class="main-wrap third">
              <div class="game-title">
                  <h1>TwentyHills</h1>
              </div>
              <div class="circle">
              <div class="fluid"></div>
              <div class="fluid2"></div>
              <div class="img"></div>
              </div>
              <div class="text-wrap">
                  <h1 class="start-btn"><a href="Game/TwentyHills">시작하기</a></h1>
              </div>
          </div>
          </div>
        </div>
        <div class="swiper-pagination"></div>
      </div>
    </div>
  </div>
  <footer>
    <p>danmat&copy</p>
  </footer>
  <!-- Swiper JS -->
  <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
  <!-- Initialize Swiper -->
  <script>
  var bullet = ['크로스워드', '끝말잇기', '스무고개'];
    var swiper = new Swiper(".mySwiper", {
      slidesPerView: 1,
      spaceBetween: 30,
      loop: true,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
        renderBullet: function (index, className) {
       return '<div class="' + className + '"><span class="bulletCover">' + (bullet[index]) + '</span></div>';
     }
      }
    });
	
	function isSameAsLocation(uriString) {
	    const uri = new URL(uriString);

	    return (
	        uri.origin === window.location.origin &&
	        uri.pathname === window.location.pathname
	    );
	}

	function pageTransition(nodeList) {
	    nodeList.forEach((node) => {
	        if (!(node instanceof HTMLAnchorElement)) return;

	        const { href } = node;

	        if (!href || node.target === "_blank" || isSameAsLocation(href)) return;

	        node.addEventListener("click", (event) => {
	            event.preventDefault();

	            document.body.addEventListener(
	                "transitionend",
	                () => {
	                    window.location.href = href;
	                },
	                { passive: true, once: true }
	            );
	            document.body.classList.add("hidden");
	        });
	    });
	}
	
	pageTransition(document.querySelectorAll("a"))
	
    document.body.classList.add("reveal");
	
	document.querySelector("a#Crossword").addEventListener('click', function(){
		alert("준비 중입니다.");
	});
  </script>
</body>

</html>
