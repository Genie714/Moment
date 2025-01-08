package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.component.CustomAuthenticationFilter;
import com.example.demo.component.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Lazy
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception 
	{
		//커스텀 필터 적용
		CustomAuthenticationFilter customFilter = new CustomAuthenticationFilter();
		
		//인증 요청 위임
		customFilter.setAuthenticationManager(authenticationManager);
		
		//커스텀 필터 요청 URL 수정
		customFilter.setFilterProcessesUrl("/login.action");
		
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session
			        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션 항상 생성
			    )
			.authorizeHttpRequests(auth -> auth
					.anyRequest()
					.permitAll())
			.addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin(form -> form
					.loginPage("/loginform.action")
					.loginProcessingUrl("/login.action")
					.defaultSuccessUrl("/main.action", true)
					.failureUrl("/login.action?error=true")
					.permitAll()
					)
			.logout(logout -> logout
					.logoutUrl("/logout.action")
					.invalidateHttpSession(true)                // 세션 무효화
			        .deleteCookies("JSESSIONID")                // 쿠키 삭제
	                .logoutSuccessUrl("/main.action")
					.permitAll());                                // 모든 사용자 접근 허용
		
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager()
	{
		return new ProviderManager(customAuthenticationProvider);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*.authorizeHttpRequests(auth -> auth
					.anyRequest()
					.permitAll()) */
