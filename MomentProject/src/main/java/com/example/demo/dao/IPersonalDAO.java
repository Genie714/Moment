package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.MomentDTO;

public interface IPersonalDAO
{
	public MemberDTO findInfo(String user_id);
	
	public ArrayList<GroupDTO> findGroup(String user_id);
	
	public ArrayList<MomentDTO> findMoment(String user_id);
	
	public MomentDTO findRecent(String user_id);
	
	public MomentDTO findNext(String user_id);
	
	public ArrayList<MomentDTO> findBuilding(String user_id);
	
	public ArrayList<MomentDTO> findSummary(String user_idq);
	
	
}
