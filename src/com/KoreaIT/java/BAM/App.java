package com.KoreaIT.java.BAM;

import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.Controller;
import com.KoreaIT.java.BAM.controller.MemberController;

public class App {

	public App() {

	}

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);

		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();

		while (true) {

			System.out.printf("명령어 ) ");

			String str = sc.nextLine().trim();

			if (str.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			if (str.equals("exit")) {
				break;
			}

			String[] strDiv = str.split(" ");
			if (strDiv.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			String controllerName = strDiv[0];
			String actionMethodName = strDiv[1];

			Controller controller = null;
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
			controller.doAction(str, actionMethodName);

		}

		System.out.println("==프로그램 끝==");
		sc.close();
	}

}