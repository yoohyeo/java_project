package com.KoreaIT.java.BAM.controller;

import com.KoreaIT.java.BAM.dto.Member;

public abstract class Controller {

	public abstract void doAction(String command, String actionMethodName);

	public void makeTestData() {

	}

	public static Member loginedMember;

	public static boolean isLogined() {
		return loginedMember != null;
	}
}