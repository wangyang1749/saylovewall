package com.yiban.model;

public class LoveList {
	private String myName;
	private String myhead;
	private String mySchool;
	private String toName;
	private String tohead;
	private String toSchool;
	private String img;
	private String sayLove;
	private String time;
	private int like;
	private int read;
	private int id;
	private int userid;
	private int loveState;
	private int loveDiscuss;
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public String getMyhead() {
		return myhead;
	}
	public void setMyhead(String myhead) {
		this.myhead = myhead;
	}
	public String getMySchool() {
		return mySchool;
	}
	public void setMySchool(String mySchool) {
		this.mySchool = mySchool;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getTohead() {
		return tohead;
	}
	public void setTohead(String tohead) {
		this.tohead = tohead;
	}
	public String getToSchool() {
		return toSchool;
	}
	public void setToSchool(String toSchool) {
		this.toSchool = toSchool;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSayLove() {
		return sayLove;
	}
	public void setSayLove(String sayLove) {
		this.sayLove = sayLove;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getLoveState() {
		return loveState;
	}
	public void setLoveState(int loveState) {
		this.loveState = loveState;
	}
	
	public int getLoveDiscuss() {
		return loveDiscuss;
	}
	public void setLoveDiscuss(int loveDiscuss) {
		this.loveDiscuss = loveDiscuss;
	}
	@Override
	public String toString() {
		return "LoveList [myName=" + myName + ", myhead=" + myhead + ", mySchool=" + mySchool + ", toName=" + toName
				+ ", tohead=" + tohead + ", toSchool=" + toSchool + ", img=" + img + ", sayLove=" + sayLove + ", time="
				+ time + ", like=" + like + ", read=" + read + ", id=" + id + ", userid=" + userid + "]";
	}
	
	
	
}
