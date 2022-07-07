<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보-패스워드</title>
<%@ include file="/resources/includes/link.jsp" %>
</head>
<body>
	<div class="container-md" id="wrapper" style="width:80%; margin: 100px auto;">
		<!-- Top Menu -->
		<%@include file="/resources/includes/topmenu.jsp" %>
		
		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp" %>
	
		<!-- Main Contents -->
		<div id="maincontents" class="container-md" style="margin-top:20px;">
		<h2>회원정보</h2>
			<form action="/MemberUpdate.do" method="post">
				<table class="table table-hover align-middle" style="margin:30px auto;">
					<tr>
						<td>
							<input type="password" name="pwd">
						</td>
					</tr>
				</table>
			<input type="submit" href="/MemberUpdate.do" class="btn btn-primary w-20" value="정보수정">
			<button class="btn btn-secondary w-20">메인이동</button>
			<input type="hidden" name="flag" value="true">
			</form>
		</div>	
		
		<!-- Footer -->
	
	</div>
</body>
</html>