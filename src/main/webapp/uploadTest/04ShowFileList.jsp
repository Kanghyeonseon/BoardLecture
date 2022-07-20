<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@page import="java.io.*" %>
<%
	File dir = new File("C://Users//ASUS//Documents//upload");
	File[] files = dir.listFiles(); // 디렉토리에서 파일이름을 가져온다.
	for(int i=0; i<files.length; i++) {
		out.println("<a href=/download.do?filename=" + files[i].getName().replaceAll(" ","+") + ">"+ files[i].getName() +"</a><br>");
	} 
	//getName을 써서 해당폴더에있는 '파일명'만 가져오도록 한다.
	// download.do라는 url을 가지고있는 서블릿에 대한 링크를 가진다.
	// url은 공백을 +문자로 처리해줘야한다.
%>
</body>
</html>