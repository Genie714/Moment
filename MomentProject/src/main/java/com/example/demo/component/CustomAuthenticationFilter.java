package com.example.demo.component;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException
	{
		//로그인 요청 받아오기
		String username = request.getParameter("my_id");
		String password = request.getParameter("pw");
		boolean isAdmin = request.getParameter("isAdmin") != null;

		//Authentication 객체 생성
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		
		// 객체에 관리자 체크 유무 추가
		token.setDetails(isAdmin);
		
		
		
		return this.getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException
	{
		//SecurityContext 설정
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		
		// 인증 결과 저장
		context.setAuthentication(authResult);
		
		SecurityContextHolder.setContext(context);
		
		// 세션에 컨텍스트 저장
		HttpSession session = request.getSession(true); // 세션이 없으면 생성
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
		
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
}
