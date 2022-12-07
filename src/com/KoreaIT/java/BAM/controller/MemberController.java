package com.KoreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = Container.memberDao.members;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}

	private void doLogout() {

		loginedMember = null;
		System.out.println("로그아웃 되었습니다");
	}

	private void doLogin() {

		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		// 사용자에게 입력받은 아이디에 해당하는 회원이 존재하는지 체크
		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}

		loginedMember = member;

		System.out.printf("로그인 성공! %s님 환영합니다\n", member.name);
	}

	private void doJoin() {
		int id = Container.memberDao.getNewId();
		String regDate = Util.getTimeAndDateStr();
		String updateDate = regDate;
		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			if (loginId.length()==0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}
			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s는 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			System.out.println("사용 가능한 아이디입니다");
			break;
		}

		String loginPw = null;
		String loginPwCheck = null;

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			if (loginPw.length()==0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}
			System.out.printf("로그인 비밀번호 확인: ");
			loginPwCheck = sc.nextLine();

			if (loginPw.equals(loginPwCheck) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		String name = null;
		while(true) {
			System.out.printf("이름 : ");
			name = sc.nextLine();
			if(name.length()==0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;
		}
		Member member = new Member(id, regDate, updateDate, loginId, loginPw, name);
		Container.memberDao.add(member);

		System.out.printf("%d번 회원이 가입 하였습니다\n", id);

	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
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

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다");

		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "admin", "admin", "관리자"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "asd", "asd", "김영희"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "qwe", "qwe", "김철수"));
	}

}























