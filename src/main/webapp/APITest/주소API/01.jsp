<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/includes/link.jsp" %>
</head>
<body>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function test() {
		addr1 = document.getElementById("inputAddress");
		zipcode = document.getElementById("inputZip");

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

	<form class="row g-3 w-50 p-5">
	  <div class="col-md-6">
	    <label for="inputEmail4" class="form-label">Email</label>
	    <input type="email" class="form-control" id="inputEmail4">
	  </div>
	  <div class="col-md-6">
	    <label for="inputPassword4" class="form-label">Password</label>
	    <input type="password" class="form-control" id="inputPassword4">
	  </div>
	  <div class="col-12">
	    <label for="inputAddress" class="form-label">Address</label>
	    <input type="text" class="form-control" id="inputAddress" placeholder="우편번호 찾기를 이용하세요.">
	  </div>
	  <div class="col-12">
	    <label for="inputAddress2" class="form-label">Address 2</label>
	    <input type="text" class="form-control" id="inputAddress2" placeholder="나머지 주소를 입력해주세요.">
	  </div>
	  <div class="col-md-8">
	    <input type="text" class="form-control" id="inputZip" placeholder="우편번호 찾기를 이용하세요.">
	  </div>
	  <div class="col-md-4">
	  	<a href="javascript:test()" class="btn btn-primary">우편번호찾기</a>
	  </div>
	  <div class="col-12">
	    <div class="form-check">
	      <input class="form-check-input" type="checkbox" id="gridCheck">
	      <label class="form-check-label" for="gridCheck">
	        Check me out
	      </label>
	    </div>
	  </div>
	  <div class="col-12">
	    <button type="submit" class="btn btn-primary">Sign in</button>
	  </div>
	</form>

</body>
</html>