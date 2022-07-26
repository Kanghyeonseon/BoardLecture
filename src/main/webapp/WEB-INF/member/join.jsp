<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<%@ include file="/resources/includes/link.jsp" %>
</head>
<body>
	<div class="container-md" style="width:60%; margin:100px auto; text-align:center;">
	<h1 class="m-4">회원가입</h1>
	<form action="/MemberJoin.do" method="post">
		<input type="email" name="email" placeholder="example@example.com" class="form-control m-2">
		<input type="password" name="pwd" placeholder ="Enter Password" class="form-control m-2">
		<div class="row g-3">
			<div class="col-md-4">
				<input type="text" name="zipcode" id="zipcode" placeholder="zipcode" class="form-control m-2">
			</div>
			<div class="col-md-3">
				<a href="javascript:zipsearch()" class="btn btn-primary m-2">우편번호검색</a>
			</div>
		</div>
		<input type="text" name="addr1" id="addr1" placeholder="Enter Address1" class="form-control m-2">
		<input type="text" name="addr2" placeholder="Enter Address2" class="form-control m-2">
		<input type="submit" value="가입하기" class="btn btn-primary w-100 m-2">
		<input type="reset" value="RESET" class="btn btn-primary w-100 m-2">
		<a href="/" class="btn btn-primary w-100 m-2">이전으로</a>
		<input type="hidden" name="flag" value="true">
	</form>
	</div>
</body>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function zipsearch() {
		addr1 = document.getElementById("addr1");
		zipcode = document.getElementById("zipcode");

		new daum.Postcode({
			oncomplete: function(data) {
				if(data.userSelectedType=="R") { // 사용자가 선택한 주소가 도로명주소 인 경우
					addr1.value =  data.roadAddress;
				} else {
					addr1.value = data.jibunAddress;
				}
				zipcode.value = data.zonecode;
			}
		}).open();
	}
</script>

</html>