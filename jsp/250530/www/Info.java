package www;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/info")
public class Info extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----header----");
		Enumeration<String> headers=request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String key=headers.nextElement();
			String value=request.getHeader(key);
			System.out.println(key + ":" + value );
		}
		
		System.out.println("----body----");
		//현재 전달되는 내용이 없어서 출력되는 되는 내용이 없음
		InputStream in=request.getInputStream();
		int c=0;
		while((c=in.read()) != -1) {
			System.out.print((char)c);
		}
		
		System.out.println("전송되는 header에서 클라이언트 PC의 정보획득!");
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteHost());	
		System.out.println(request.getRemotePort());
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		System.out.println(request.getServletPath());
		System.out.println(request.getQueryString());
		System.out.println(request.getRealPath(getServletInfo()));
		
		response.getOutputStream();
		
	}
}
