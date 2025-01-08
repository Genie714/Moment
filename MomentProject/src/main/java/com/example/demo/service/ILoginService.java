package com.example.demo.service;

import com.example.demo.dto.MemberDTO;

public interface ILoginService
{
	public MemberDTO authenticate(String username, String password, boolean isAdmin);

	public void updatePassword(String username, String password);
}
