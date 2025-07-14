package securityloginAnotation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// Spring Security를 활성화하고 웹 보안 구성을 정의하겠다는 의미
@Configuration
@EnableWebSecurity // Spring Security의 웹 보안 기능을 활성화합니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 1. HTTP 보안 설정 (XML의 <security:http> 부분)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() // 요청에 대한 접근 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN") // /admin/** 경로에는 ROLE_ADMIN 권한 필요
                .antMatchers("/user/**").hasRole("USER")   // /user/** 경로에는 ROLE_USER 권한 필요
                .anyRequest().permitAll() // 그 외 모든 요청은 허용 (로그인 페이지, 정적 자원 등)
                .and()
            .formLogin() // 폼 기반 로그인 설정
                .loginPage("/login") // 사용자 정의 로그인 페이지 URL
                .defaultSuccessUrl("/user/index") // 로그인 성공 시 이동할 기본 URL
                .failureUrl("/login?error=true") // 로그인 실패 시 이동할 URL
                .usernameParameter("username") // 사용자명 파라미터 이름
                .passwordParameter("password") // 비밀번호 파라미터 이름
                .permitAll() // 로그인 페이지 및 관련 엔드포인트는 모든 사용자에게 허용
                .and()
            .logout() // 로그아웃 설정
                .logoutUrl("/logout") // 로그아웃 처리 URL (POST 요청)
                .logoutSuccessUrl("/login?logout=true") // 로그아웃 성공 시 이동할 URL
                .permitAll(); // 로그아웃 엔드포인트는 모든 사용자에게 허용
    }

    // 2. 인증 관리자 설정 (XML의 <security:authentication-manager> 부분)
    // 인메모리 사용자 인증 (실제 프로덕션에서는 데이터베이스 등을 사용)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // XML의 <security:user-service>에 해당하는 인메모리 사용자 설정
        auth.inMemoryAuthentication()
            // {noop} 접두사는 PasswordEncoder를 사용하지 않고 비밀번호를 평문으로 처리하겠다는 의미
            // 실제 환경에서는 강력한 암호화 (BCryptPasswordEncoder 등)를 사용해야 합니다.
            .withUser("root").password("{noop}1234").roles("ADMIN", "USER") // ROLE_ADMIN, ROLE_USER 권한 부여
            .and()
            .withUser("admin").password("{noop}1234").roles("ADMIN") // ROLE_ADMIN 권한 부여
            .and()
            .withUser("user").password("{noop}1234").roles("USER");   // ROLE_USER 권한 부여
    }

    // (옵션) 패스워드 인코더 설정
    // {noop}을 사용하지 않고 BCryptPasswordEncoder 등을 사용하려면 아래와 같이 빈을 등록해야 합니다.
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
}
//@Configuration //리스너로 등록하면 해당어노테이션이 필요없음
/*
public class SecurityConfig {

	public SecurityConfig() {
		System.out.println("security config exec!!");
	}
}
*/
