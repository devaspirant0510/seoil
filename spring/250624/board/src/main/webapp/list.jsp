<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="board.Writing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="lombok.ToString"%>
<%@page import="lombok.Getter"%>
<%@page import="lombok.Setter"%>
<%@page import="lombok.NoArgsConstructor"%>
<%@page import="lombok.AllArgsConstructor"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
//jsp페이지내에서 어노테이션 선언은 적용이 안됨
/*
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Writing{
	int id;
	String title;
	String author;
	Date createdate;
	String content;
	String attatchement;
	int viewcnt;
	String type;
}
*/
%>
<%
//sql연결하기 위한 설정(oracle)
Class.forName("oracle.jdbc.driver.OracleDriver");
String url="jdbc:oracle:thin:@localhost:1521:xe";
String user="system";
String password="system";
Connection conn=DriverManager.getConnection(url, user, password);
System.out.println(conn);

int totalCount=0; //글의 전체 갯수 ->자동계산
//String sql="select count(*) from board";
String sql="select count(*) count from board";
PreparedStatement ps=conn.prepareStatement(sql);
ResultSet rs=ps.executeQuery();
if(rs.next()){
	//totalCount=rs.getInt("count(*)");
	totalCount=rs.getInt("count");
}
rs.close();
ps.close();
conn.close();
System.out.println("글의 전체 갯수:"+totalCount);

int pagePerCount=10; //1페이지의 글의 갯수 ->수동입력

int totalPage=0; //전체페이지 ->자동계산
totalPage=totalCount/pagePerCount;
System.out.printf("전체페이지:%d\n",totalPage);
//상황적인 부분확인
//전체글이 111개 일 때 페이지수는 12page
//전체글이 110개 일 때 페이지수는 11page
//나머지 연산자를 이용하여 나머지 값이 0인경우, 그렇지 않은 경우
//totalCount=135;
if((totalCount%pagePerCount) == 0){
	totalPage=totalCount/pagePerCount;
}else{
	totalPage=(totalCount/pagePerCount)+1;
}
System.out.printf("전체페이지:%d\n",totalPage);


int requestPage=1;//요청한페이지 or 현재페이지 ->수동입력
//페이지에 요청 페이지가 전달되는 경우
String _requestPage=request.getParameter("requestPage");
if(_requestPage!=null){
	requestPage=Integer.parseInt(_requestPage);
}
//http://localhost:8888/board/list.jsp?requestPage=3
//요청한 페이지에 대한 시작글번호와 끝글번호가 필요
//글의 시작번호, 글의 끝번호
//고려사항:글의 번호가 순서적으로 되어 있다고 가정
//글의 번호가 순서적으로 되어 있지 않은 경우 oracle rownum, 날짜의 순서 order by 필드명 desc
int startnum=1; //글의 시작번호 -> 자동계산
int endnum=10; //글의 끝번호 ->자동계산
startnum=((requestPage-1)*pagePerCount)+1;
endnum=requestPage*pagePerCount;
System.out.printf("요청페이지:%d\n",requestPage);
System.out.printf("글 시작번호:%d\n",startnum);
System.out.printf("글 끝번호:%d\n",endnum);

//시나리오 : 요청한페이지가 1페이지 일때 시작페이지1, 끝페이지5
//시나리오 : 요청한페이지가 2페이지 일때 시작페이지1, 끝페이지5
//시나리오 : 요청한페이지가 5페이지 일때 시작페이지1, 끝페이지5
//시나리오 : 요청한페이지가 6페이지 일때 시작페이지6, 끝페이지10
//시나리오 : 요청한페이지가 11페이지 일때 
//전체페이지가 12페이지 일때 시작페이지10, 끝페이지12
//요청한페이지의 네비게이트 시작페이지 번호, 끝페이지 번호
//requestPage=1; 
int startPage=0; //네비게이트 시작번호 ->자동계산
int endPage=0;//네비게이트 끝번호 ->자동계산
startPage=((requestPage-1)/5 * 5) + 1;
endPage=startPage + (5-1);
if(endPage>totalPage) endPage=totalPage;
System.out.printf("네이게이트 시작페이지:%d\n",startPage);
System.out.printf("네이게이트 끝페이지 :%d\n",endPage);

//이전페이지 다음페이지 표시 여부 확인 속성
boolean isPre=false;
boolean isNext=false;
//시나리오: 요청한 페이지 1~5페이지일 경우 이전페이지 클릭 비활성화
//시나리오: 요청한 페이지 6~10페이지일 경우 이전페이지, 다음페이지 클릭 활성화
//시나리오: 요청한 페이지 11페이지일 경우 이전페이지 활성화, 다음페이지 비활성화
//요청한 페이지의 게시판 리스트
if(requestPage>5) isPre=true;
//시나리오 요청한 페이지:11page, 전체페이지:12page, 마지막페이지:12page
//11페이지에서 전체페이지 endPage:12page
if(endPage<totalPage) isNext=true;
System.out.println("네이게이트 이전링크표시여부:\n"+isPre);
System.out.println("네이게이트 다음링크표시여부:\n"+isNext);

//시나리오:시작글번호와 끝글번호에 대한 리스트데이터
List<Writing> list=new ArrayList();
//startnum=1; //글의 시작번호
//endnum=10; //글의 끝번호
conn=DriverManager.getConnection(url, user, password);
//sql="select * from (select rownum rid,t1.* from (select * from board order by id asc) t1) where rid>=11 and rid<=20";
StringBuffer sql2=new StringBuffer();
sql2.append("select * from ");
sql2.append("(select rownum rid, t1.* from ");
sql2.append("(select * from board order by id asc) t1) ");
sql2.append("where rid>=? and rid<=?");
ps=conn.prepareStatement(sql2.toString());
System.out.println(sql2.toString());

ps.setInt(1, startnum);
ps.setInt(2, endnum);
rs=ps.executeQuery();
while(rs.next()){
	//System.out.println(rs.getDate("createdate"));
	Writing write=new Writing();
	write.setId(rs.getInt("id"));
	write.setTitle(rs.getString("title"));
	write.setAuthor(rs.getString("author"));
	write.setCreatedate(rs.getDate("createdate"));
	write.setViewcnt(rs.getInt("viewcnt"));
	list.add(write);
}
//jsp페이지 내에서는 ${}를 사용하기 위해서 setAttribute에 저장
request.setAttribute("list",list); 
System.out.println(list);
rs.close();
ps.close();
conn.close();
%>
<!-- 위쪽은 jsp코드로서 데이터를 획득/아래쪽은 데이터로 화면표시 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판 테이블</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">게시판</h2>
        <div><%=totalCount%>개의 글(현재<%=requestPage%>page/전체<%=totalPage%>page)</div>
        <table class="table table-bordered table-hover">
            <thead class="table-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                </tr>
            </thead>
            <tbody>
                <!-- Writing 데이터를 반복처리 -->
                <c:forEach var="board" items="${list}">
                <tr>
                    <td name="id">${board.id}</td>
                    <td name="title">${board.title}</td>
                    <td name="author">${board.author}</td>
                    <td name="createdate">${board.createdate}</td>
                    <td name="viewcnt">${board.viewcnt}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 페이지 네비게이션 -->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
            <% 
            //if(isPre){ 
            %>
                <li id="pre" class="page-item disabled">
                    <a class="page-link" href="/board/list.jsp?requestPage=<%=startPage-5%>" tabindex="-1">이전</a>
                </li>
            <% 
            //}
            if(isPre){
            	System.out.println("pre활성화");
            %>
            <script>
            document.getElementById("pre").className +="disabled"
            //document.getElementById("pre").disabled=false;
            //document.getElementById("pre").setAttribute("disabled", false)
            //document.getElementById("pre").removeAttribute("disabled");

            </script>
            <%	
            }else{
            	System.out.println("pre비활성화");
            %>
            <script>
            document.getElementById("pre").className.replace("disabled","");
            //document.getElementById("pre").disabled=true;
             //document.getElementById("pre").setAttribute("disabled", true)
            </script>
            <%}%>    
                <%for(int i=startPage;i<=endPage;i++){ 
                //문제:요청한페이지와 화면에 표시될 페이지가 같은 경우 active를 class에 추가하기 
	                if(requestPage==i){
	             %>
	            <li class="page-item">
                <a class="page-link active" href="/board/list.jsp?requestPage=<%=i%>"><%=i%></a>
                </li>
                <%  
	                }else{	                	
	            %>
	            <li class="page-item">
                <a class="page-link" href="/board/list.jsp?requestPage=<%=i%>"><%=i%></a>
                </li>	
	            <%
	                }
                } 
                %>
                
             <% if(isNext){ %>
                <li class="page-item">
                    <a class="page-link" href="/board/list.jsp?requestPage=<%=startPage+5%>">다음</a>
                </li>
              <% }  %>
            </ul>
        </nav>
    </div>

    <!-- Bootstrap JS (선택사항) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
