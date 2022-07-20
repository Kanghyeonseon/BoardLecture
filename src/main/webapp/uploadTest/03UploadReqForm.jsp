<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/upload3" enctype="multipart/form-data">
		Upload's file : <input type="file" name="test" multiple>
		<input type="submit" value="upload">
	</form>
</body>
</html>