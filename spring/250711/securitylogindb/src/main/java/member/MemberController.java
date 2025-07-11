package member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
//@RequestMapping("/member")
public class MemberController {

	@GetMapping("register")
	public void getRegister() {} //WEB-INF/views/member/register.jsp
}
