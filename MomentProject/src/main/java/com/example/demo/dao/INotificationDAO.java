package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.dto.NotificationDTO;

public interface INotificationDAO
{
	public ArrayList<NotificationDTO> findPreInviteNoti(String user_id);
	
	public int deletepreInviteNoti(String id);
}
