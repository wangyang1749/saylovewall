package com.yiban.model;

public class LoveLink {
	private int id;
	private int myUserId;
	private int toUserId;
	private String sayLove;
	private String loveImg;
	private int loveState;
	private int redState;
	private int loveRead;
	private int loveLike;
	private String loveTime;
	private YBUser Self;
	private YBUser To;
	private Discuss discuss;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMyUserId() {
		return myUserId;
	}
	public void setMyUserId(int myUserId) {
		this.myUserId = myUserId;
	}
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public String getSayLove() {
		return sayLove;
	}
	public void setSayLove(String sayLove) {
		this.sayLove = sayLove;
	}
	public String getLoveImg() {
		return loveImg;
	}
	public void setLoveImg(String loveImg) {
		this.loveImg = loveImg;
	}
	public int getLoveState() {
		return loveState;
	}
	public void setLoveState(int loveState) {
		this.loveState = loveState;
	}
	public int getRedState() {
		return redState;
	}
	public void setRedState(int redState) {
		this.redState = redState;
	}
	public int getLoveRead() {
		return loveRead;
	}
	public void setLoveRead(int loveRead) {
		this.loveRead = loveRead;
	}
	public int getLoveLike() {
		return loveLike;
	}
	public void setLoveLike(int loveLike) {
		this.loveLike = loveLike;
	}
	public String getLoveTime() {
		return loveTime;
	}
	public void setLoveTime(String loveTime) {
		this.loveTime = loveTime;
	}
	
	
	public YBUser getSelf() {
		return Self;
	}
	public void setSelf(YBUser self) {
		Self = self;
	}
	public YBUser getTo() {
		return To;
	}
	public void setTo(YBUser to) {
		To = to;
	}
	public Discuss getDiscuss() {
		return discuss;
	}
	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}
	@Override
	public String toString() {
		return "LoveLink [id=" + id + ", myUserId=" + myUserId + ", toUserId=" + toUserId + ", sayLove=" + sayLove
				+ ", loveImg=" + loveImg + ", loveState=" + loveState + ", redState=" + redState + ", loveRead="
				+ loveRead + ", loveLike=" + loveLike + ", loveTime=" + loveTime + "]";
	}
	
	
	
}
