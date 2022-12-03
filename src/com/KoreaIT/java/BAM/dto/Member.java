package com.KoreaIT.java.BAM.dto;

public class Member {
	public int seq;
	public String loginId;
	public String loginPassWord;
	public String userName;
	public String regDate;
	public String updateDate;
	

	public Member(int seq, String loginId, String loginPassWord, String userName, String regDate, String updateDate) {
		this.seq = seq;
		this.loginId = loginId;
		this.loginPassWord = loginPassWord;
		this.userName = userName;
		this.regDate = regDate;
		this.updateDate = updateDate;
		
	}
	
}