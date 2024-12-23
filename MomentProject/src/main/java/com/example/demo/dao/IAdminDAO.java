package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.GroupDTO;

public interface IAdminDAO
{
	public int countUser();
	
	public int countNewUser();
	
	public int countGroup();
	
	public int countNewGroup();
	
	public int countMoment();
	
	public int countNewMoment();
	
	public int[] countPhaseMoment();
	
	public ArrayList<AdminDTO> countGroupChange();
	
	public ArrayList<AdminDTO> countMomentChange();
	
	public ArrayList<AdminDTO> countUserChange();
	
	public int[] countGender();
	
	public ArrayList<GroupDTO> groupList();
	
}
