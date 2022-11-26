package com.KoreaIT.java.BAM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastId = 0;

		List<Article> articles = new ArrayList<>();

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
				System.out.println("번호 / 제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.println(article.id + "  /  " + article.title);
				}
			} else if (str.equals("article write")) {
				LocalDateTime now = LocalDateTime.now();
				//현재 시간 날짜 데이터 가져오기
				String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
				//가지고온 데이터 String으로 변경
				int id = lastId + 1;
				lastId++;

				System.out.printf("제목 : ");
				String title = sc.nextLine();

				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body,formatedNow);
				articles.add(article);

				System.out.printf("%d글이 생성되었습니다.\n", id);

			} else if (str.startsWith("article detail ")) {

				String[] strBits = str.split(" ");

				int id = Integer.parseInt(strBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("%d번 게시물은 존재합니다.\n", id);
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.now);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
			} 

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

		}

		System.out.println("==프로그램 끝==");
		sc.close();
	}
}

class Article {
	int id;
	String title;
	String body;
	String now;
	
	public Article(int id, String title, String body,String now) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.now = now;
	}

}