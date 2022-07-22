<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@page import="com.korea.dao.*, com.korea.dto.*, java.util.*" %>
<%
	BoardDAO dao = BoardDAO.getInstance();
	List<BoardDTO> list = dao.Select(1, 10);
%>
<h1>게시글 정보</h1>
<%
	for(int i=0; i<list.size(); i++){
		out.println("글번호 : " + list.get(i).getNo() + " ");
		out.println("글제목 : " + list.get(i).getTitle() + " ");
		out.println("글내용 : " + list.get(i).getContent() + " ");
		out.println("작성자 : " + list.get(i).getWriter() + "<br>");
	}
%>
</body>
</html>