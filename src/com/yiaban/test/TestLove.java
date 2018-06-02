package com.yiaban.test;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.LoveLinkDao;
import com.yiban.model.LoveLink;
import com.yiban.model.LoveList;

public class TestLove {
	public static LoveLinkDao love = DaoFactory.getLoveLinkDao();
	public static void main(String[] args) {
		//add();
		//findid();
		//qlist();
		list();
		//love.addUserlike(true, 15);
		//love.addRead(15);
		//System.out.println(love.findUserLike("like", 26)) ;
	}
	public static void add() {
		LoveLink ll = new LoveLink();
		ll.setMyUserId(15817543);
		ll.setToUserId(15817543);
		ll.setSayLove("l love you");
		ll.setLoveImg("e3eee");
		ll.setLoveState(1);
		love.add(ll);
	}
	public static void findid() {
		System.out.println(love.findId("my",1581754).size());
	}
	public static void list() {
		for(LoveList ll :love.selectLoveLinkList("",5)) {
			System.out.println(ll);
		}
	}
	public static void qlist() {
		for(LoveLink ll : love.getLoveLinkList(5)) {
			System.out.println(ll);
		}
	}
}
