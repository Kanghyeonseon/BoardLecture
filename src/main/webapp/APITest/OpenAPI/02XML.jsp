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
	<div class="container-md w-75" style="margin:50px auto;">
		<div class="row"> <!-- row시작 -->
			<div class="col-md-2">
				<label class="form-check-label" for="tstart">출발지터미널</label>
				<select name="depTerminalId" id="tstart" class="form-select"></select>
			</div>
			<div class="col-md-2">
				<label class="form-check-label" for="tend">목적지터미널</label>
				<select name="arrTerminalId" id="tend" class="form-select"></select>
			</div>
			<div class="col-md-2">
				<label class="form-check-label" for="date">출발날짜</label>
				<input type="date" name="depPlandTime" id="date" class="form-select">
			</div>
			<div class="col-md-2">
				<label class="form-check-label" for="busGradeId">버스등급</label>
				<select name="busGradeId" id="busGradeId" class="form-select">
					<option value="1" selected>고속</option>
					<option value="2">고속</option>
					<option value="3">심야우등</option>
					<option value="4">심야고속</option>
					<option value="5">일반</option>
					<option value="6">일반심야</option>
					<option value="7">프리미엄</option>
					<option value="8">심야프리미엄</option>
				</select>
			</div>
			<div>
				<br><a href="javascript:ReqBusInfo()" class="btn btn-primary">조회</a>
			</div>
		</div> <!-- row 끝 -->
		
		<div class="row"> <!-- 전달받은 정보를 넣을 테이블 -->
			<table id="resulttbl" class="table table-striped"></table>
		</div>
	</div>

</body>

<script>
	$.ajax({
		type : "GET",
		dataType : "xml",
		contentType : "application/xml",
		url : "/ShowBusXML.Info",
		success : function(result){
			//alert(result);
			console.log(result);
			alert(result);
			$(result).find('item').each(function(){ // 전체 item이 list형태로 가져와진다. each.
				var terminalId = $(this).find("terminalId").text();	//각각의 item을 반복하면서 terminalId를 찾아서 text 형태로 가져온다.
				var terminalNm = $(this).find("terminalNm").text();
				$('#tstart').append("<option value = " + terminalId + ">" + terminalNm + </option>);
				$('#tend').append("<option value = " + terminalId + ">" + terminalNm + </option>);

				console.log("id : " + terminalId + " name : " + terminalNm);
			})
			
			/*
			bodylist = result["response"]["body"]["items"]["item"];
			for(i=0; i<bodylist.length;i++) {
				// 출발, 도착 터미널에 옵션으로 터미널아이디, 표시는 이름으로 되도록 설정한다.
				$('#tstart').append("<option value="+ bodylist[i].terminalId +">" + bodylist[i].terminalNm + "</option>");
				$('#tend').append("<option value="+ bodylist[i].terminalId +">" + bodylist[i].terminalNm + "</option>");
			}
			*/
		},
		error : function(request, status, error) {
			alert("실패!!");
		}
	})
	
	function ReqBusInfo() {
		$.ajax({
			type : "GET",
			dataType : "json",
			url : "/ReqBus.Info",
			data : {
				"depTerminalId" : $("#tstart").val(), "arrTerminalId" : $("#tend").val(), "depPlandTime" : $("#date").val(),
				"busGradeId" : $("#busGradeId").val()
			},
			contentType : "application/json",
			success : function(result) {
				$("#resulttbl").children().remove();
				
				bodylist = result["response"]["body"]["items"]["item"];
				str = "<tr>"; 
				str+="<th>출발지터미널</th>"
				str+="<th>목적지터미널</th>"
				str+="<th>출발시간</th>"
				str+="<th>도착시간</th>"
				str+="<th>요금</th>"
				str+="</tr>"
				
				for(i=0; i<bodylist.length; i++) {
					str+="<tr>"
					str+="<td>"+ bodylist[i].depPlaceNm +"</td>"
					str+="<td>"+ bodylist[i].arrPlaceNm +"</td>"
					str+="<td>"+ bodylist[i].depPlandTime +"</td>"
					str+="<td>"+ bodylist[i].arrPlandTime +"</td>"
					str+="<td>"+ bodylist[i].charge +"</td>"
					str+="</tr>"
				}
				$("#resulttbl").append(str);

			},
			error : function() {
				
			}
			
		})
	}
	
	

</script>

</html>