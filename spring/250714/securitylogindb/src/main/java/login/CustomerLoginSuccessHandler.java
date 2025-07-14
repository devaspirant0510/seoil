package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomerLoginSuccessHandler 
implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 성공시 LoginService에서 authorities에 저장된 값을 확인한 후에
		// 권한에 해당하는 페이지 이동
		// authentication.getAuthorities()함수가  
		//LoginService에서 authorities에 저장된 값을 확인할 수 있음
		System.out.println(authentication.getAuthorities());
	}

}
