package com.yiaban.test;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.UserLikeDao;
import com.yiban.model.UserLike;

public class TestUserLikeDao {
	static UserLikeDao uld = DaoFactory.getUserLikeDao();
	public static void main(String[] args) {
//		System.out.println(uld.findLikeUser(18,158175437));
//		UserLike ul= new UserLike();
//		ul.setUserId(15817543);
//		ul.setLoveId(15);
//		uld.add(ul);
		uld.delete(19, 15817543);
	}
}
