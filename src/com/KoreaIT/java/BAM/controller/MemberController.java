package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private String str;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = new ArrayList<Member>();
	}

	public void doAction(String str, String actionMethodName) {
		this.str = str;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		}
	}

	public void doJoin() {
		String regDate = Util.getTimeAndDateStr();
		String updateDate = regDate;
		int id = members.size() + 1;

		String loginId = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s는 사용중인 아이디 입니다.\n", loginId);
				continue;
			}
			System.out.println("사용 가능한 아이디 입니다.");
			break;
		}
		String loginPassWord = null;
		String loginPassWordCheck = null;

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPassWord = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인: ");
			loginPassWordCheck = sc.nextLine();

			if (loginPassWord.equals(loginPassWordCheck) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String userName = sc.nextLine();

		Member member = new Member(id, loginId, loginPassWord, userName, regDate, updateDate);
		members.add(member);

		System.out.printf("%d번 회원이 가입되었습니다.\n", id);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

}