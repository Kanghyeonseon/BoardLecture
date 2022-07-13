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
			    <li class="breadcrumb-item active" aria-current="page">Board</li>
			  </ol>
			</nav>
			</div> 
			<!-- 브래드크럼 끝 -->
			
			<h1>자유게시판</h1>
			<%
				// list 구성에 사용되는 변수들
				int totalcount = 0; 	// 전체 게시물 수  
				int numPerPage = 10;  // 페이지 당 표시할 게시물 수
				int totalPage = 0; 		// 현재 페이지 수
				int nowPage = 1;		  // 현재 페이지 번호
				int start = 1; 				// 게시물 읽을 때 사용되는 시작값
				int end = 10; 				// 게시물 읽을 때 사용되는 끝값
				
				// 페이징 처리에 사용되는 변수들
				int totalBlock = 0;			// 전체 페이징 블럭 수
				int nowBlock = 1;				// 현재 페이징 블럭 수 
				int pagePerBlock = 15; 	// 블럭 당 표시할 페이지 수
			%>
			
			<%
				// 상단의 현재 페이지 번호를 위한 처리
				if(request.getAttribute("nowPage")!=null)
					nowPage= Integer.parseInt( (String)request.getAttribute("nowPage")
					);
				// 컨트롤러에 처음 접속했을때는 nowPage가 없다. nowPage를 전달해주는 경우에만 그 값을 Integer로 바꿔서 가지고오겠다는 뜻이다.
			%>
			<%
				totalcount = (int) request.getAttribute("tcnt");
				totalPage = (int) Math.ceil(totalcount/numPerPage);
				
				totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
				nowBlock = (int) Math.ceil((double) nowPage / pagePerBlock);
			%>
			
			<!-- -->
			<script>
				/* 페이징 처리함수 : 페이지 번호를 받아 해당 페이지를 표시하는 함수 */
				function paging(pageNum) {
					form = document.readFrm;
					form.nowPage.value=pageNum;
					var numPerPage = <%=numPerPage %>;
					form.start.value = (pageNum  - 1) * numPerPage + 1;
					form.end.value = pageNum * numPerPage;
					form.action = "/Board/list.do";
					form.submit();
				}
			
				/* 블럭 처리 함수 : 이전, 이후 버튼을 눌렀을 때 블럭을 이동하는 함수 */
				function block(value) { // value값에는 현재블럭 이전 또는 이후 숫자가 있을 것이다.
					form = document.readFrm;
					StartPage = <%=pagePerBlock%>*(value-1) + 1; // 이전 혹은 다음 블럭의 첫번째 페이지 번호이다.
					numPerPage = <%=numPerPage%>;
					
					form.start.value = (StartPage - 1) * numPerPage + 1; // 꺼낼 게시물의 시작 값 DB랑 연결된다.
					form.end.value = StartPage * numPerPage; // 꺼낼 게시물의 끝값 DB랑 연결된다.
					form.nowPage.value=StartPage;
					form.action = "/Board/list.do";
					form.submit();
				}
				
				/* 처음으로 이동 처리 */
				function init() {
					form = document.initFrm;
					form.nowPage.value=1;
					form.action="/Board/list.do";
					form.submit();
				}
				
			</script>
			
			<form name="initFrm" method="get">
				<input type="hidden" name="nowPage">
			</form>
			
			<!-- 페이징 처리 폼 -->
			<form name="readFrm" method="get">
				<input type="hidden" name="no">				<!-- 게시물 번호 -->
				<input type="hidden" name="start">		<!-- DB로 부터 읽을 시작번호 -->
				<input type="hidden" name="end">			<!-- DB로 부터 읽을 끝번호 -->
				<input type="hidden" name="nowPage">	<!-- 현재 페이지 번호 -->
			</form>
			
			<!-- 현재페이지 정보 -->
			<table class="table">
				<tr >
					<td style="border-bottom:0px;"><span style="color:red;"><%=nowPage %></span> / <%=totalPage %> Page</td>
					<td style="border-bottom:0px; text-align:right;">
						<button class="btn btn-secondary" onclick="init()">처음으로</button>
						<a class="btn btn-secondary" href="/Board/post.do">글쓰기</a>
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
					<td colspan="5" style="border-bottom: 0px;">
						<!-- 페이지네이션 시작 -->
						<nav aria-label="Page navigation example">
							<ul class="pagination">
							<!-- 이전으로 : 첫 번째 페이지에서는 보이지 않아야한다. -->
							<%
								if(nowBlock>1) {
							%>
								<li class="page-item"><a class="page-link" href="javascript:block('<%=nowBlock-1%>')"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							<%		
								}
							%>
							
							<!-- 페이지 표시 -->
							<%
								int pageStart = (nowBlock-1)*pagePerBlock + 1;
								int pageEnd = ((pageStart + pagePerBlock -1)<totalPage)?(pageStart + pagePerBlock - 1):totalPage;
							%>
							<%
								for( ;pageStart<=pageEnd;pageStart++) {
							%>
								<li class="page-item"><a class="page-link" href="javascript:paging('<%=pageStart%>')"><%=pageStart %></a></li>
							<%
								}
							%>

							<!-- 다음으로 : 마지막 페이지에서는 보이지 않아야 한다. -->
							<%
							if(nowBlock < totalBlock) {
							%>
								<li class="page-item"><a class="page-link" href="javascript:block('<%=nowBlock+1%>')"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							<%
							}
							%>
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