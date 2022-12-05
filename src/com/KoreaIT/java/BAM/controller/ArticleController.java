package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {

	private List<Article> articles;
	private Scanner sc;
	private String str;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articles = new ArrayList<Article>();
	}

	public void doAction(String str, String actionMethodName) {
		this.str = str;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			doWrite();
			break;
		case "delete":
			doDelete();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		}
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, "제목1", "내용1", Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 11));
		articles.add(new Article(2, "제목2", "내용2", Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 22));
		articles.add(new Article(3, "제목3", "내용3", Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), 33));

	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
		}
		System.out.println("번호 / 제목 / 조회");
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			System.out.println(article.id + "  /  " + article.title + "  /  " + article.hit);
		}
	}

	public void doWrite() {

//		int id = lastId + 1;
//		lastId++;
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

	}

	public void showDetail() {
		String[] strBits = str.split(" ");

		int id = Integer.parseInt(strBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		foundArticle.intcreaseHitCount();
		System.out.printf("%d번 게시물은 존재합니다.\n", id);
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("등록날짜 : %s\n", foundArticle.regDate);
		System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	private void doModify() {
		String[] commandDiv = str.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
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

	public void doDelete() {

		String[] strBits = str.split(" ");

		int id = Integer.parseInt(strBits[2]);

		int foundArticleIndex = getArticleIndexById(id);

		if (foundArticleIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		articles.remove(foundArticleIndex);
		System.out.printf("%d번 게시물을 삭제했습니다.\n", id);

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