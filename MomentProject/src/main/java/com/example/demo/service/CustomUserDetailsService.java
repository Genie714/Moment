package com.example.demo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ILoginDAO;
import com.example.demo.dto.MemberDTO;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	
	@Autowired 
	private SqlSession sqlsession;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		ILoginDAO dao = sqlsession.getMapper(ILoginDAO.class);
		
		System.out.println("search user : " + username);
		
		MemberDTO user = new MemberDTO();
		
		user.setMy_id(username);
		
		MemberDTO result= dao.login(user);
		
		UserDetails details = User.builder()
				.username(result.getMy_id())
				.password(result.getPw())
				.roles("USER")
				.build();
		
		return details;
		
		
	}
	
}
