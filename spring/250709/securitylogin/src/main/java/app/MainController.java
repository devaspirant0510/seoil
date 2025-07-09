package app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/all")
public class MainController {
	
	@RequestMapping("index")
	public String index() {
		return "all"; //views/all.jsp
	}
}
