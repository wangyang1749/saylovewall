package com.yiban.dao;

public class DaoFactory {
	private static UserDao ud;
	public static  UserDao getUserDao() {
		if(ud==null) {
			ud = new UserDao();
		}
		return ud;
	}
	private static LoveLinkDao love;
	public static  LoveLinkDao getLoveLinkDao() {
		if(love==null) {
			love = new LoveLinkDao();
		}
		return love;
	}
	private static UserLikeDao uld;
	public static  UserLikeDao getUserLikeDao() {
		if(uld==null) {
			uld = new UserLikeDao();
		}
		return uld;
	}
	private static DiscussDao dd;
	public static  DiscussDao getDiscussDao() {
		if(dd==null) {
			dd = new DiscussDao();
		}
		return dd;
	}
}
