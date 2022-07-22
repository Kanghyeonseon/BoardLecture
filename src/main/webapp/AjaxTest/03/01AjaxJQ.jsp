<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- JQ CDN -->
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
</body>

<script>
	function sendRequest(URL, TAG){
		$.ajax({
			url : URL, // 요청 URL
			type : "GET", // 요청 메서드
			dataType : "html", //html : 기본, xml, json을 선택할 수 있다.
			/* timeout : 1000,
			data : {"param" : "value"}, */
			error : function(request, status, error) { // key : error, value : function
				alert("code : " + request.status + "\nstatus : " + status + "\nerror : " + error);
			},
			success : function(result){
				$("#" + TAG).html(result);
			}
		})
	}
</script>

<style>
.ajaxCont {
	border:1px solid gray;
	height:200px; overflow:auto;
	margin:10px 0;
}
</style>

<h1>Ajax 테스트</h1>
<div class="ajaxCont">
	<button onclick="sendRequest('01page1.jsp', 'test1')">버튼</button>
	<!-- url도 받고 파라미터 Hong도 같이 보내도록 지정했다. -->
	<div id="test1"></div>
</div>
<div class="ajaxCont">
	<button onclick="sendRequest('01page2.jsp', 'test2')">버튼</button>
	<div id="test2"></div>
</div>





</html>