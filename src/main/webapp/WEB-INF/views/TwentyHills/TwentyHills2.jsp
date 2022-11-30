<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스무고개</title>
<link rel="stylesheet" type="text/css" href="/Danmat/resources/css/TwentyHills/TwentyHills.css"/>
<link rel="stylesheet" type="text/css" href="/Danmat/resources/css/wordRelay.css"/>
</head> 
<body>
<canvas id="demo-canvas"></canvas>
<div class="pageloader"></div>
        <div class="infraloader is-active"></div>        
        <!-- Hero and nav -->
        <div id="large-header" class="hero is-slanted is-relative is-gradient is-fullheight is-halfed-mobile">
        
          <div id="main-hero" class="hero-body">
                <div id="main-landing-title" class="container has-text-centered">
                    <div class="columns">
                        <div class="column is-6 is-offset-3 has-text-centered">
                        <div class="wrap">
		<div class="life">
			<p id="life"><label>남은 횟수:</label>&nbsp;&nbsp;${life }</p>
			<div id="A"></div>
			<span id="e1"> = 정답</span>
			<div id="L_A"></div>
			<span id="e2"> = 자리가 틀림</span>
			<div id="WA"></div>
			<span id="e3"> = 틀림</span>
			<%String userid = (String)session.getAttribute("userid"); 
			if(userid == null){%>
			<p id="user"> 유저 ID: guest </p>
			<%} else { %>
			<p id="user"> 유저 ID: <%=userid %></p>
			<%} %>
		</div>
		<div class="text-area" id="hint">
		<h2 id="guide">보기</h2>
		<c:forEach items="${hint }" var="hint">
			
			<c:if test="${not empty hint}">
			<h3 class="hint" hidden>${hint }</h3>
			</c:if>
		</c:forEach>
		<h3 class="hint" hidden>힌트가 없습니다.</h3>
		</div>
		${input }
		<div class="btn">
		<button>입력</button>
		<input type="submit" id="Hint" value="힌트 보기"/>
		<input type="hidden" id="Answer" value="정답 보기"/>
		</div>
		<form method="get" action="../Game/TwentyHills">
		<input type="hidden" id="Restart" value="다시하기"/>
		</form>
		<form method="get" action="../game">
		<input type="submit" id="Exit" value="종료"/>
		</form>
	</div>             
    </div>
    </div>
    </div>
    </div>
        </div>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script><script  src="/Danmat/resources/js/animation.js"></script>
	<script>
		var result = `${word }`;
		var count = 0;
		var h_count = 0;
		var check = 0;
		var life = ${life};
		document.querySelectorAll('.hint')[h_count].removeAttribute("hidden");
			document.querySelector('button').addEventListener('click',
					function(){
						var input = document.querySelectorAll('.input');
						for(let i = 0; i < input.length; i++){
							if(input[i].value != ""){
								check++;
							}
						}
						if(check == input.length){
						var input = document.querySelectorAll('.input');
						for(let i = 0; i<result.length; i++){
								if(input[i].value == result[i]){
									input[i].style.background = '#8CD790';
									input[i].setAttribute("readonly","true");
									count++;
								}else if(result.includes(input[i].value)){
									input[i].style.background = '#77AF9C';
								}else {
									input[i].style.background = '#285943';
							}	
						}
						h_count++;
						if(document.querySelectorAll(".hint").length <= h_count){
							document.querySelector('#Restart').setAttribute("type","submit");
							console.log("힌트가 없습니다.");
						}else {
						document.querySelectorAll(".hint")[h_count].removeAttribute("hidden");				
						}
						if(result.length == count){
							document.getElementById('hint').innerHTML = "<h3 id=GameClear>정답입니다. \n 총 점수: "+(life*10)+"점 입니다.</h3>";
							for(let i = 0; i<result.length; i++){
								input[i].setAttribute("type","hidden");
							}
							document.querySelector('button').setAttribute("hidden", "true");
							document.querySelector('#Restart').setAttribute("type","submit");
							document.querySelector("#Hint").setAttribute("type","hidden");
							document.querySelector("input#Answer").setAttribute("type","hidden");
							document.querySelector(".life").remove();
							document.querySelector("#input label").remove();
							document.querySelector('p').remove();
							End_game();
						}
						if(life > 0){
							life--;
							document.getElementById("life").innerHTML = "<label>남은 횟수:</label>&nbsp;&nbsp;"+life;
							}else {
								document.getElementById('hint').innerHTML="<h3 id=GameOver>Game Over</h3><h3 id=Answer>정답은 "+result+"입니다.</h3>";
								for(let i = 0; i<result.length; i++){
									input[i].setAttribute("type","hidden");
								}
								document.querySelector('button').setAttribute("hidden","true");
								document.querySelector('#Restart').setAttribute("type","submit");
								document.querySelector("#Hint").setAttribute("type","hidden");
								document.querySelector("input#Answer").setAttribute("type","hidden");
								document.querySelector(".life").remove();
								document.querySelector("#input label").remove();
								document.querySelector('p').remove();
								End_game();
							}
						count = 0;
						check = 0;
						}else {
							alert("단어를 입력하여 주세요.");
							check = 0;
						}
					})
					
				document.querySelector('#Hint').addEventListener('click',
					function(){
					h_count++;
					if(document.querySelectorAll(".hint").length <= h_count){
						End_game();
						document.querySelector('input#Answer').setAttribute("type","submit");
						console.log("힌트가 없습니다.");
					}else {
					life--;
					document.getElementById("life").innerHTML = "<label>남은 횟수:</label>&nbsp;&nbsp;"+life;
					document.querySelectorAll(".hint")[h_count].removeAttribute("hidden");
					if(life == 1){
						for(let i = 0; i<result.length; i++){
							input[i].setAttribute("type","hidden");
						}
						End_game();
					}
					
					}
				document.querySelector('input#Answer').addEventListener('click',
						function(){
					document.getElementById('hint').innerHTML="<h3 id=GameOver>Game Over</h3><h3 id=Answer>정답은 "+result+"입니다.</h3>";
					for(let i = 0; i<result.length; i++){
						input[i].setAttribute("type","hidden");
					}
					End_game();
				});
				});
			if(life <= 1){
				document.getElementById('hint').innerHTML="<h3 id=GameOver>Game Over</h3><h3 id=Answer>정답은 "+result+"입니다.</h3>";
				End_game();
			}
			for(let i = 0; i < 5; i++){
				document.querySelectorAll('input.input')[i].addEventListener('keyup', function(){
					if(this.value.length == 2){
						document.querySelectorAll('input.input')[i+1].focus();
					}
				});
				}
			function End_game(){
				document.querySelector('button').setAttribute("hidden","true");
				document.querySelector('#Restart').setAttribute("type","submit");
				document.querySelector("#Hint").setAttribute("type","hidden");
				document.querySelector("input#Answer").setAttribute("type","submit");
				document.querySelector("#input").remove();
				document.querySelector('.life p#life').remove();
				document.querySelector(".life").remove();
				document.querySelector('p').remove();
			}
	</script>
</body>
</html>