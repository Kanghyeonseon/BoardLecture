<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="resources/includes/link.jsp" %>
<link rel="stylesheet" href="resources/css/common.css">
</head>
<body>
	<div class="container-md" style="width:400px; height:400px; margin:200px auto;">
		<div class="row m-3" id="logo">
			<div class="col" style="text-align:center;">
				<i class="bi bi-bootstrap" style="font-size:7rem;"></i>
			</div>
		</div>
		<div class="row m-3">
			<input name="userid" class="form-control" placeholder="아이디를 입력하세요">
		</div>
		<div class="row m-3">
			<input type="password" name="pwd" class="form-control" placeholder="패스워드를 입력하세요">
		</div>
		<div class="row m-3">
			<input type="submit" value="로그인" class="btn btn-primary">
		</div>
		<div class="row m-3">
			<a href="/MemberJoin.do" class="btn btn-primary">회원가입</a>
		</div>
	</div>
</body>
</html>
