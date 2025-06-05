<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/fileimg/fileform2.jsp</title>
</head>
<body>

<form action="/fileimg/fileform2_proc.jsp" method="post" 
enctype="multipart/form-data">
<input type="text" name="name" id="name"><br>
<input type="text" name="hp" id="hp"><br>
<input type="file" name="file1" id="file1"><br>
<input type="submit" value="파일업로드">
</form>
</body>
</html>







