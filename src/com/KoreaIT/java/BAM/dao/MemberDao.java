package com.KoreaIT.java.BAM.dao;


import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BAM.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}
	public void add(Member member) {
		members.add(member);
		lastId++;
	}


}