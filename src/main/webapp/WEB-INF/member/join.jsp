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
		<input type="text" name="addr1" placeholder="Enter Address1" class="form-control m-2">
		<input type="text" name="addr2" placeholder="Enter Address2" class="form-control m-2">
		<input type="submit" value="가입하기" class="btn btn-primary w-100 m-2">
		<input type="reset" value="RESET" class="btn btn-primary w-100 m-2">
		<a href="/" class="btn btn-primary w-100 m-2">이전으로</a>
		<input type="hidden" name="flag" value="true">
	</form>
	</div>
</body>
</html>