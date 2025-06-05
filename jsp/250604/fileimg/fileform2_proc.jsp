<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=UTF-8");
*/
//필요한 입력사항:데이터스트림객체,파일저장위치,파일크기제한,엔코딩,파일이름정책
MultipartRequest multi=new MultipartRequest(
		request,
		"D:/work/eclipse-workspace/phonebook/src/main/webapp/img",
		5*1024*1024,
		"UTF-8",
		new DefaultFileRenamePolicy()
		);
String name=multi.getParameter("name");
String hp=multi.getParameter("hp");
out.print(name+"<br>");
out.print(hp+"<br>");

Enumeration<?> files=multi.getFileNames();
String filename=(String)files.nextElement();//name을획득
out.print(filename+"<br>");
//tag의 name으로 찾을 수 있는 두 가지 정보는? 원본파일이름, 정책에 의한 파일이름
out.print(multi.getOriginalFileName(filename)+"<br>");
out.print(multi.getFilesystemName(filename)+"<br>");


%>