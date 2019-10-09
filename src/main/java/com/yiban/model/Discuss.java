package com.yiban.model;

public class Discuss {
	private int id;
	private int loveLinkId;
	private int userId;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLoveLinkId() {
		return loveLinkId;
	}
	public void setLoveLinkId(int loveLinkId) {
		this.loveLinkId = loveLinkId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Discuss [id=" + id + ", loveLinkId=" + loveLinkId + ", userId=" + userId + ", content=" + content + "]";
	}
	
}
