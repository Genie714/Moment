package com.example.demo.dao;

import com.example.demo.dto.MemberDTO;

public interface ILoginDAO
{
	public MemberDTO login(MemberDTO member);
	
	public MemberDTO loginAdmin(MemberDTO member);
}
