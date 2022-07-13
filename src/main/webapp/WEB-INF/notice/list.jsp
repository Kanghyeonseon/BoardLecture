<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<%@ include file="/resources/includes/link.jsp" %>
</head>
<body>

	<div class="container-md" id="wrapper" style="margin: 100px auto;">

	
		<!-- Top Menu -->
		<%@include file="/resources/includes/topmenu.jsp" %>
		
		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp" %>
	
		<!-- Main Contents -->
		<div id="maincontents" style="margin-top:15px;">
			<!-- 브래드크럼 시작-->
			<div> 
				<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="#">Home</a></li>
				    <li class="breadcrumb-item active" aria-current="page">공지사항</li>
				  </ol>
				</nav>
			</div> 
			<!-- 브래드크럼 끝 -->
			<h1>공지사항</h1>
			
			
			<a href="/Notice/post.do">글쓰기</a>
		</div>	
		
		<!-- Footer -->
	
	</div>
</body>
</html>