package com.yiban.model;
//yb_userid":"12927286",
//"yb_username":"\u98de\u8d70\u7684\u7eb8\u98de\u673a","
//+ ""yb_usernick":"\u98de\u8d70\u7684\u7eb8\u98de\u673a","
//+ ""yb_sex":"M","
//+ ""yb_money":"1131","
//+ ""yb_exp":"601","
//+ ""yb_userhead":"http:\/\/img02.fs.yiban.cn\/12927286\/avatar\/user\/200","
//+ ""yb_schoolid":"10039",
//"yb_schoolname":"\u5546\u6d1b\u5b66\u9662","
//+ ""yb_regtime":"2017-09-20 10:12:33"}
public class YBUser {
	private int ybUserid;
	private String ybUsername;
	private String ybUsernick;
	private String ybSex;
	private int ybMoney;
	private int ybExp;
	private String ybUserhead;
	private int ybSchoolid;
	private String ybSchoolname;
	private String ybTime;
	public int getYbUserid() {
		return ybUserid;
	}
	public void setYbUserid(int ybUserid) {
		this.ybUserid = ybUserid;
	}
	public String getYbUsername() {
		return ybUsername;
	}
	public void setYbUsername(String ybUsername) {
		this.ybUsername = ybUsername;
	}
	public String getYbUsernick() {
		return ybUsernick;
	}
	public void setYbUsernick(String ybUsernick) {
		this.ybUsernick = ybUsernick;
	}
	public String getYbSex() {
		return ybSex;
	}
	public void setYbSex(String ybSex) {
		this.ybSex = ybSex;
	}
	public int getYbMoney() {
		return ybMoney;
	}
	public void setYbMoney(int ybMoney) {
		this.ybMoney = ybMoney;
	}
	public int getYbExp() {
		return ybExp;
	}
	public void setYbExp(int ybExp) {
		this.ybExp = ybExp;
	}
	public String getYbUserhead() {
		return ybUserhead;
	}
	public void setYbUserhead(String ybUserhead) {
		this.ybUserhead = ybUserhead;
	}
	public int getYbSchoolid() {
		return ybSchoolid;
	}
	public void setYbSchoolid(int ybSchoolid) {
		this.ybSchoolid = ybSchoolid;
	}
	public String getYbSchoolname() {
		return ybSchoolname;
	}
	public void setYbSchoolname(String ybSchoolname) {
		this.ybSchoolname = ybSchoolname;
	}
	public String getYbTime() {
		return ybTime;
	}
	public void setYbTime(String ybTime) {
		this.ybTime = ybTime;
	}
	@Override
	public String toString() {
		return "YBUser [ybUserid=" + ybUserid + ", ybUsername=" + ybUsername + ", ybUsernick=" + ybUsernick + ", ybSex="
				+ ybSex + ", ybMoney=" + ybMoney + ", ybExp=" + ybExp + ", ybUserhead=" + ybUserhead + ", ybSchoolid="
				+ ybSchoolid + ", ybSchoolname=" + ybSchoolname + ", ybTime=" + ybTime + "]";
	}
	
	
}
