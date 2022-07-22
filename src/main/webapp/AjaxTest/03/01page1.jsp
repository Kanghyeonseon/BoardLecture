<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@page import="com.korea.dao.*, com.korea.dto.*" %>
<%
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.Select("admin@admin.com");
%>

<h1>멤버 정보</h1>
email : <%=dto.getEmail() %><br>
addr1 : <%=dto.getAddr1() %><br>
addr2 : <%=dto.getAddr2() %><br>
</body>
</html>