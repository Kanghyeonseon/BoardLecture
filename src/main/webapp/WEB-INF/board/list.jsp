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

	<div class="container-md" id="wrapper" style="width:80%; margin: 100px auto;">

	
		<!-- Top Menu -->
		<%@include file="/resources/includes/topmenu.jsp" %>
		
		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp" %>
	
		<!-- Main Contents -->
		<div id="maincontents" margin-top:15px;">
			<!-- 브래드크럼 시작-->
			<div> 
			<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="#">Home</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Board</li>
			  </ol>
			</nav>
			</div> 
			<!-- 브래드크럼 끝 -->
			
			<h1>자유게시판</h1>
			
			<!-- 현재페이지 정보 -->
			<table class="table">
				<tr >
					<td style="border-bottom:0px;">1/100 Page</td>
					<td style="border-bottom:0px; text-align:right;">
						<button class="btn btn-secondary">처음으로</button>
						<button class="btn btn-secondary">글쓰기</button>
					</td>
				</tr>
			</table>
			
			<table class="table border-bottom-0">
				<tr>
					<td>NO</td>
					<td>TITLE</td>
					<td>WRITER</td>
					<td>DATE</td>
					<td>COUNT</td>
				</tr>
				
				<%@page import="java.util.*, com.korea.dto.*" %> <!-- boardDTO사용, arraylist사용 -->
				<%
					ArrayList<BoardDTO> list = (ArrayList<BoardDTO>) request.getAttribute("list"); /* 기본 오브젝트형인것을 바꿔준다. */
					for(int i=0; i<list.size(); i++){
						%>
						<tr>
						<td><%=list.get(i).getNo() %></td>
						<td><%=list.get(i).getTitle() %></td>
						<td><%=list.get(i).getWriter() %></td>
						<td><%=list.get(i).getRegdate() %></td>
						<td><%=list.get(i).getCount() %></td>
					</tr>		
					<%
					}
				%>
		
				
				
				<tr>
					<td colspan="5" style="border-bottom:0px;">
					<!-- 페이지네이션 시작 -->
						<nav aria-label="Page navigation example">
						 <ul class="pagination">
						   <li class="page-item">
						     <a class="page-link" href="#" aria-label="Previous">
						       <span aria-hidden="true">&laquo;</span>
						     </a>
						   </li>
						   <li class="page-item"><a class="page-link" href="#">1</a></li>
						   <li class="page-item"><a class="page-link" href="#">2</a></li>
						   <li class="page-item"><a class="page-link" href="#">3</a></li>
						   <li class="page-item">
						     <a class="page-link" href="#" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						     </a>
						   </li>
						 </ul>
						</nav>
					</td>
					<!-- 페이지네이션 끝 -->
				</tr>
				
			</table>
			<a href="/Board/post.do">글쓰기</a>
		</div>	
		
		<!-- Footer -->
	
	</div>
</body>
</html>