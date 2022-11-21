import java.util.Scanner;
package com.KoreaIT.java.BAM;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 실행==");
		System.out.println("명령어 ) ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.printf("입력하신 명령어 : %s\n",str);
		System.out.println("==프로그램 끝==");
	}
}
