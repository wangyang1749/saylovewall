package com.yiban.model;

public class DiscussList {
	private int id;
	private int loveId;
	private int userId;
	private String content;
	private String userName;
	private String userHead;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLoveId() {
		return loveId;
	}
	public void setLoveId(int loveId) {
		this.loveId = loveId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserHead() {
		return userHead;
	}
	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	@Override
	public String toString() {
		return "DiscussList [id=" + id + ", loveId=" + loveId + ", userId=" + userId + ", content=" + content
				+ ", userName=" + userName + ", userHead=" + userHead + "]";
	}
	
}
