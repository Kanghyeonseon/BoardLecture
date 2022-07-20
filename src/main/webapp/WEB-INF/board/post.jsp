<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
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
			<!-- 브래드크럼 시작 -->
			<div> 
				<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="#">Home</a></li>
				    <li class="breadcrumb-item"><a href="#">Board</a></li>
				    <li class="breadcrumb-item active" aria-current="page">post</li>
				  </ol>
				</nav>
			</div>
			<!-- 브래드크럼 끝 -->
			
			<h1>글쓰기</h1>
			
			<form action="/Board/post.do" method="post" enctype="multipart/form-data">
				<input type="text" name="title" class="form-control mb-3" placeholder="제목을 입력하세요.">
				<textarea name="content" rows="10" class="form-control mb-3" placeholder="내용을 입력하세요."></textarea>
				<input type="password" name="pwd" class="form-control mb-3" placeholder="게시글의 패스워드를 입력하세요.">
				<input type="file" name="files" class="form-control mb-3" multiple/>
				<input type="submit" value="글쓰기" class="btn btn-primary">
				<a href="#" class="btn btn-primary">처음으로</a>
				<input type="hidden" name="flag" value="true">
			</form>
		</div>	
		
		<!-- Footer -->
	
	</div>
</body>
</html>