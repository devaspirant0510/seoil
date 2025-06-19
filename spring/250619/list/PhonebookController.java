package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.PhonebookService;
import vo.Phonebook;

@Controller
@RequestMapping("/phonebook")
public class PhonebookController{
	
	@Autowired
	PhonebookService service;
	
	public PhonebookController() {
		System.out.println("phonebook controller!! /phonebook/");
	}
	
	//@RequestMapping("/phonebook/save")
	@RequestMapping("save")
	public String save() {
		return "save";
	}
	
	@RequestMapping("insertform")
	public String insertform() {
		return "insert";
	}
	
	/*
	@RequestMapping("insert")
	public String insert(String name, String hp, String email, String memo) {
		System.out.println(name);
		System.out.println(hp);
		System.out.println(email);
		System.out.println(memo);
		return "insert";
	}
	*/
	
	@RequestMapping("insert")
	public String insert(Phonebook phonebook) {
		System.out.println(phonebook);
		int result=service.save(phonebook);
		if(result>0) {
			return "success";
		}else {
			return "error";
		}
		
	}
	
	@RequestMapping("list")
	public ModelAndView list() {
		//서비스로 데이터요청-모델에 데이터저장-뷰로 페이지이동(data+view)
		List<Phonebook> list=service.list();
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("list"); // /WEB-INF/views/list.jsp
		return mv;
	}
	
	@RequestMapping("view")
	public String view() {
		return null;
	}
	
	@RequestMapping("updateform")
	public String updateform() {
		return null;
	}
	
	@RequestMapping("update")
	public String update() {
		return null;
	}
	
	@RequestMapping("delete")
	public String delete() {
		return null;
	}
}
