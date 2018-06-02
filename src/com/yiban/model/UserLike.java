package com.yiban.model;

public class UserLike {
	private int loveId;
	private int userId;
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
	@Override
	public String toString() {
		return "UserLike [loveId=" + loveId + ", userId=" + userId + "]";
	}
	
}
