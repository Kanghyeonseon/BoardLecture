<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글보기</title>
<%@ include file="/resources/includes/link.jsp" %>
<!-- css -->
<link rel="stylesheet" href="/resources/css/common.css?v=2">

</head>
<body>
	<%
		String MSG = (String) request.getAttribute("MSG");
		if(MSG!=null) {
			%>
			<script>
				alert('<%=MSG%>');
			</script>
			<%
			request.setAttribute("MSG", null);
		}
	%>
	
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
				    <li class="breadcrumb-item active" aria-current="page">Read</li>
				  </ol>
				</nav>
			</div>
			<!-- 브래드크럼 끝 -->
			
			<h1 class="mt-3 mb-3">글내용</h1>
			<%@page import="com.korea.dto.*" %>
			<%
				BoardDTO dto = (BoardDTO) request.getAttribute("dto");
				String nowPage = (String) request.getAttribute("nowPage");
				String[] filelist = null;
				String[] filesize = null;
				if(dto.getFilename()!=null) { // 파일이 없을 때는 스플릿을 하지 않도록 해야한다.
					filelist = dto.getFilename().split(";");
					filesize = dto.getFilesize().split(";");
					
				}
				// 시작번호, 끝 번호 계산
				int numPerPage = 10;
				int np = Integer.parseInt(nowPage);
				int start=(np*numPerPage)-numPerPage+1;
				int end = np * numPerPage;
			%>
			
			<form action="" method="post">
				<input type="text" id="title" name="title" class="form-control mb-3" value="<%=dto.getTitle() %>">
				<input type="text" name="writer" class="form-control mb-3" value="<%=dto.getWriter() %>" disabled> 
				<textarea name="content" id="content" rows="10" class="form-control mb-3"><%=dto.getContent() %></textarea>

				
				<a href="/Board/list.do?nowPage=<%=nowPage %>&start=<%=start %>&end=<%=end %>" class="btn btn-light"><i class="bi bi-list"></i>
				리스트</a>
				<a href="#" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#staticBackdrop2">글수정</a>
				<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop3">글삭제</a>
				<button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
				  첨부파일보기
				</button>
				
			</form>
			<p class="mt-3" style="font-size:0.5rem;">
				Remote IP : <%=dto.getIp() %>
			</p>
				
			<!-- 모달시작 -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel">첨부파일 리스트</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
						<%@page import="java.net.URLEncoder" %>
						<%
						
							if(filelist!=null) {
								for(int i=0;i<filelist.length;i++){	
									String tmpfilename = filelist[i].substring(0,filelist[i].lastIndexOf("_"));
									tmpfilename += filelist[i].substring(filelist[i].lastIndexOf("."),filelist[i].length());
									filelist[i] = URLEncoder.encode(filelist[i],"utf-8").replaceAll("\\+", "%20");							 
										out.println("<a href=/Board/download.do?filename="+filelist[i]+">"+tmpfilename+"("+filesize[i]+" byte)</a><br>");
									}
								} else {
									out.println("파일 없음");
								}
						%>	
			      </div>
			      <div class="modal-footer">
			        <a href="" class="btn btn-primary" id="downall">모두받기(무압축)</a>
			        <a href="/Board/downloadAll.do" class="btn btn-primary" id="">모두받기(압축)</a>
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			      </div>
			      
			      <!-- 다중 파일 무압축 받기 -->
						<form name="multiform">
							<% 
							if(filelist!=null){
							for(int i=0;i<filelist.length;i++) { 
								%>
								<input type=hidden name=file value=<%=filelist[i] %>>
								<% 
							} }
							%>
						</form>
						
						<script>
							$(document).ready(function () {
						
								form = document.multiform;
								var iFrameCnt = 0;
						
								$('#downall').click(function() {
									for (i = 0; i < form.childElementCount; i++) {
										fileName = form[i].value;
										var url = "/Board/download.do?filename=" + fileName;
										fnCreateIframe(iFrameCnt);
										$("iframe[name=" + iFrameCnt + "]").attr("src", url);
										iFrameCnt++;
										fnSleep(1000);
									}
								});
								fnCreateIframe = function (name) {
									var frm = $('<iframe name="' + name + '" style="display: none;"></iframe>');
									frm.appendTo("body");
								}
								fnSleep = function (delay) {
						
									var start = new Date().getTime();
									while (start + delay > new Date().getTime());
								};
							});
						</script>
					</div>
			  </div>
			</div>
			<!-- 모달끝 -->
			
		</div>	
		
		<!-- Footer -->
		
		
	
	
	<!-- *************************** 글 수정 모달 *************************** -->
	<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">게시글 패스워드 확인</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <!-- *** 글수정 폼 *** -->
	      <form action="/Board/update.do" method="post" id="updatefrm" name="updatefrm">
		      <div class="modal-body">
		        <input type="password" name="pwd" class="form-control mb-3" placeholder="Insert Password">
		        <input type="hidden" name="title">
		        <input type="hidden" name="content">
		        <input type="hidden" name="nowPage" value="<%=nowPage %>" >
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" id="updatebtn">수정요청</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      </div>
	      </form>
			  <!-- *** 글수정 폼 *** -->
	    </div>
	  </div>
	</div>
	<!-- *************************** 글 수정 모달 끝 *************************** -->
	
  <script>
		$(document).ready(function(){
			$("#updatebtn").on("click",function(){
				form=document.updatefrm;
				form[1].value = $("#title").val();
				form[2].value =  $("#content").val();
				form.submit();
			})
		})
  </script>
  
  <!-- *************************** 글 삭제 모달 시작 *************************** -->
  <div class="modal fade" id="staticBackdrop3" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">게시글 패스워드 확인</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <!-- *** 글삭제 폼 *** -->
	      <form action="/Board/delete.do" method="post" id="deletefrm" name="deletefrm">
		      <div class="modal-body">
		        <input type="password" name="pwd" class="form-control mb-3" placeholder="Insert Password">
		        <input type="hidden" name="nowPage" value="<%=nowPage %>">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" id="deletebtn">삭제요청</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      </div>
	      </form>
			  <!-- *** 글삭제 폼 *** -->
	    </div>
	  </div>
	</div>
	
  <script>
		$(document).ready(function(){
			$("#deletebtn").on("click",function(){
				form=document.deletefrm;
				form.submit();
			})
		})
  </script>
  <!-- *************************** 글 삭제 모달 끝   *************************** -->
  
  <!-- 댓글 시작 -->
  <div id="replycontainer" class="mt-3">
  	<form>
  		<div class="input-group mb-3">
			  <textarea class="form-control" rows="5" aria-label="With textarea" id="comment"></textarea>
			  <a href="javascript:postreply()" class="input-group-text">댓글 작성</a>
	  		<input type="hidden" name="nowPage" value=<%=nowPage %> id="nowPage">
			</div>
  		<div>
  			<div id="replycnt"></div>
  		</div>
  		<div class="mt-3 mb-3" style="overflow:auto; height:300px;" id="replylist">

  		</div>
  	</form>
  </div>
  <!-- 댓글  끝 -->
	</div>
</body>
<script>
	// 댓글 등록 함수
	function postreply(){
		$.ajax({
			url : '/Board/replypost.do', // key와 value형태로 전달 하는 것이다.
			type : 'GET',
			data : {"comment" : $('#comment').val(), "nowPage" : $('#nowPage').val()}, // 각 parameter에 해당하는 인자를 아이디에서 값을 뽑아서 전달한다.
			error : function(){
				alert('댓글 작성에 오류가 발생했습니다!');
			},
			success : function(result){
				listreply();
				$('#comment').val("");
			}
		});
	}
	
	// 댓글 목록 확인 함수
	function listreply(){
		$.ajax({
			url : '/Board/replylist.do', // key와 value형태로 전달 하는 것이다.
			type : 'GET',
			error : function(){
				alert('댓글 목록 확인 에러!');
			},
			success : function(result){
				// 현재 게시글의 정보를 result에 담아서 붙여넣을것이다.
				$('#replylist').html(result);
				totalreplycnt();
			}
		});
	}
	listreply();
	
	// 총 댓글 수 확인 함수 
	function totalreplycnt(){
		$.ajax({
			url : '/Board/replycnt.do', // key와 value형태로 전달 하는 것이다.
			type : 'GET',
			error : function(){
				alert('댓글 수 불러오기 에러!');
			},
			success : function(result){
				$('#replycnt').html(result);
			}
		});
	}

</script>

</html>