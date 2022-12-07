package com.KoreaIT.java.BAM;

import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.Controller;
import com.KoreaIT.java.BAM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			String[] cmdDiv = command.split(" ");

			if (cmdDiv.length == 1) {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}

			String controllerName = cmdDiv[0];
			String actionMethodName = cmdDiv[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}
			String controllerActionName = controllerName + "/"+actionMethodName;
			
			switch(controllerActionName) {
			case "article/write":
			case "article/modify":
			case "article/delete":
			case "member/logout":
				if (controller.isLogined()==false) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				break;
			}
			switch(controllerActionName) {
			case "member/join":
			case "member/login":
				if (controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				break;
			}

			controller.doAction(command, actionMethodName);

		}

		System.out.println("==프로그램 끝==");
		sc.close();

	}

}