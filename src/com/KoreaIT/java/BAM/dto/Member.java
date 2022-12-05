package com.KoreaIT.java.BAM.dto;

public class Member extends Dto{
	public String loginId;
	public String loginPassWord;
	public String userName;
	

	public Member(int id, String loginId, String loginPassWord, String userName, String regDate, String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.loginPassWord = loginPassWord;
		this.userName = userName;
		this.regDate = regDate;
		this.updateDate = updateDate;
		
	}
	
}