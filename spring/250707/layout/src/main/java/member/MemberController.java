package member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("page", "member/"+ "index");
		return "index"; //무조건 views/index.jsp
	}
}
