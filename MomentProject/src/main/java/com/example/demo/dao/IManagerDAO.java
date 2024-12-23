package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.dto.GroupMemberDTO;
import com.example.demo.dto.ManagerVoteDTO;
import com.example.demo.dto.ManagerVoteeDTO;
import com.example.demo.dto.ManagerVoterDTO;

public interface IManagerDAO
{
	// 회원코드로 그룹코드 확인
	public GroupMemberDTO searchId(String user_id, String group_id);
	
	// 매니저 수 확인
	public int managerCount(String group_id);
	
	// 매니저 리스트 확인
	public ArrayList<GroupMemberDTO> managerList(String group_id);
	
	// 매니저 임명투표 후보전체리스트 확인
	public ArrayList<GroupMemberDTO> voteList(String group_id); 
	
	// 매니저 임명투표 추가
	public int voteAdd(ManagerVoteDTO vote);
	
	// 매니저 임명후보자 추가
	public int voteeAdd(ManagerVoteeDTO votee);
	
	// 매니저 임명투표자 추가
	public int voterAdd(ManagerVoterDTO voter);
	
	// 선택된 후보자의 매치코드로 후보자코드 확인
	public String searchSelectedId(GroupMemberDTO dto);
	
	// 다음 투표 코드 가져오기
	public String nextVoteId();
	
	// 투표코드가 생성되는 날짜 가져오기
	public String checkEndDate(String group_id);
	
	// 그룹 내 가장 최근에 열린 투표코드 가져오기
	public String checkVoteId(String group_id);
	
	// 해당 투표 코드에 있는 투표자들 보기
	public ArrayList<ManagerVoterDTO> voterList(String vote_id);
	
	// 투표 참여자 수 현황 보기
	public int voterCount(String vote_id);

	
	
	
}
