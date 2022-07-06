<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
<%@ include file="/resources/includes/link.jsp" %>
</head>
<body>
<%
	String email = (String) session.getAttribute("email");
%>


	<div class="container-md" id="wrapper" style="width:80%; margin: 100px auto;">
		<!-- Top Menu -->
		<%@include file="/resources/includes/topmenu.jsp" %>
		
		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp" %>
	
		<!-- Main Contents -->
		<div id="maincontents" style="border:1px solid gray; margin-top:15px;">
		<h1>회원정보</h1>
			<table class="table w-75 table-striped" style="margin:100px auto;">
				<tr>
					<td>Email</td>
					<td><%=email %></td>
				</tr>
				<tr>
					<td>addr1</td>
					<td>대구</td>
				</tr>
				<tr>
					<td>addr2</td>
					<td>대구대구</td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-primary w-50">정보수정</button>
					</td>
					<td>
						<button class="btn btn-secondary w-50">메인이동</button>
					</td>
				</tr>
			</table>
		</div>	
		
		<!-- Footer -->
	
	</div>
</body>
</html>