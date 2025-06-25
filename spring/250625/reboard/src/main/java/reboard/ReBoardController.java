package reboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
}
