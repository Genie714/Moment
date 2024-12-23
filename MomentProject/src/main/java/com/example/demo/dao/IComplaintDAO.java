package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.dto.CPReasonDTO;
import com.example.demo.dto.ComplaintDTO;

public interface IComplaintDAO
{
	// 신고 사유 리스트 불러오기
	public ArrayList<CPReasonDTO> reasonList();
	
	// 신고 접수 처리
	public int complaintInsert(ComplaintDTO complaint);
}
