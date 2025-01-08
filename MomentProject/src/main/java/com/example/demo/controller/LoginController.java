/*
 * LoginController.java
 * 로그인 관련 컨트롤러
 * 1. 로그인 처리 O
 * 2. 로그아웃 처리 o
 * 3. 회원가입 처리
 * 4. 아이디 비밀번호 찾기 처리
 */
package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ILoginDAO;
import com.example.demo.dto.MemberDTO;

@Controller
public class LoginController
{
	
	@GetMapping("/loginform.action")
	public String loginForm(@RequestParam(value = "error", required = false) String error, Model model)
	{
		String result = "LoginForm";
		
		if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }

		return result;
	}
	

	@GetMapping("/logout.action")
	public String logout(HttpSession session)
	{
		session.invalidate();
        return "redirect:main.action";
	}
}
