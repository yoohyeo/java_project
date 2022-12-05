package com.KoreaIT.java.BAM.dto;

public class Article extends Dto {
	
	public String title;
	public String body;
	public int hit;

	public Article(int id, String title, String body, String updateDate, String regDate) {
		this(id,title,body,updateDate,regDate,0);
	}
	public Article(int id, String title, String body, String updateDate, String regDate,int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.hit = hit;
	}

	public void intcreaseHitCount() {
		hit++;
	}

}