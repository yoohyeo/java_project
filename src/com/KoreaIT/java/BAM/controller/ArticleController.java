package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {

	private List<Article> articles;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articles = new ArrayList<Article>();
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다");

		articles.add(new Article(1, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 1, "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 2, "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 3, "제목3", "내용3", 33));
	}

	private void doWrite() {

		int id = articles.size() + 1;
		String regDate = Util.getTimeAndDateStr();
		String updateDate = regDate;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, updateDate, loginedMember.id, title, body);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	private void showList() {

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}
		System.out.println("번호     /    제목      /     조회      /     작성자  ");
		String tmpTitle = null;
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			if (article.title.length() > 4) {
				tmpTitle = article.title.substring(0, 4);
				System.out.printf("%3d    /   %6s    /   %5d    /   %6s    \n", article.id, tmpTitle + "...",
						article.hit, article.memberId);
				continue;
			}
			System.out.printf("%3d    /   %6s    /   %5d    /   %6s    \n", article.id, article.title, article.hit,
					article.memberId);
		}

	}

	private void showDetail() {
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		foundArticle.increaseHitCount();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
		System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
		System.out.printf("작성자 : %s\n", foundArticle.memberId);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	private void doModify() {
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다");
			return;
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

	private void doDelete() {
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다");
			return;
		}

		articles.remove(foundArticle);
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);

	}

	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

}