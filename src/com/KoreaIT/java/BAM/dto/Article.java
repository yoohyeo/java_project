package com.KoreaIT.java.BAM.dto;

public class Article {
	public int id;
	public String title;
	public String updateDate;
	public String body;
	public String reDate;
	public int hit;

	public Article(int id, String title, String body, String updateDate, String reDate) {
		this(id,title,body,updateDate,reDate,0);
	}
	public Article(int id, String title, String body, String updateDate, String reDate,int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.reDate = reDate;
		this.updateDate = updateDate;
		this.hit = hit;
	}

	public void intcreaseHitCount() {
		hit++;
	}

}