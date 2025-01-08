package com.example.demo.controller;


import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController
{
	@GetMapping("/main.action")
	public String main(Model model)
	{
		// 현재 사용자의 Authentication 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 로그인 여부 확인
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated()
                             && !"anonymousUser".equals(authentication.getPrincipal());

        // 로그인 여부를 JSP로 전달
        model.addAttribute("isLoggedIn", isLoggedIn);
		System.out.println(isLoggedIn);
		
		return "Main";
	}
	
	@GetMapping("/")
	public String defaultURI(Model model){
		return "redirect:/main.action";
	}
	
}
