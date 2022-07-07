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
							<input type="password" class="form-control" name="pwd">
						</td>
					</tr>
				</table>
				<input type="submit" class="btn btn-primary w-20" value="패스워드 입력">
				<input type="hidden" name="flag" value="true">
				<!-- 리퀘스트가 초히화 될 예정이기 때문에 -->
				<input type="hidden" name="addr1" value="<%=request.getParameter("addr1") %>">
				<input type="hidden" name="addr2" value="<%=request.getParameter("addr2") %>">
			</form>
		</div>	
		
		<!-- Footer -->
	
	</div>
</body>
</html>