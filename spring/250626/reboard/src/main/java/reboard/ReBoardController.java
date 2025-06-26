package reboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reboard")
public class ReBoardController {
	
	@Autowired(required = true)
	ReBoardService service;
	
	//http://localhost:8888/reboard/list -> @RequestParam(defaultValue = "1")
	//http://localhost:8888/reboard/list?requestPage=1
	@RequestMapping("list")
	public ModelAndView list(
			@RequestParam(defaultValue = "1") int requestPage,
			ModelAndView mv) {
		//페이지정보가 있고, 페이지정보를 출력할 view jsp를 설정하는 객체(ModelAndView)
		//ModelAndView mv=new ModelAndView();
		mv.addObject("pageList", service.getPageList(requestPage));
		mv.setViewName("list");
		return mv;
	}
	
	// http://localhost:8888/reboard/writeform
	@RequestMapping("writeform")
	public String writeform() {
		return "writeform"; // /WEB-INF/views/writeform.jsp
	}
	
	//multipart/form-data전송시 입력값에 설정을 추가
	//form객체는 @ModelAttribute
	@RequestMapping("write")
	public String write(@ModelAttribute BoardForm form, 
			@RequestParam("attachment") MultipartFile file) {
		System.out.println(form);
		System.out.println(file.getOriginalFilename());
		//파일저장처리
		service.write(form,file);
		return "redirect:/reboard/list";
	}
	
	//http://localhost:8888/reboard/view?id=xx
	@RequestMapping("view")
	public ModelAndView view(int id,ModelAndView mv) {
		
		mv.addObject("page", service.getViewPage(id));
		mv.setViewName("view");
		return mv;
	}
	
	//댓글쓰기폼
	//http://localhost:8888/reboard/reply?id=x
	@GetMapping("reply")
	public String reply(@RequestParam(name = "id") int parentid, Model model) {
		//댓글에 필요한 정보는 부모글번호,제목,댓글깊이가 필요한 경우 데이터 획득
		//획득된 데이터를 객체로 만들지 않고 Model에 임시 저장
		model.addAttribute("parentid", parentid);
		model.addAttribute("title",service.getViewPage(parentid).getTitle());
		model.addAttribute("tab",service.getViewPage(parentid).getTab());
		return "reply"; //WEB-INF/views/reply.jsp(writeform.jsp복사)
	}
	//댓글처리(입력처리유사)
	//http://localhost:8888/reboard/reply
	@PostMapping("reply")
	public void reply(@ModelAttribute ReplyBoardForm form, 
			@RequestParam("attachment") MultipartFile file) {
		
	}
	
	
	
	
}
