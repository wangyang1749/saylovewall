package com.yiaban.test;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.UserDao;
import com.yiban.model.YBUser;

public class Test {
	public static UserDao ud = DaoFactory.getUserDao();
	public static void main(String[] args) {
		//add();
		findId();
	}
	public static void add() {
		YBUser user = new YBUser();
		user.setYbUserid(85);
		user.setYbUsername("张三");
		user.setYbUsernick("下里巴人");
		user.setYbUserhead("www.baidu.com");
		user.setYbSex("男");
		user.setYbSchoolid(789);
		user.setYbExp(456);
		user.setYbMoney(112);
		user.setYbSchoolname("商洛学院");
		System.out.println(user);
		ud.add(user);
	}
	public static void findId() {
		System.out.println(ud.findId(1297286));
	}

}
