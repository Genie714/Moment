package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.demo.dao.IPersonalDAO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.MomentDTO;


@Controller
public class PersonalController
{
	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping("/personal.action")
	public String PersonalLoad(Model model,@AuthenticationPrincipal UserDetails userDetails)
	{
		String result = "";
		
		IPersonalDAO dao = sqlSession.getMapper(IPersonalDAO.class);
		
		// 유저 아이디를 받아 개인페이지에 표기할 정보들 받아오
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof UserDetails) {
	            String user_id = ((UserDetails) principal).getUsername();
	            model.addAttribute("username", user_id);
	            // 유저의 정보(이름, 생일) 가져오기
	            MemberDTO userinfo = dao.findInfo(user_id);
	            
	            // 그룹리스트 가져오기
	            ArrayList<GroupDTO> groupList= dao.findGroup(user_id);
	            
	            // 모먼트 리스트 가져오기
	            ArrayList<MomentDTO> momentList = dao.findMoment(user_id);
	            
	            // 가장 최근 모먼트 가져오기
	            MomentDTO recentMoment = dao.findRecent(user_id);
	            
	            // 가장 가까운 다음 모먼트 가져오기
	            MomentDTO nextMoment = dao.findNext(user_id);
	            
	            // 빌딩 중인 모먼트 가져오기
	            ArrayList<MomentDTO> buildingMoment = dao.findBuilding(user_id);
	            
	            //모먼트 서머리 가져오기
	            ArrayList<MomentDTO> summaryMoment = dao.findSummary(user_id);
	            
	            
	            model.addAttribute("userinfo", userinfo);
	            model.addAttribute("groupList", groupList);
	            model.addAttribute("momentList", momentList);
	            model.addAttribute("recentMoment", recentMoment);
	            model.addAttribute("nextMoment", nextMoment);
	            model.addAttribute("buildingMoment", buildingMoment);
	            model.addAttribute("summaryMoment", summaryMoment);
	            
	        }
	    } else {
	        throw new RuntimeException("User is not authenticated");
	    }
		
		
		
		result = "Personal";
		
		return result;
	}
	
	@GetMapping("/personalcalendar.action")
	public String PersonalCalendarLoad(Model model, @AuthenticationPrincipal UserDetails userDetails)
	{
		
		IPersonalDAO dao = sqlSession.getMapper(IPersonalDAO.class);
		String user_id = userDetails.getUsername();
		
		// 모먼트 리스트 가져오기
		ArrayList<MomentDTO> momentList = dao.findMoment(user_id);
		
		model.addAttribute("momentList", momentList);
		return "PersonalCalendar";
	}
	
}
