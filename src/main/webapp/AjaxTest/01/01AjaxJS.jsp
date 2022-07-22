<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>



<script>
	// url : 요청 한 위치, tag : 응답의 내용을 표시 할 위치 를 외부인자를 받아서 메서드를 정의 할 것이다.
	function sendRequest(url, tag) {
		var httpRequest;
		function createRequest() {
			// 익스플로러 7과 그 이상의 버전, 크롬, 파이어폭스, 사파리, 오페라 등 브라우저가 맞지 않는 경우가 요즘은 대부분없어서 분기처리를 지웠다.
			return new XMLHttpRequest();
			// XML리퀘스트 객체를 만들고있다.
		}
		
		// 서버에 요청을 보낼 때는 request로 보내고 응답은 recieveResponse()메서드가 처리하도록 한다.
		function receiveResponse() {
			// XMLHttpRequest 객체의 현재 상태가 요청 완료이고, 서버에 문서가 존재하면 받은 데이터를 출력함.
			 // 데이터를 제대로 받아왔다는 뜻이다.
			if (httpRequest.readyState == XMLHttpRequest.DONE
					&& httpRequest.status == 200) {
				 // 200이라는건 요청이 제대로들어왔다는 뜻이다.
				document.getElementById(tag).innerHTML = httpRequest.responseText; 
				 // 특정 아이디를 불러와서 거기에 텍스트를 넣어준다는뜻이다.
			}
		}
	
		httpRequest = createRequest(); // XMLHttpRequest 객체를 생성함.
		
		httpRequest.onreadystatechange = receiveResponse; // XMLHttpRequest 객체의 현재 상태를 파악함.
		// GET 방식의 비동기식 요청으로 Http 요청을 생성함.
		httpRequest.open("GET", url, true);
		httpRequest.send(); // Http 요청을 보냄.
	}
</script>

<style>
.ajaxCont {
	height:200px;
	border:1px solid gray;
	margin:10px 0;
}
</style>

<h1>Ajax 테스트</h1>
<div class="ajaxCont">
	<button onclick="sendRequest('01page1.jsp?userid=Hong', 'test1')">버튼</button>
	<!-- url도 받고 파라미터 Hong도 같이 보내도록 지정했다. -->
	<p id="test1"></p>
</div>
<div class="ajaxCont">
	<button onclick="sendRequest('01page2.jsp?userid=Kim', 'test2')">버튼</button>
	<p id="test2"></p>
</div>



</html>