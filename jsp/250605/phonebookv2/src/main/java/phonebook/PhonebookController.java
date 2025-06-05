package phonebook;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/")
public class PhonebookController extends HttpServlet{
	PhonebookService service;
	
	public PhonebookController() {
		service=new PhonebookService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String page="list.jsp";
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURI().split("/").length);
		
		if(req.getRequestURI().split("/").length==0) {
			resp.sendRedirect("/list");
			return;
		}
		
		switch(req.getRequestURI().split("/")[1]) {
		case "insertForm": 
			System.out.println("입력폼요청");
			//resp.sendRedirect("/WEB-INF/insertform.jsp");
			//req.getRequestDispatcher("/WEB-INF/insertform.jsp").forward(req, resp);
			page="insertform.jsp";
			break;
		case "insert": 
			System.out.println("입력처리");
			MultipartRequest multi=new MultipartRequest
			(req,
			"D:/work/eclipse-workspace/phonebookv2/src/main/webapp/img",
			5*1024*1024,
			"UTF-8",
			new DefaultFileRenamePolicy()
			);
			
			Enumeration<?> files=multi.getFileNames();
			String filename=(String)files.nextElement();//name을획득
			
			int result=service.insert(
					new Phonebook(0, 
							multi.getParameter("name"), 
							multi.getParameter("hp"), 
							multi.getParameter("email"), 
							multi.getParameter("memo"),
							multi.getFilesystemName(filename)
							)
					);
			if(result>0) {req.setAttribute("state","insert success");}
			else {req.setAttribute("state","insert fail");}
			//req.getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
			page="result.jsp";
			break;
		case "list": 
			System.out.println("전체출력");
			List<Phonebook> list=service.findAll();
			req.setAttribute("list",list);
			//req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
			page="list.jsp";
			break;
		case "view": 
			System.out.println("선택출력");
			req.setAttribute("pb",
				service.findById(Integer.parseInt(req.getParameter("id"))));
			//req.getRequestDispatcher("/WEB-INF/view.jsp").forward(req, resp);
			page="view.jsp";
			break;
		case "updateForm": 
			System.out.println("수정폼요청");
			//아이디를 이용하여 수정할 정보를 들고오는 작업
			req.setAttribute("pb",
					service.findById(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/WEB-INF/updateform.jsp").forward(req, resp);
			page="updateform.jsp";
			break;
			
		case "update":
			MultipartRequest multi2=new MultipartRequest
			(req,
			"D:/work/eclipse-workspace/phonebookv2/src/main/webapp/img",
			5*1024*1024,
			"UTF-8",
			new DefaultFileRenamePolicy()
			);
			
			Enumeration<?> files2=multi2.getFileNames();
			String filename2=(String)files2.nextElement();//name을획득
			
			result=service.updateById(
					new Phonebook(Integer.parseInt(multi2.getParameter("id")), 
							multi2.getParameter("name"), 
							multi2.getParameter("hp"), 
							multi2.getParameter("email"), 
							multi2.getParameter("memo"),
							multi2.getFilesystemName(filename2)
							)
					);
			if(result>0) {req.setAttribute("state","update success");}
			else {req.setAttribute("state","update fail");}
			//req.getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
			page="result.jsp";
			System.out.println("수정처리");
			break;
		case "delete": 
			System.out.println("삭제처리");
			result=service.deleteById(Integer.parseInt(req.getParameter("id")));
			if(result>0) {req.setAttribute("state","delete success");}
			else {req.setAttribute("state","delete fail");}
			//req.getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
			page="result.jsp";
			break;
		default: resp.sendRedirect("/list"); return; 
		}
		
		req.getRequestDispatcher("/WEB-INF/"+page).forward(req, resp);
	}
}






