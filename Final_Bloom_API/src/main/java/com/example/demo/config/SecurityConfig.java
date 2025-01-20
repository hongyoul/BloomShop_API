package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.member.service.MemberService;
import com.example.demo.member.service.MemberServiceImpl;
import com.example.demo.security.ApiCheckFilter;
import com.example.demo.security.ApiLoginFilter;
import com.example.demo.security.UserDetailsServiceImpl;

// 스프링 시큐리티 설정 클래스
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// API Check 필터에서 인증객체를 생성할 때 사용됨
	@Bean
	UserDetailsService customUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	// 회원가입시 패스워드를 암호화할때, 로그인시 패스워드를 대조할 때 사용됨
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 데이터베이스에서 회원 정보를 조회하는 서비스
	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl();
	}

	// 필터를 빈으로 등록하면, 자동으로 필터 체인에 추가됨
	@Bean
	public ApiCheckFilter apiCheckFilter() {
		return new ApiCheckFilter(customUserDetailsService());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 폼 로그인 기능 비활성화
		http.formLogin().disable();
		
		// 세션 상태 관리 비활성화 (서버에서 세션 생성 안함)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// 세션 사용안함
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// 인증 없이 접근 가능한 경로 설정 (로그인 등)
		// 처음에는 모든 경로를 권한이 없는 사용자에게 허용
		http.authorizeHttpRequests()
		.requestMatchers("/login","/register", "/product/*").permitAll()
		.requestMatchers("/cart/*", "/orders/*", "/orders_detail/*", "/member/*", "/review/myList", "/review/register", "/review/*").authenticated(); //로그인한 회원만 접속 가능
		
		// CSRF 비활성화
		http.csrf().disable();
		
		
		/* API 로그인 필터 */
		/* API 로그인 필터에 필요한 인증 매니저 생성 */
		// 인증매니저 설정
 		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);	
 		authenticationManagerBuilder.userDetailsService(customUserDetailsService())
					 				.passwordEncoder(passwordEncoder());	
 		
 		// 인증매니저 생성
 		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

 		// 시큐리티에 인증매니저 등록
 		http.authenticationManager(authenticationManager);
 		
 		// ApiLoginFilter 생성 및 등록
 		// /login 요청이 들어오면 필터가 실행됨
//		ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login");	
		ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", memberService());	

		//로그인 필터에 인증매니저 주입
		apiLoginFilter.setAuthenticationManager(authenticationManager);
		
		// UsernamePasswordFilter는 폼로그인에서 사용하는 필터이다
		// apiLoginFilter가 UsernamePasswordFilter보다 먼저 실행되도록 설정한다
		 http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);

		/* API 체크 필터 */
		// apiCheckFilter가 UsernamePasswordFilter보다 먼저 실행되도록 설정
		http.addFilterBefore(apiCheckFilter(), 
							UsernamePasswordAuthenticationFilter.class); 
		 
		return http.build();
	}	

}
