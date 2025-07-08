package member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RequestMapping("/member")
public class MemberController {

	public MemberController() {
		System.out.println("member 컨트롤러 생성");
	}
	
	@RequestMapping("index")
	public String index() {
		return "index"; //views/member/index.jsp
	}
}
