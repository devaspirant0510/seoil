<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
<table>
<thead>
<tr>
	<th>아이디</th>
	<th>이름</th>
	<th>전화번호</th>
	<th>이메일</th>
	<th>메모</th>
</tr>
</thead>
<tbody>

<%
//java code
Class.forName("org.h2.Driver");
Connection conn=DriverManager
.getConnection("jdbc:h2:tcp://localhost/~/test", "sa","");
		
String sql="select * from phonebook";
PreparedStatement ps=conn.prepareStatement(sql);
ResultSet rs=ps.executeQuery();
while(rs.next()) {
%>
<tr>
	<td><%=rs.getInt("id")%></td>
	<td><%=rs.getString("name")%></td>
	<td><%=rs.getString("hp")%></td>
	<td><%=rs.getString("email")%></td>
	<td><%=rs.getString("memo")%></td>
</tr>
<%
}
rs.close();
ps.close();
conn.close();
%>
</tbody>
</table>
</body>
</html>