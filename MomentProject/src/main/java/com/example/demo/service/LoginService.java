package com.example.demo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ILoginDAO;
import com.example.demo.dto.MemberDTO;

@Service
public class LoginService implements ILoginService
{
	@Autowired
	SqlSession sqlsession;
	
	@Override
	public MemberDTO authenticate(String username, String password, boolean isAdmin)
	{
		ILoginDAO dao = sqlsession.getMapper(ILoginDAO.class);
		
		MemberDTO dto = new MemberDTO();
		
		dto.setMy_id(username);
		dto.setPw(password);
		
		
		
		
		if(isAdmin) {
			return dao.loginAdmin(dto);
		}
		else {			
			return dao.login(dto);
		}
	}
	@Override
	public void updatePassword(String username, String password)
	{
		ILoginDAO dao = sqlsession.getMapper(ILoginDAO.class);
		
		dao.updatePassword(username, password);
	}
	
	
}
