package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class App {
	public static List<Article> articles = new ArrayList<>();
	public static List<Member> members = new ArrayList<>();

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();
		Scanner sc = new Scanner(System.in);
//		int lastId = 0;
		while (true) {

			System.out.printf("명령어 ) ");

			String str = sc.nextLine().trim();
			if (str.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (str.equals("exit")) {
				break;
			} else if (str.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목 / 조회");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.println(article.id + "  /  " + article.title + "  /  " + article.hit);
				}
			} else if (str.equals("member join")) {
				String regDate = Util.getTimeAndDateStr();
				String updateDate = regDate;
				int seq = members.size() + 1;

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
					
					if(loginPassWord.equals(loginPassWordCheck) == false) {
						System.out.println("비밀번호를 다시 입력해주세요.");
						continue;
					}
					break;
				}

				

				System.out.printf("이름 : ");
				String userName = sc.nextLine();

				Member member = new Member(seq, loginId, loginPassWord, userName, regDate, updateDate);
				members.add(member);

				System.out.printf("%d번 회원이 가입되었습니다.\n", seq);

			} else if (str.equals("article write")) {
//				int id = lastId + 1;
//				lastId++;
				int id = articles.size() + 1;
				String regDate = Util.getTimeAndDateStr();
				String updateDate = regDate;

				System.out.printf("제목 : ");
				String title = sc.nextLine();

				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body, updateDate, regDate);
				articles.add(article);

				System.out.printf("%d글이 생성되었습니다.\n", id);

			} else if (str.startsWith("article detail ")) {

				String[] strBits = str.split(" ");

				int id = Integer.parseInt(strBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				foundArticle.intcreaseHitCount();
				System.out.printf("%d번 게시물은 존재합니다.\n", id);
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("등록날짜 : %s\n", foundArticle.reDate);
				System.out.printf("수정날짜 : %s\n", foundArticle.reDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);

			} else if (str.startsWith("article delete ")) {

				String[] strBits = str.split(" ");

				int id = Integer.parseInt(strBits[2]);

				int foundArticleIndex = getArticleIndexById(id);

				if (foundArticleIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				articles.remove(foundArticleIndex);
				System.out.printf("%d번 게시물을 삭제했습니다.\n", id);

			} else if (str.startsWith("article modify ")) {

				String[] commandDiv = str.split(" ");

				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String newTitle = sc.nextLine();
				System.out.printf("내용 : ");
				String newBody = sc.nextLine();

				foundArticle.title = newTitle;
				foundArticle.body = newBody;
				foundArticle.updateDate = Util.getTimeAndDateStr();

				System.out.printf("%d번 게시물을 수정했습니다\n", id);

			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

		}

		System.out.println("==프로그램 끝==");
		sc.close();
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, "제목1", "내용1", Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 11));
		articles.add(new Article(2, "제목2", "내용2", Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 22));
		articles.add(new Article(3, "제목3", "내용3", Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 33));

	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return true;
		}
		return false;
	}

	private static int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private static int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private static Article getArticleById(int id) {
		/**
		 * for (int i = 0; i < articles.size(); i++) { Article article =
		 * articles.get(i);
		 * 
		 * if (article.id == id) { return article; } } for (Article article : articles)
		 * { if (article.id == id) { return article; } }
		 */

		int index = getArticleIndexById(id);
		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}
}