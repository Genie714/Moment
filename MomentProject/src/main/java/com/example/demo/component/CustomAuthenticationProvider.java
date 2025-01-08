package com.example.demo.component;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.service.CustomAdminDetailsService;
import com.example.demo.service.CustomUserDetailsService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	private final CustomUserDetailsService userDetailService;
	
	private final CustomAdminDetailsService adminDetailService;
	
	private final PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, CustomAdminDetailsService adminDetailsService, PasswordEncoder passwordEncoder)
	{
		this.adminDetailService = adminDetailsService;
		this.userDetailService = customUserDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		boolean isAdmin = (Boolean)authentication.getDetails();
		
		// 관리자 체크 유무에 따라 조회 분기
		UserDetails user = isAdmin?
				adminDetailService.loadUserByUsername(username)
				: userDetailService.loadUserByUsername(username);

		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("invaild password");
		}
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
		
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
