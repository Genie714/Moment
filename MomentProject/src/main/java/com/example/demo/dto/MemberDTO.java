/*====================
	MemberDTO.java
====================*/

package com.example.demo.dto;

import lombok.Data;

@Data
public class MemberDTO
{
	
	private String user_id;
	private String my_id, pw, user_name;
	private String birth_date;
	private String gender_id, gender;
	private String find_id, question;
	private String answer;
	private String signup_date;
	
	
}
