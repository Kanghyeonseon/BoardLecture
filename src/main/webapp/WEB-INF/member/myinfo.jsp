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
<%@page import="com.korea.dto.MemberDTO" %>
<%
	MemberDTO dto = (MemberDTO) request.getAttribute("dto");
	String email = (String) dto.getEmail();
	String addr1 = (String) dto.getAddr1();
	String addr2 = (String)	dto.getAddr2();
%>
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
					<thead>
						<tr class="table-light">
							<th>분류</th>
							<th>정보</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Email</td>
							<td><input type="text" class="form-control" placeholder="<%=email%>" style="border:none;" disabled ></td>
						</tr>
						<tr>
							<td>addr1</td>
							<td><input type="text" name="addr1" class="form-control" placeholder="<%=addr1%>" style="border:none;"></td>
						</tr>
						<tr>
							<td>addr2</td>
							<td><input type="text" name="addr2" class="form-control" placeholder="<%=addr2%>" style="border:none;"></td>
						</tr>
						<tr>
							<td>password</td>
							<td><input type="password" name="pwd" class="form-control" value="<%=dto.getPwd() %>" placeholder="변경할 비밀번호를 입력하세요." style="border:none;"></td>
						</tr>
					</tbody>
				</table>
			<input type="submit" class="btn btn-primary w-20" value="정보수정">
			<button class="btn btn-secondary w-20">메인이동</button>
			</form>
		</div>	
		
		<!-- Footer -->
	
	</div>
</body>
</html>