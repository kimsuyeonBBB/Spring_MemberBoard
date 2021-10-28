package com.hiball.api.domain;

public class BoardInfo {
	private int no;
	private String name;
	private String title;
	private String story;
	private String createdDate;
	private String boardcount;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getBoardcount() {
		return boardcount;
	}
	public void setBoardcount(String boardcount) {
		this.boardcount = boardcount;
	}
}
